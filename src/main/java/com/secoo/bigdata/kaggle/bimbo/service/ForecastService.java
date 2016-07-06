package com.secoo.bigdata.kaggle.bimbo.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.secoo.bigdata.kaggle.bimbo.domain.TrainData;

/**
 * 
 * @author xiewei
 *
 */
public class ForecastService {
	private int beginSemana = 3;
	private int endSemana = 10;

	List<TrainData> fill(List<TrainData> trainDatas) {
		if (CollectionUtils.isNotEmpty(trainDatas)) {
			Collections.sort(trainDatas);
		} else {
			trainDatas = new LinkedList<TrainData>();
		}

		List<TrainData> tmpTrainDatas = new LinkedList<TrainData>();

		int i = beginSemana;
		for (TrainData td : trainDatas) {
			while (td.getSemana() > i) {
				tmpTrainDatas.add(make(i++));
			}
			tmpTrainDatas.add(td);
		}
		while (endSemana > i) {
			tmpTrainDatas.add(make(i++));
		}

		return tmpTrainDatas;
	}

	TrainData make(int i) {
		TrainData td = new TrainData();
		td.setSemana(i);
		td.setDemanda_uni_equil(0);
		return td;
	}

	public Result forecast(List<TrainData> trainDatas) {
		trainDatas = fill(trainDatas);
		Model model = new AverageModel();
		for (int i = 1, len = trainDatas.size(); i < len; i++) {
			model.train(trainDatas.subList(0, i), trainDatas.get(i).getDemanda_uni_equil());
		}
		Result r = new Result();
		r.demand = model.forecast(trainDatas);
		r.mape = model.mape();
		return r;
	}

	public static class Result {
		double demand;
		double mape;

		public double getDemand() {
			return demand;
		}

		public double getMape() {
			return mape;
		}
	}
}

interface Model {
	void train(List<TrainData> trainDatas, double demand);

	double forecast(List<TrainData> trainDatas);

	double mape();
}

class AverageModel implements Model {
	DescriptiveStatistics gaps = new DescriptiveStatistics();
	DescriptiveStatistics mape = new DescriptiveStatistics();

	@Override
	public void train(List<TrainData> trainDatas, double demand) {
		double sum = 0;
		int cnt = 0;
		for (TrainData td : trainDatas) {
			sum += td.getDemanda_uni_equil();
			cnt++;
		}
		double mean = sum / cnt;
		gaps.addValue(mean - demand);
		if (demand != 0)
			mape.addValue(Math.abs(mean - demand) / demand);
		else
			mape.addValue(Math.abs(mean - demand));
	}

	@Override
	public double forecast(List<TrainData> trainDatas) {
		double sum = 0;
		int cnt = 0;
		for (TrainData td : trainDatas) {
			sum += td.getDemanda_uni_equil();
			cnt++;
		}
		double mean = sum / cnt;
		return mean;
	}

	@Override
	public double mape() {
		return mape.getMean();
	}

}
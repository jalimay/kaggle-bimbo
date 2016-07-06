package com.secoo.bigdata.kaggle.bimbo.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;

import com.secoo.bigdata.kaggle.bimbo.domain.TestData;
import com.secoo.bigdata.kaggle.bimbo.domain.TrainData;

public class GroupTrainDataService {
	public GroupData group(Iterable<Text> values) {
		GroupData gd = new GroupData();
		for (Text value : values) {
			String[] cols = value.toString().split(",");
			if (cols.length == 7) {
				TestData td = TestData.make(value.toString());
				gd.testDatas.add(td);
			} else if (cols.length == 11) {
				TrainData td = TrainData.make(value.toString());
				String tk = groupKey(td.getAgencia_ID(), td.getCanal_ID(), td.getRuta_SAK(), td.getProducto_ID());
				if (gd.trainDatas.containsKey(tk)) {
					gd.trainDatas.get(tk).add(td);
				} else {
					List<TrainData> tds = new LinkedList<TrainData>();
					tds.add(td);
					gd.trainDatas.put(tk, tds);
				}
			}
		}
		return gd;
	}

	public String groupKey(long Agencia_ID, long Canal_ID, long Ruta_SAK, long Producto_ID) {
		return Agencia_ID + "_" + Canal_ID + "_" + Ruta_SAK + "_" + Producto_ID;
	}

	public static class GroupData {
		List<TestData> testDatas = new LinkedList<TestData>();
		Map<String, List<TrainData>> trainDatas = new HashMap<String, List<TrainData>>();

		public List<TestData> getTestDatas() {
			return testDatas;
		}

		public Map<String, List<TrainData>> getTrainDatas() {
			return trainDatas;
		}
	}
}

package com.secoo.bigdata.kaggle.bimbo.mr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import com.secoo.bigdata.kaggle.bimbo.domain.TestData;
import com.secoo.bigdata.kaggle.bimbo.domain.TrainData;
import com.secoo.bigdata.kaggle.bimbo.service.ForecastService;
import com.secoo.bigdata.kaggle.bimbo.service.ForecastService.Result;
import com.secoo.bigdata.kaggle.bimbo.service.GroupTrainDataService;
import com.secoo.bigdata.kaggle.bimbo.service.GroupTrainDataService.GroupData;

/**
 * 按客户进行预测，假设每个客户的购买结果是独立的
 * 
 * @author xiewei
 *
 */
public class Forecast {
	public static class M extends Mapper<LongWritable, Text, LongWritable, Text> {
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] cols = value.toString().split(",");
			try {
				if (cols.length == 7) {
					TestData td = TestData.make(value.toString());
					context.write(new LongWritable(td.getCliente_ID()), value);
				} else if (cols.length == 11) {
					TrainData td = TrainData.make(value.toString());
					context.write(new LongWritable(td.getCliente_ID()), value);
				}
			} catch (Exception e) {

			}
		}
	}

	public static class R extends Reducer<LongWritable, Text, LongWritable, Text> {
		private GroupTrainDataService groupTrainDataService = new GroupTrainDataService();
		private ForecastService forecastService = new ForecastService();

		@Override
		protected void reduce(LongWritable key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			GroupData gd = groupTrainDataService.group(values);
			List<TestData> testDatas = gd.getTestDatas();
			Map<String, List<TrainData>> trainDatas = gd.getTrainDatas();

			for (TestData testData : testDatas) {
				String tk = groupTrainDataService.groupKey(testData.getAgencia_ID(), testData.getCanal_ID(),
						testData.getRuta_SAK(), testData.getProducto_ID());

				Result r = forecastService.forecast(trainDatas.get(tk));

				context.write(new LongWritable(testData.getId()), new Text(String.valueOf(r.getDemand())));
			}
		}

	}
}

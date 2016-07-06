package com.secoo.bigdata.kaggle.bimbo.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.secoo.bigdata.kaggle.bimbo.domain.TestData;
import com.secoo.bigdata.kaggle.bimbo.domain.TrainData;

/**
 * 按客户ID进行抽样
 * 
 * @author xiewei
 *
 */
public class SampleData {
	public static class M extends Mapper<LongWritable, Text, NullWritable, Text> {
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] cols = value.toString().split(",");
			try {
				if (cols.length == 7) {
					TestData td = TestData.make(value.toString());
					if (td.getCliente_ID() % 100 == 5)
						context.write(null, value);
				} else if (cols.length == 11) {
					TrainData td = TrainData.make(value.toString());
					if (td.getCliente_ID() % 100 == 5)
						context.write(null, value);
				}
			} catch (Exception e) {
				context.getCounter("error", cols.length + "").increment(1);
			}
		}
	}
}

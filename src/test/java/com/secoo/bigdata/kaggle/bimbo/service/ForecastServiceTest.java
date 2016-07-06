package com.secoo.bigdata.kaggle.bimbo.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.secoo.bigdata.kaggle.bimbo.domain.TestData;
import com.secoo.bigdata.kaggle.bimbo.service.ForecastService.Result;
import com.secoo.bigdata.kaggle.bimbo.service.GroupTrainDataService.GroupData;

public class ForecastServiceTest {
	Logger log = Logger.getLogger(getClass());

	@Test
	public void test() throws IOException {
		List<String> testDataLines = IOUtils.readLines(getClass().getResourceAsStream("/text/testDataSample.txt"));
		List<String> trainDataLines = IOUtils.readLines(getClass().getResourceAsStream("/text/trainDataSample.txt"));

		List<Text> lines = new LinkedList<Text>();
		for (String testDataLine : testDataLines)
			lines.add(new Text(testDataLine));
		for (String trainDataLine : trainDataLines)
			lines.add(new Text(trainDataLine));

		GroupTrainDataService groupTrainDataService = new GroupTrainDataService();
		GroupData gd = groupTrainDataService.group(lines);

		ForecastService model = new ForecastService();
		for (TestData td : gd.getTestDatas()) {
			String k = groupTrainDataService.groupKey(td.getAgencia_ID(), td.getCanal_ID(), td.getRuta_SAK(),
					td.getProducto_ID());
			Result r = model.forecast(gd.getTrainDatas().get(k));
			log.info(k + ":" + r.demand + "/" + r.mape);
		}

	}

}

package com.secoo.bigdata.kaggle.bimbo.domain;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TrainDataTest {

	@Test
	public void testTrainData() throws IOException {
		List<String> lines = IOUtils.readLines(getClass().getResourceAsStream("/text/trainDataSample.txt"));
		for (String line : lines) {
			TrainData t = TrainData.make(line);
			System.out.println(t.toString());
		}
	}

}

package com.secoo.bigdata.kaggle.bimbo.domain;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestDataTest {

	@Test
	public void testTestData() throws IOException {
		List<String> lines = IOUtils.readLines(getClass().getResourceAsStream("/text/testDataSample.txt"));
		for (String line : lines) {
			TestData t = TestData.make(line);
			System.out.println(t.toString());
		}
	}

}

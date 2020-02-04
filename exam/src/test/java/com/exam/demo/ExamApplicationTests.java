package com.exam.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamApplicationTests {

	@Test
	public void testExceptionWhenMoreRanges() {
		try {
			String inputDataSet = "[92004,92002,92003] [92003,92004]";
			ZipcodeProcessor zipcodeProcessor = new ZipcodeProcessor(inputDataSet);
			List<Zipcode> zipcodeList = zipcodeProcessor.stripZipcode();
		} catch (IllegalArgumentException e) {
			assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
		}
	}

	@Test
	public void testExceptionMessageWhenLowerBoundGreater() {
		try {
			String inputDataSet = "[92004,92002] [92003,92004]";
			ZipcodeProcessor zipcodeProcessor = new ZipcodeProcessor(inputDataSet);
			List<Zipcode> zipcodeList = zipcodeProcessor.stripZipcode();
		} catch (IllegalArgumentException e) {
			String expectedMessage = "92004 92002:  Zipcode lower bound should be less" + " than upper bound";
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void testSortingBeforeMerging() {
		Zipcode zipcode1 = new Zipcode(95000, 95005);
		Zipcode zipcode2 = new Zipcode(95002, 95006);
		List<Zipcode> zipcodeList = new LinkedList<Zipcode>();
		zipcodeList.add(zipcode2);
		zipcodeList.add(zipcode1);
		ZipcodeMerger zipcode_merger = new ZipcodeMerger();
		List<Zipcode> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
		assertTrue(sortedZipCodeList.get(0) == zipcode1);
	}

	@Test
	public void testLoadAfterCallingMerge() {
		Zipcode zipcode1 = new Zipcode(95000, 95005);
		Zipcode zipcode2 = new Zipcode(95007, 95008);
		List<Zipcode> zipcodeList = new LinkedList<Zipcode>();
		zipcodeList.add(zipcode1);
		zipcodeList.add(zipcode2);
		ZipcodeMerger zipcode_merger = new ZipcodeMerger();
		List<Zipcode> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
		List<Zipcode> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
		assertEquals(mergedZipcodeList, zipcodeList);
	}

}

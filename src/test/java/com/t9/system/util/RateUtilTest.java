package com.t9.system.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RateUtilTest {

	@Test
	public void testRun() {
		double x=RateUtil.run(700.4, 0.24, 597);
		System.out.println(x);
		
		x=RateUtil.rate(100, 0.24, 20);
		System.out.println(x);
		
		x=RateUtil.averageInterest(100, 0.19, 36);
		System.out.println(x);
		
		x=RateUtil.averageInterestAll(100, 0.20, 36);
		System.out.println(x);
	}

}

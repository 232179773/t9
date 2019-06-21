package com.t9.system.util;

public class RateUtil {

	
	public static double run(double total,double rate,int day){
		double x=total/(1+day*rate/360);
		return x;
	}
	
	public static double rate(double total,double rate,int month){
		double x=total*Math.pow((1+rate/12), month);
		return x;
	}
	/*
	 * 等额本息
	 */
	public static double averageInterest(double total,double rate,int month){
		double temp=Math.pow((1+rate/12), month);
		double mTotal=total*rate/12*temp/(temp-1);
		return mTotal;
	}
	
	/*
	 * 等额本息
	 */
	public static double averageInterestAll(double total,double rate,int month){
		double mTotal=total*Math.pow((1+rate/12), month);
		return mTotal;
	}
}

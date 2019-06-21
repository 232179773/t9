package com.t9.system.util;


public class loanUtil {

	public static double loan(int totleLoan,int monthLoan,double rateMonth){
		totleLoan=totleLoan*100;//从元转化为分
		double mm=Math.pow((1+rateMonth), monthLoan);
		double p=(totleLoan*rateMonth*mm)/(mm-1);
		double x=toMoneyByDouble(p);
		return x;
	}
	
	public static double toMoneyByDouble(double money){
		return (int)money/(double)100;
	}
	
	public static void myRate(){
		int totleMoney=500;
		int totleMonth=36;
		double rateyear=0.045;
		double rateMonth=rateyear/12;
		double p=loan(totleMoney,totleMonth,rateMonth);
		int totleMoneySecond=totleMoney*100;
		System.out.println("期数:\t剩余本金\t每月还款\t还款利息\t还款本金");
		for (int i = 0; i < totleMonth; i++) {
			double rateMoney=toMoneyByDouble(totleMoneySecond*rateMonth); //每个月还款利息
			double repaymentPrincipal=p-rateMoney;//每个月还款本金
			totleMoneySecond=totleMoneySecond-(int)(repaymentPrincipal*100);//待还本金
			
			System.out.println((i+1)+":\t"+toMoneyByDouble(totleMoneySecond)+"\t"+p+"\t"+rateMoney+"\t"+repaymentPrincipal);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//	loan(1000000,36,0.0861);
		int totleMoney=1000;
		int totleMonth=36;
		double rateyear=0.132;
		double rateMonth=rateyear/12;
		double p=loan(totleMoney,totleMonth,rateMonth);
		int totleMoneySecond=totleMoney*100;
		System.out.println("期数:\t剩余本金\t每月还款\t还款利息\t还款本金");
		for (int i = 0; i < totleMonth; i++) {
			double rateMoney=toMoneyByDouble(totleMoneySecond*rateMonth); //每个月还款利息
			double repaymentPrincipal=p-rateMoney;//每个月还款本金
			totleMoneySecond=totleMoneySecond-(int)(repaymentPrincipal*100);//待还本金
			
			System.out.println((i+1)+":\t"+toMoneyByDouble(totleMoneySecond)+"\t"+p+"\t"+rateMoney+"\t"+repaymentPrincipal);
		}
		
	}

}

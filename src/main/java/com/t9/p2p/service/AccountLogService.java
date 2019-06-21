package com.t9.p2p.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.AccountLogDao;
import com.t9.p2p.entity.AccountLog;
import com.t9.system.util.DataUtil;
import com.t9.system.util.DateUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.PageInfo;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9RuningTimeException;

@Service
public class AccountLogService{
	@Autowired
	private AccountLogDao accountLogDao;
	
	public List<Map> queryAccountLogList(Map<String, String> map,PageInfo pageInfo) {
		return accountLogDao.queryAccountLogList(map,pageInfo);
	}

	public List<Map> queryCalculatorList(AccountLog accountLog) {
		List result=new ArrayList();
		String tenderType=accountLog.getTENDER_TYPE();
		String rate=accountLog.getINTEREST_RATE();
		String interestCost=accountLog.getINTEREST_COST();
		String reward=accountLog.getREWARD();
		double dCost=0.0;
		double dReward=0.0;
		if(StringUtil.isNotEmpty(interestCost)){
			if(!interestCost.endsWith("%"))
				throw new T9RuningTimeException("interestCost "+interestCost +" is not current!!!");
			interestCost=interestCost.substring(0, interestCost.length()-1);
			dCost=Double.parseDouble(interestCost)/100;
		}
		if(StringUtil.isNotEmpty(reward)){
			if(!reward.endsWith("%"))
				throw new T9RuningTimeException("reward "+reward +" is not current!!!");
			reward=reward.substring(0, reward.length()-1);
			dReward=Double.parseDouble(reward)/100;
		}
		String earnMoney=DataUtil.douleToMoneyString(accountLog.getMONEY()*dReward);
		accountLog.setEARN_MONEY(Double.parseDouble(earnMoney));
		
		double money=accountLog.getMONEY();
			if("day".equals(tenderType)){
				HashMap map=new HashMap();
				int day=Integer.parseInt(accountLog.getTENDER_DURATION());
				map.put("DUE_TIME", DateUtil.addDay(accountLog.getDUE_TIME(), day));
				result.add(map);
			}else{
				int dureTime=Integer.parseInt(accountLog.getTENDER_DURATION());	
				int type=Integer.parseInt(accountLog.getREPAMENT_TYPE());				
				if(!rate.endsWith("%"))
					throw new T9RuningTimeException("rate "+rate +" is not current!!!");
				rate=rate.substring(0, rate.length()-1);
				double drate=Double.parseDouble(rate)/100;
				double mrate=drate/12;
				
				switch(type){
				case 1://到期还本付息
				{
					double monthPrincipal=money*dureTime*mrate;
					monthPrincipal=monthPrincipal-monthPrincipal*dCost;
					String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
					HashMap map=new HashMap();
					map.put("DUE_TIME", DateUtil.addDay(accountLog.getDUE_TIME(), dureTime));
					map.put("MONEY", money+Double.parseDouble(monthPrincipalStr));
					map.put("COLLECTION_MONEY", money);
					map.put("EARN_MONEY", Double.parseDouble(monthPrincipalStr));
					map.put("EARN_TOTAL_MONEY", Double.parseDouble(monthPrincipalStr));
					result.add(map);
				}
					break;
				case 2://等额本息
					double temp=Math.pow((1+mrate), dureTime);
					double mTotal=money*mrate*temp/(temp-1);
					String mTotalStr=DataUtil.douleToMoneyString(mTotal);
			//		System.out.println("per month sum is:"+mTotalStr);
					double earnTotalMoney=0;
					double daiCollectionMoney=money;
					double daiEarnMoney=(mTotal*dureTime-money)*(1-dCost);
					for (int i = 1; i <= dureTime; i++) {
						double temp2=Math.pow((1+mrate), i-1);
						double mInterest=money*mrate*temp2/(temp-1);
						String mInterestStr=DataUtil.douleToMoneyString(mInterest);
				//		System.out.println("per month interest is:"+mInterestStr);
						double mPrincipal=mTotal-mInterest;
						mPrincipal=mPrincipal-mPrincipal*dCost;
						String mPrincipalStr=DataUtil.douleToMoneyString(mPrincipal);
				//		System.out.println("per month principal is:"+mPrincipalStr);
						
						HashMap map=new HashMap();
						map.put("DUE_TIME",i);
						map.put("MONEY", Double.parseDouble(mTotalStr));
						map.put("COLLECTION_MONEY", Double.parseDouble(mInterestStr));
						map.put("EARN_MONEY", Double.parseDouble(mPrincipalStr));
						earnTotalMoney+=Double.parseDouble(mPrincipalStr);
						map.put("EARN_TOTAL_MONEY",DataUtil.douleToMoneyString(earnTotalMoney));
						
						daiCollectionMoney=daiCollectionMoney-mInterest;
						daiEarnMoney=daiEarnMoney-mPrincipal;
						map.put("DAI_COLLECTION_MONEY", DataUtil.douleToMoneyString(daiCollectionMoney));
						map.put("DAI_EARN_MONEY", DataUtil.douleToMoneyString(daiEarnMoney));
						result.add(map);
						
					}
					break;
				case 3://等额本金
					earnTotalMoney=0;
					for (int i = 1; i <= dureTime; i++) {
						double monthTotal=(money/dureTime)+money/dureTime*(dureTime-i+1)*mrate;
						double monthPrincipal=money/dureTime*(dureTime-i+1)*mrate;
						monthPrincipal=monthPrincipal-monthPrincipal*dCost;
						double monthInterest=money/dureTime;
						String monthTotalStr=DataUtil.douleToMoneyString(monthTotal);
						String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
						String monthInterestStr=DataUtil.douleToMoneyString(monthInterest);
//						System.out.println("per month total is:"+monthTotalStr);
//						System.out.println("per month interest is:"+monthInterestStr);
//						System.out.println("per month principal is:"+monthPrincipalStr);
						

						HashMap map=new HashMap();
						map.put("DUE_TIME", i);
						map.put("MONEY", Double.parseDouble(monthTotalStr));
						map.put("COLLECTION_MONEY", Double.parseDouble(monthInterestStr));
						map.put("EARN_MONEY", Double.parseDouble(monthPrincipalStr));
						earnTotalMoney+=Double.parseDouble(monthPrincipalStr);
						map.put("EARN_TOTAL_MONEY",DataUtil.douleToMoneyString(earnTotalMoney));
						result.add(map);
					}
					break;
				case 4://按月付息，到期还本
					double monthPrincipal=money*mrate;
					monthPrincipal=monthPrincipal-monthPrincipal*dCost;
					earnTotalMoney=0;
					for (int i = 1; i <= dureTime; i++) {
						String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
//						System.out.println("per month total is:"+monthTotalStr);
//						System.out.println("per month interest is:"+monthInterestStr);
//						System.out.println("per month principal is:"+monthPrincipalStr);
						

						HashMap map=new HashMap();
						map.put("DUE_TIME", i);
						
						if(i != dureTime){
							map.put("MONEY", Double.parseDouble(monthPrincipalStr));
							map.put("COLLECTION_MONEY", "0.0");
							map.put("EARN_MONEY", Double.parseDouble(monthPrincipalStr));
						}else{
							map.put("MONEY",Double.parseDouble(monthPrincipalStr)+money);
							map.put("COLLECTION_MONEY", money);
							map.put("EARN_MONEY", Double.parseDouble(monthPrincipalStr));
						}
						earnTotalMoney+=Double.parseDouble(monthPrincipalStr);
						map.put("EARN_TOTAL_MONEY",DataUtil.douleToMoneyString(earnTotalMoney));
						result.add(map);
					}
					break;
				}
			}
		return result;
	}
	

	public List<Map> queryCalculatorCompList(Map<String, String> tMap) {
		List result=new ArrayList();
		String rate=tMap.get("INTEREST_RATE");
		if(rate.endsWith("%")){
			rate=rate.substring(0, rate.length()-1);
		}
		double drate=Double.parseDouble(rate)/100;

		int dureTime=Integer.parseInt(tMap.get("TENDER_DURATION"));	
		
		double mrate=drate/12;
		
		double money=Double.parseDouble(tMap.get("MONEY"));
		double monthMoney=Double.parseDouble(tMap.get("MONTH_MONEY"));
		double collectionMoney=money;
		double totalMoney=money;
		double earnMoneyToal=0;
		for (int i = 1; i <= dureTime; i++) {
			HashMap map=new HashMap();
			collectionMoney=collectionMoney+monthMoney;
			map.put("DUE_TIME", i);
			map.put("COLLECTION_MONEY", collectionMoney);

			double earnMoney=totalMoney*mrate;
			map.put("EARN_MONEY", DataUtil.douleToMoneyString(earnMoney));
			earnMoneyToal=earnMoneyToal+earnMoney;
			map.put("EARN_MONEY_TOTAL", DataUtil.douleToMoneyString(earnMoneyToal));

			totalMoney=collectionMoney+earnMoneyToal;
			map.put("TOTAL_MONEY", DataUtil.douleToMoneyString(totalMoney));
			result.add(map);
		}
		
		return result;
	}

	public T9Result saveAccountLog(AccountLog accountLog) {
		T9Result result=new T9Result();
		String logType=accountLog.getLOG_TYPE();
		String tenderType=accountLog.getTENDER_TYPE();
		String rate=accountLog.getINTEREST_RATE();
		String interestCost=accountLog.getINTEREST_COST();
		String reward=accountLog.getREWARD();
		double dCost=0.0;
		double dReward=0.0;
		if(StringUtil.isNotEmpty(interestCost)){
			if(!interestCost.endsWith("%"))
				throw new T9RuningTimeException("interestCost "+interestCost +" is not current!!!");
			interestCost=interestCost.substring(0, interestCost.length()-1);
			dCost=Double.parseDouble(interestCost)/100;
		}
		if(StringUtil.isNotEmpty(reward)){
			if(!reward.endsWith("%"))
				throw new T9RuningTimeException("reward "+reward +" is not current!!!");
			reward=reward.substring(0, reward.length()-1);
			dReward=Double.parseDouble(reward)/100;
		}
		String earnMoney=DataUtil.douleToMoneyString(accountLog.getMONEY()*dReward);
		accountLog.setEARN_MONEY(Double.parseDouble(earnMoney));
		accountLogDao.save(accountLog);
		
		double money=accountLog.getMONEY();
		if("bid".equals(logType)){
			if("day".equals(tenderType)){
				AccountLog tLog=new AccountLog();
				tLog.setSITE_NAME(accountLog.getSITE_NAME());
				tLog.setLOG_TYPE("gather");
				int day=Integer.parseInt(accountLog.getTENDER_DURATION());
				tLog.setDUE_TIME(DateUtil.addDay(accountLog.getDUE_TIME(), day));
				tLog.setTENDER_TYPE(accountLog.getTENDER_TYPE());
				tLog.setBID_TITLE(accountLog.getBID_TITLE());
				tLog.setBID_URL(accountLog.getBID_URL());
				tLog.setGATHER_STATE("0");
				tLog.setREMARK(accountLog.getID());
				accountLogDao.save(tLog);
			}else{
				int dureTime=Integer.parseInt(accountLog.getTENDER_DURATION());	
				int type=Integer.parseInt(accountLog.getREPAMENT_TYPE());				
				if(!rate.endsWith("%"))
					throw new T9RuningTimeException("rate "+rate +" is not current!!!");
				rate=rate.substring(0, rate.length()-1);
				double drate=Double.parseDouble(rate)/100;
				double mrate=drate/12;
				
				switch(type){
				case 1://到期还本付息
				{
					double monthPrincipal=money*dureTime*mrate;
					monthPrincipal=monthPrincipal-monthPrincipal*dCost;
					String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
					AccountLog tLog=new AccountLog();
					tLog.setSITE_NAME(accountLog.getSITE_NAME());
					tLog.setLOG_TYPE("gather");
					tLog.setDUE_TIME(DateUtil.addMonth(accountLog.getDUE_TIME(), dureTime));
					tLog.setTENDER_TYPE(accountLog.getTENDER_TYPE());
					tLog.setREPAMENT_TYPE(accountLog.getREPAMENT_TYPE());
					tLog.setTENDER_DURATION(accountLog.getTENDER_DURATION());
					tLog.setBID_TITLE(accountLog.getBID_TITLE());
					tLog.setBID_URL(accountLog.getBID_URL());
					tLog.setGATHER_STATE("0");
					tLog.setREMARK(accountLog.getID());
					tLog.setMONEY(money+Double.parseDouble(monthPrincipalStr));
					tLog.setCOLLECTION_MONEY(money);
					tLog.setEARN_MONEY(Double.parseDouble(monthPrincipalStr));
					accountLogDao.save(tLog);
				}
					break;
				case 2://等额本息
					double temp=Math.pow((1+mrate), dureTime);
					double mTotal=money*mrate*temp/(temp-1);
					String mTotalStr=DataUtil.douleToMoneyString(mTotal);
			//		System.out.println("per month sum is:"+mTotalStr);

					for (int i = 1; i <= dureTime; i++) {
						double temp2=Math.pow((1+mrate), i-1);
						double mInterest=money*mrate*temp2/(temp-1);
						String mInterestStr=DataUtil.douleToMoneyString(mInterest);
				//		System.out.println("per month interest is:"+mInterestStr);
						double mPrincipal=mTotal-mInterest;
						mPrincipal=mPrincipal-mPrincipal*dCost;
						String mPrincipalStr=DataUtil.douleToMoneyString(mPrincipal);
				//		System.out.println("per month principal is:"+mPrincipalStr);
						

						AccountLog tLog=new AccountLog();
						tLog.setSITE_NAME(accountLog.getSITE_NAME());
						tLog.setLOG_TYPE("gather");
						tLog.setDUE_TIME(DateUtil.addMonth(accountLog.getDUE_TIME(), i));
						tLog.setTENDER_TYPE(accountLog.getTENDER_TYPE());
						tLog.setREPAMENT_TYPE(accountLog.getREPAMENT_TYPE());
						tLog.setTENDER_DURATION(accountLog.getTENDER_DURATION());
						tLog.setBID_TITLE(accountLog.getBID_TITLE());
						tLog.setBID_URL(accountLog.getBID_URL());
						tLog.setGATHER_STATE("0");
						tLog.setREMARK(accountLog.getID());
						tLog.setMONEY(Double.parseDouble(mTotalStr));
						tLog.setCOLLECTION_MONEY(Double.parseDouble(mInterestStr));
						tLog.setEARN_MONEY(Double.parseDouble(mPrincipalStr));
						accountLogDao.save(tLog);
					}
					break;
				case 3://等额本金
					for (int i = 1; i <= dureTime; i++) {
						double monthTotal=(money/dureTime)+money/dureTime*(dureTime-i+1)*mrate;
						double monthPrincipal=money/dureTime*(dureTime-i+1)*mrate;
						monthPrincipal=monthPrincipal-monthPrincipal*dCost;
						double monthInterest=money/dureTime;
						String monthTotalStr=DataUtil.douleToMoneyString(monthTotal);
						String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
						String monthInterestStr=DataUtil.douleToMoneyString(monthInterest);
//						System.out.println("per month total is:"+monthTotalStr);
//						System.out.println("per month interest is:"+monthInterestStr);
//						System.out.println("per month principal is:"+monthPrincipalStr);
						

						AccountLog tLog=new AccountLog();
						tLog.setSITE_NAME(accountLog.getSITE_NAME());
						tLog.setLOG_TYPE("gather");
						tLog.setDUE_TIME(DateUtil.addMonth(accountLog.getDUE_TIME(), i));
						tLog.setTENDER_TYPE(accountLog.getTENDER_TYPE());
						tLog.setTENDER_DURATION(accountLog.getTENDER_DURATION());
						tLog.setREPAMENT_TYPE(accountLog.getREPAMENT_TYPE());
						tLog.setBID_TITLE(accountLog.getBID_TITLE());
						tLog.setBID_URL(accountLog.getBID_URL());
						tLog.setGATHER_STATE("0");
						tLog.setREMARK(accountLog.getID());
						tLog.setMONEY(Double.parseDouble(monthTotalStr));
						tLog.setCOLLECTION_MONEY(Double.parseDouble(monthInterestStr));
						tLog.setEARN_MONEY(Double.parseDouble(monthPrincipalStr));
						accountLogDao.save(tLog);
					}
					break;
				case 4://按月付息，到期还本
					double monthPrincipal=money*mrate;
					monthPrincipal=monthPrincipal-monthPrincipal*dCost;
					for (int i = 1; i <= dureTime; i++) {
						String monthPrincipalStr=DataUtil.douleToMoneyString(monthPrincipal);
//						System.out.println("per month total is:"+monthTotalStr);
//						System.out.println("per month interest is:"+monthInterestStr);
//						System.out.println("per month principal is:"+monthPrincipalStr);
						AccountLog tLog=new AccountLog();
						tLog.setSITE_NAME(accountLog.getSITE_NAME());
						tLog.setLOG_TYPE("gather");
						tLog.setDUE_TIME(DateUtil.addMonth(accountLog.getDUE_TIME(), i));
						tLog.setTENDER_TYPE(accountLog.getTENDER_TYPE());
						tLog.setTENDER_DURATION(accountLog.getTENDER_DURATION());
						tLog.setREPAMENT_TYPE(accountLog.getREPAMENT_TYPE());
						tLog.setBID_TITLE(accountLog.getBID_TITLE());
						tLog.setBID_URL(accountLog.getBID_URL());
						tLog.setGATHER_STATE("0");
						tLog.setREMARK(accountLog.getID());
						if(i != dureTime){
							tLog.setMONEY(Double.parseDouble(monthPrincipalStr));
							tLog.setCOLLECTION_MONEY(0.0);
							tLog.setEARN_MONEY(Double.parseDouble(monthPrincipalStr));
						}else{
							tLog.setMONEY(Double.parseDouble(monthPrincipalStr)+money);
							tLog.setCOLLECTION_MONEY(money);
							tLog.setEARN_MONEY(Double.parseDouble(monthPrincipalStr));
						}
						accountLogDao.save(tLog);
					}
					break;
				}
			}
			
		}else if("gather".equals(logType)){
		
			
		}
		return result;
	}
	
	Pattern ratePattern=Pattern.compile("\\d+\\.\\d+%");
	
	public double computerDayTender(double money,String rate,int dureTime){
		if(!rate.endsWith("%"))
			throw new T9RuningTimeException("rate "+rate +" is not current!!!");
		rate=rate.substring(0, rate.length()-1);
		Double drate=Double.parseDouble(rate);
		double interest=((int)(money*drate/360*dureTime*100))/(double)100;
		System.out.println(interest);
		return interest;
	}

	public T9Result updateAccountLog(AccountLog accountLog) {
//		AccountLog oldLog=queryAccountLogById(accountLog.getID());
		T9Result result=new T9Result();
		String logType=accountLog.getLOG_TYPE();
		String gatherState=accountLog.getGATHER_STATE();
		String tenderType=accountLog.getTENDER_TYPE();
		if("bid".equals(logType)&&gatherState.equals("1")){//提前还款时，删除剩余待还记录
			Date repaymentTime=accountLog.getREPAYMENT_TIME();
			accountLogDao.deleteRepaymentBid(accountLog.getID(), repaymentTime);			
			accountLog.setCOLLECTION_MONEY(accountLog.getMONEY());
		}else if("gather".equals(logType)){
			AccountLog tLog=queryAccountLogById(accountLog.getREMARK());
//			if("gather".equals(oldLog.getLOG_TYPE())){//已还款
//				
//			}
			double earnMoney=(tLog.getEARN_MONEY()==null?0:tLog.getEARN_MONEY())+(accountLog.getEARN_MONEY()==null?0:accountLog.getEARN_MONEY());
			tLog.setEARN_MONEY(earnMoney);
			int noRepay=accountLogDao.queryNoRepaymentByBid(accountLog.getREMARK());
			if(noRepay==1)
				tLog.setGATHER_STATE("1");
			accountLogDao.update(tLog);
		}
		accountLogDao.update(accountLog);
		return result;
	}

	public T9Result deleteAccountLog(String[] ids) {
		T9Result result=new T9Result();
		accountLogDao.delete(ids);
		return result;
	}

	public AccountLog queryAccountLogById(String id) {
		return (AccountLog)accountLogDao.get(id);

	}
}

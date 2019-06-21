package com.t9.p2p.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.util.DateUtil;
import com.t9.system.web.PageInfo;

@Repository
public class MoneyLogDao extends BaseDaoImpl {
	
	
	public List<Map> queryMoneyLogList(Map<String, String> map,PageInfo pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_MONEYLOG WHERE 1=1 ");
		sqlQuery.addLikeParam("SITE_NAME", map.get("SITE_NAME"));
		sqlQuery.addLikeParam("PAY_TYPE", map.get("PAY_TYPE"));
		sqlQuery.addEquealParam(map, "LOG_TYPE","GATHER_STATE","PAY_BANK");
		sqlQuery.addDateRange(map, "DUE_TIME");
		sqlQuery.addSQL("order by due_time desc");
		List<Map> list= this.querySql(sqlQuery,pageInfo);
		return list;
	}

	//TOTALMONEY,MONEYDAY
	public List<Map> queryPletMoneyList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT SITE_NAME，DUE_TIME，PAY_TYPE，MONEY FROM TB_P2P_MONEYLOG order by SITE_NAME,DUE_TIME asc");
		ArrayList list= (ArrayList)this.querySql(sqlQuery);
		String currSite="",lastSite="";
		double money;
		Date dueTime;
		String PAY_TYPE;
		String TOTALMONEY;
		int MONEYDAY;
		for (int i = 0; i < list.size(); i++) {
			Map dataMap=(Map)list.get(i);
			currSite=(String)dataMap.get("SITE_NAME");
			money=Double.parseDouble((String)dataMap.get("MONEY"));
			dueTime=DateUtil.parasDate((String)dataMap.get("DUE_TIME")) ;
			PAY_TYPE=(String)dataMap.get("PAY_TYPE");
			if(!currSite.equals(lastSite)){
				TOTALMONEY=(String)dataMap.get("MONEY");
				dataMap.put("TOTALMONEY", TOTALMONEY);
			}else{
				Map lastDataMap=(Map)list.get(i-1);
			}
			lastSite=currSite;
		}
		return list;
	}
}

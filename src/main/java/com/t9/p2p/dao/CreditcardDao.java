package com.t9.p2p.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.util.DataUtil;

@Repository
@SuppressWarnings({"rawtypes"})
public class CreditcardDao extends BaseDaoImpl{
	
	public double queryBill(String BANK_NAME,String datetime) {		
		SQLQuery sqlQuery=new SQLQuery("select SUM(COU) COU from (");
		sqlQuery.addSQL("select SUM(happen_money) COU from tb_p2p_cardlog where bank_name=? and ((HAPPEN_TIME<=? and HAPPEN_TYPE!='3') ",BANK_NAME,datetime);
		sqlQuery.addSQL("or (HAPPEN_TYPE='3')) ");
		sqlQuery.addSQL("union all ");
		sqlQuery.addSQL("select SUM(money) COU from tb_p2p_moneylog where pay_bank=? and DUE_TIME<=?",BANK_NAME,datetime);
		sqlQuery.addSQL(")a");
		List<Map> tList= this.querySql(sqlQuery);
		
		double money=DataUtil.getdoubleFromStr((String)tList.get(0).get("COU"));
		return money;
	}
	
}

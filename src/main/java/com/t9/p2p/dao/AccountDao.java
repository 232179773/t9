package com.t9.p2p.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.web.PageInfo;

@Repository
public class AccountDao extends BaseDaoImpl {
	
	
	public List<Map> queryAccountList(Map<String, String> map,PageInfo pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_ACCOUNT WHERE 1=1 ");
		sqlQuery.addLikeParam("SITE_NAME", map.get("SITE_NAME"));
		if(map.get("PAY_TYPE")!=null){
			sqlQuery.addSQL(" and PAY_TYPE like '%"+map.get("PAY_TYPE")+"%'");
		}
		sqlQuery.addDateRange(map, "REGISTER_TIME");
		List<Map> list= this.querySql(sqlQuery,pageInfo);
		return list;
	}

}

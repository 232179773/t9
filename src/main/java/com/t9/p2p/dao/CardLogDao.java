package com.t9.p2p.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.web.PageInfo;

@Repository
public class CardLogDao extends BaseDaoImpl {
	
	
	public List<Map> queryCardLogList(Map<String, String> map,PageInfo pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_CARDLOG WHERE 1=1 ");
		sqlQuery.addLikeParam("STORE_NAME", map.get("STORE_NAME"));
		List<Map> list= this.querySql(sqlQuery,pageInfo);
		return list;
	}

}

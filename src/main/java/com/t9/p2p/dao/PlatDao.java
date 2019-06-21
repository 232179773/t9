package com.t9.p2p.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.util.StringUtil;
import com.t9.system.web.PageInfo;

@Repository
public class PlatDao extends BaseDaoImpl {
	
	
	public List<Map> queryPlatList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_PLAT WHERE 1=1 ");
		sqlQuery.addLikeParam("plat", map.get("PLAT"));
		sqlQuery.addEquealParam("MONTH", map.get("MONTH"));
		String rankCol=map.get("rankCol");
		if(StringUtil.isEmpty(rankCol)){
			rankCol="rank";
		}
		sqlQuery.addSQL(" ORDER BY MONTH DESC,"+rankCol+" asc");
		List<Map> list= this.queryPageSql(sqlQuery);
		return list;
	}

}

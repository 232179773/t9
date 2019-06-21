package com.t9.p2p.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.BaseDaoImpl;
import com.t9.system.util.DateUtil;
import com.t9.system.web.PageInfo;

@Repository
public class AccountLogDao extends BaseDaoImpl {
	
	
	public List<Map> queryAccountLogList(Map<String, String> map,PageInfo pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_ACCOUNTLOG WHERE 1=1 ");
		sqlQuery.addLikeParam("SITE_NAME", map.get("SITE_NAME"));
		sqlQuery.addEquealParam(map, "LOG_TYPE","GATHER_STATE","REMARK");
		sqlQuery.addDateRange(map, "DUE_TIME");
		sqlQuery.addSQL("order by due_time");
		List<Map> list= this.querySql(sqlQuery,pageInfo);
		return list;
	}
	/**
	 * 提前还款时删除该标的剩余待还款记录
	 * @param bid
	 * @param repaymentTime
	 * @return
	 */
	public int deleteRepaymentBid(String bid,Date repaymentTime){
		SQLQuery sqlQuery=new SQLQuery("delete FROM TB_P2P_ACCOUNTLOG WHERE 1=1 ");
		sqlQuery.addSQL(" and REMARK = ?",bid);
		String repaymentTimeStr=DateUtil.dateToShortString(repaymentTime);
		sqlQuery.addSQL(" and DUE_TIME >= ?",repaymentTimeStr+" 23:59:59");
		int i=this.execSql(sqlQuery);
		return i;
	}

	/**
	 * 查询投标是否还有未还款的记录
	 */
	public int queryNoRepaymentByBid(String bidId) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_P2P_ACCOUNTLOG WHERE 1=1 ");
		sqlQuery.addSQL(" and GATHER_STATE=0 and REMARK=?",bidId);
		List<Map> list= this.querySql(sqlQuery);
		return list.size();
	}
}

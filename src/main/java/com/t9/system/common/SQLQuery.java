package com.t9.system.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.t9.system.util.LogUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.PageInfo;
/**
 * 
 * 功能描述：SQLQuery工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/02/2013 
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class SQLQuery {
	private StringBuilder baseSQL=new StringBuilder();
	private PageInfo pageInfo;
	private ArrayList list=new ArrayList();
	private boolean countSql=false;
	public SQLQuery(String baseSQL,String... param){
		this.baseSQL.append(baseSQL);	
		for (int i = 0; i < param.length; i++) {
			list.add(param[i]);
		}
	}
	
	public SQLQuery addSQL(String sql,String... param){
		baseSQL.append(sql);
		for (int i = 0; i < param.length; i++) {
			list.add(param[i]);
		}
		return this;
	}
	/**
	 * 增加相等变量
	 * @param column 列名
	 * @param value 值
	 */
	public SQLQuery addEquealParam(String column,String value){
		return addEquealParam(column,value,false);
	}
	/**
	 * 增加相等变量
	 * @param column 列名
	 * @param value  值
	 * @param empty 为空时是否也增加该变量条件
	 */
	public SQLQuery addEquealParam(String column,String value,boolean empty){
		if(empty||StringUtil.isNotEmpty(value)){
			baseSQL.append(" and ").append(column).append(" = ").append("?");
			list.add(value);
		}
		return this;
	}
	
	public SQLQuery addEquealParam(Map map,String...columns){
		for (int i = 0; i < columns.length; i++) {
			String column=columns[i];
			if(StringUtil.isNotEmpty((String)map.get(column))){
				baseSQL.append(" and ").append(column).append(" = ?");
				list.add(map.get(column));
			}
		}
		return this;
	}	

	public void addDateRange(Map map,String... columns){
		for (int i = 0; i < columns.length; i++) {
			String column=columns[i];
			if(StringUtil.isNotEmpty((String)map.get(column+"_START"))){
				String startTime=(String)map.get(column+"_START");
				if(startTime.length()==10){
					startTime+=" 00:00:00";
				}
				baseSQL.append(" and ").append(column).append(" >=?");
				list.add(startTime);
			}
			if(StringUtil.isNotEmpty((String)map.get(column+"_END"))){
				String endTime=(String)map.get(column+"_END");
				if(endTime.length()==10){
					endTime+=" 23:59:59";
				}
				baseSQL.append(" and ").append(column).append(" <=?");
				list.add(endTime);
			}
		}
	}
	
	public void addLikeParam(String column,String value){
		addLikeParam(column,value,false);
	}

	public void addLikeParam(String column,String value,boolean empty){
		if(empty||StringUtil.isNotEmpty(value)){
			baseSQL.append(" and ").append(column).append(" like ").append("?");
			list.add(value+"%");
		}
	}
	
	@Override
	public String toString() {
		if(list.size()==0)
			return baseSQL.toString();
		return "baseSQL={" + baseSQL + "}, params="+list.toString();
	}
	
	@Autowired
	HibernateDao hibernateDao;
	/**
	 * 普通查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> querySql() {
		LogUtil.logInfoPath();
		List<Map> tempList=hibernateDao.querySql(this);
		return tempList;
	}
	
	/**
	 * 执行更新删除
	 * 
	 * @param sql
	 * @return
	 */
	public int execSql() {
		LogUtil.logInfoPath();
		return hibernateDao.execSql(this);
	}
	
	
	String getSql(){
		if(pageInfo!=null){
			String sql="select * from ("+baseSQL.toString()+") t limit ?,?";
			list.add(pageInfo.getFirstNum());
			list.add(pageInfo.getMaxNum());
			return sql;
		}
		if(countSql){
			return "select count(*) COUNT from ("+baseSQL.toString()+") t ";
		}
		return baseSQL.toString();
	}
	
	Object[] getParams(){
		return list.toArray();
	}
	
	void setPageInfo(PageInfo pageInfo){
		this.pageInfo=pageInfo;
	}	

	 void setCountSql(boolean countSql){
		this.countSql=countSql;
	}

}

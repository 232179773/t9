package com.t9.system.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.common.SQLQuery;
import com.t9.system.dao.FastDao;
import com.t9.system.dao.FastFileDaoImpl;
import com.t9.system.util.DateUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.RequestContext;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9RuningTimeException;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastService{

	@Autowired
	private FastDao fastDao;

	@Autowired
	private FastFileDaoImpl fastFileDaoImpl;

	@Autowired
	private DataCache dateCache;
	/**
	 * 表名查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=dateCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamsMap();
		
		HashMap tableMap=dateCache.getTableMap(tableCode);
		List<Map> list= fastDao.queryList(map,tableMap);
		
		return list;
	}
	
	/**
	 * 表名查询
	 * @param Map
	 * @return QueryResult
	 */
	public Map queryById() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=dateCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamsMap();
		
		HashMap tableMap=dateCache.getTableMap(tableCode);
		Map resultMap= fastDao.queryById(map,tableMap);
		return resultMap;
	}
	
	/**
	 * 唯一索引效验
	 * @param map
	 */
	public void checkUniqueIndex(String tableCode,Map map){
		HashMap tableMap=dateCache.getTableMap(tableCode);
		String UNIQUE_COL=(String)tableMap.get("UNIQUE_COL");
		if(StringUtil.isEmpty(UNIQUE_COL)){
			return;
		}
		String[] uniqueIndexCols=UNIQUE_COL.split(";");
		for (int i = 0; i < uniqueIndexCols.length; i++) {
			String[] uIndexCols=uniqueIndexCols[i].split("#");
			System.out.println(uIndexCols.length);
			SQLQuery sqlQuery=new SQLQuery("select * from "+tableCode+" where 1=1 ");
			for (int j = 0; j < uIndexCols.length; j++) {
				sqlQuery.addEquealParam(uIndexCols[j], (String)map.get(uIndexCols[j]),true);
			}
			sqlQuery.addSQL(" and ID !=?", (String)map.get("ID"));
			List list=fastDao.querySql(sqlQuery);
			if(list.size()>0){
				throw new T9RuningTimeException(Arrays.toString(uIndexCols)+" 重复!");
			}
		}
	}
	public T9Result saveEntity() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=dateCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamsMap();
		
		checkUniqueIndex(tableCode,map);
		HashMap tableMap=dateCache.getTableMap(tableCode);
//		MongoUtil.saveEntity(map, tableMap);
		List tableColumnList=(List)tableMap.get("columnList");
		StringBuilder stringBuilder=new StringBuilder();
		StringBuilder paramBuilder=new StringBuilder();
		String[] params=new String[tableColumnList.size()];
		for (int i = 0; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);
			String COL_TYPE=(String)columnHashMap.get("COL_TYPE");
			String DATA_SOURCE=(String)columnHashMap.get("DATA_SOURCE");
			stringBuilder.append(columnHashMap.get("COL_CODE")).append(",");
			paramBuilder.append("?").append(",");
			
			String paramValue=(String)map.get(columnHashMap.get("COL_CODE"));
			if(i == 0){
				paramValue=""+System.currentTimeMillis();
			}
			if("sysdate".equals(DATA_SOURCE)){
				if(StringUtil.isEmpty(params[i])){
					params[i]=DateUtil.dateToString(new Date());
				}
			}else if("variable".equals(DATA_SOURCE)){
				params[i]=request.getSession().getAttribute((String)columnHashMap.get("DATA_SOURCE_SRC")).toString();
			}else if(("date".equals(COL_TYPE)||"datetime".equals(COL_TYPE))&&StringUtil.isEmpty(paramValue)){
				params[i]=null;
			}else{
				params[i]=paramValue;
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		paramBuilder.deleteCharAt(paramBuilder.length()-1);
		SQLQuery sqlQuery=new SQLQuery("insert into "+tableCode+"(");
		sqlQuery.addSQL(stringBuilder.toString()).addSQL(")values(")
			.addSQL(paramBuilder.toString()).addSQL(")",params);
		int i=fastDao.execSql(sqlQuery);
		T9Result result=new T9Result(map);
		return result;
	}
	

	public T9Result updateEntity() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=dateCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamsMap();
		
		checkUniqueIndex(tableCode,map);
		HashMap tableMap=dateCache.getTableMap(tableCode);
		List tableColumnList=(List)tableMap.get("columnList");

		SQLQuery sqlQuery=new SQLQuery("update "+tableCode+" set ");
		for (int i =1; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
			
			String splitChat=",";
			if(i==tableColumnList.size()-1){
				splitChat=" ";
			}
			String paramValue=(String)map.get(columnHashMap.get("COL_CODE"));

			String COL_TYPE=(String)columnHashMap.get("COL_TYPE");
			if(("date".equals(COL_TYPE)||"datetime".equals(COL_TYPE))&&StringUtil.isEmpty(paramValue)){
				paramValue=null;
			}
			
			sqlQuery.addSQL(columnHashMap.get("COL_CODE")+"=?"+splitChat,paramValue);
		}
		sqlQuery.addSQL("where ID = ?",(String)map.get("ID"));
		int i=fastDao.execSql(sqlQuery);
		T9Result result=new T9Result(map);
		return result;
	}
	
	public T9Result deleteEntityByIds() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=dateCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamsMap();
		
		String[] ids=((String)map.get("ID")).split(",");
		SQLQuery sqlQuery=new SQLQuery("delete FROM "+tableCode+" WHERE ID in ("+StringUtil.getInStr(ids)+")");
		int i=fastDao.execSql(sqlQuery);
		T9Result result=new T9Result(map);
		return result;
	}
	

}

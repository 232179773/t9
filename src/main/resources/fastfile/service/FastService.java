package com.arp.system.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arp.system.common.ARPRuningTimeException;
import com.arp.system.common.Expression;
import com.arp.system.common.SQLHelper;
import com.arp.system.dao.FastDao;
import com.arp.system.dao.FastFileDaoImpl;
import com.arp.system.util.DateUtil;
import com.arp.system.util.StringUtil;
import com.arp.system.web.ARPResult;
import com.arp.system.web.RequestContext;

@SuppressWarnings({ "rawtypes"})
@Service
public class FastService{

	@Autowired
	private FastDao fastDao;

	@Autowired
	private FastFileDaoImpl fastFileDaoImpl;

	@Autowired
	FastFileServiceImpl fastFileServiceImpl;
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
		String tableCode=FastFileCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamMap();
		
		HashMap tableMap=FastFileCache.getTableMap(tableCode);
		List<Map> list= fastDao.queryList(map,tableMap);
		
		return list;
	}
	
	/**
	 * 唯一索引效验
	 * @param map
	 */
	public void checkUniqueIndex(String tableCode,Map map){
		HashMap tableMap=FastFileCache.getTableMap(tableCode);
		String UNIQUE_COL=(String)tableMap.get("UNIQUE_COL");
		if(StringUtil.isEmpty(UNIQUE_COL)){
			return;
		}
		String[] uniqueIndexCols=UNIQUE_COL.split(";");
		for (int i = 0; i < uniqueIndexCols.length; i++) {
			String[] uIndexCols=uniqueIndexCols[i].split("#");
			System.out.println(uIndexCols.length);
			SQLHelper sqlHelper=new SQLHelper("select * from "+tableCode+" where 1=1 ");
			for (int j = 0; j < uIndexCols.length; j++) {
				sqlHelper.addCondition(Expression.eq(uIndexCols[j], (String)map.get(uIndexCols[j])));
			}
			sqlHelper.addCondition(Expression.ne("ID", (String)map.get("ID")));
			List list=sqlHelper.querySql();
			if(list.size()>0){
				throw new ARPRuningTimeException(Arrays.toString(uIndexCols)+" 重复!");
			}
		}
	}
	public ARPResult saveEntity() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=FastFileCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamMap();
		
		checkUniqueIndex(tableCode,map);
		HashMap tableMap=FastFileCache.getTableMap(tableCode);
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
			if("int".equals(COL_TYPE)){
				if(StringUtil.isEmpty(paramValue)){
					params[i]=null;
				}
			}else if("sysdate".equals(DATA_SOURCE)){
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
		SQLHelper sqlHelper=new SQLHelper("insert into "+tableCode+"(");
		sqlHelper.addCondition(Expression.sql(stringBuilder.toString()+")values("+
				paramBuilder.toString()+")", params));
		int i=sqlHelper.execSql();
		ARPResult result=new ARPResult(map);
		return result;
	}
	

	public ARPResult updateEntity() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=FastFileCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamMap();
		
		checkUniqueIndex(tableCode,map);
		HashMap tableMap=FastFileCache.getTableMap(tableCode);
		List tableColumnList=(List)tableMap.get("columnList");

		SQLHelper sqlHelper=new SQLHelper("update "+tableCode+" set ");
		for (int i =1; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
			
			String splitChat=",";
			if(i==tableColumnList.size()-1){
				splitChat=" ";
			}
			String paramValue=(String)map.get(columnHashMap.get("COL_CODE"));

			String COL_TYPE=(String)columnHashMap.get("COL_TYPE");
			if("int".equals(COL_TYPE)){
				if(StringUtil.isEmpty(paramValue)){
					paramValue=null;
				}
			}else if(("date".equals(COL_TYPE)||"datetime".equals(COL_TYPE))&&StringUtil.isEmpty(paramValue)){
				paramValue=null;
			}
			
			sqlHelper.addCondition(Expression.sql(columnHashMap.get("COL_CODE")+"=?"+splitChat, paramValue));
		}
		sqlHelper.addCondition(Expression.sql("where ID = ?",(String)map.get("ID")));
		int i=sqlHelper.execSql();
		ARPResult result=new ARPResult(map);
		return result;
	}
	
	public ARPResult deleteEntityByIds() {
		HttpServletRequest request=RequestContext.getContext().getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		String[] vs=pathInfo.split("!");
		String tableCode=FastFileCache.appPathToTableName(vs[0]);
		Map map = RequestContext.getContext().getParamMap();
		
		String[] ids=((String)map.get("ID")).split(",");
		SQLHelper sqlHelper=new SQLHelper("delete FROM "+tableCode+" WHERE ID in ("+StringUtil.getInStr(ids)+")");
		int i=sqlHelper.execSql();
		ARPResult result=new ARPResult(map);
		return result;
	}
	

}

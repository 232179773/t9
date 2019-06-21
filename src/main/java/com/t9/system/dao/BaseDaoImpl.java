package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.t9.system.common.HibernateDao;
import com.t9.system.common.SQLQuery;
import com.t9.system.entity.BaseDomain;
import com.t9.system.util.LogUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.PageInfo;
import com.t9.system.web.T9RuningTimeException;

/**
 * 
 * 功能描述：dao基类
 *
 * @author husq
 * @since 1.0
 * create on: 7/1/2013 
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public abstract class BaseDaoImpl  implements BaseDao {

	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	@Autowired
	HibernateDao hibernateDao;
	
	public Class getDomainClass(){
		Package p=getClass().getPackage();
		String domain=null;
		if(getClass().getSimpleName().endsWith("DaoImpl")){
			domain=StringUtil.capitalize(StringUtil.substringBefore(getClass().getSimpleName(), "DaoImpl"));
		}else if(getClass().getSimpleName().endsWith("Dao")){
			domain=StringUtil.capitalize(StringUtil.substringBefore(getClass().getSimpleName(), "Dao"));
		}
		
		Class classZ=null;
		try {
			String name=StringUtil.substringBefore(p.getName(), "dao");
			classZ = Class.forName(name+"entity." + domain);
		} catch (ClassNotFoundException e) {
	//		e.printStackTrace();
			throw new T9RuningTimeException("ClassNotFoundException",domain);
		}		
		return classZ;
	}

	public Object get(String id){
		LogUtil.logInfoPath();
		Class classz=getDomainClass();
		return (BaseDomain)hibernateDao.get(classz, id);
	}
		
	public void save(BaseDomain... pojos) {
		LogUtil.logInfoPath();
		log.info("save object:" + pojos[0].getClass().getName());
		try {
			for(BaseDomain pojo:pojos){
				pojo.setID(""+System.currentTimeMillis());
				hibernateDao.save(pojo);
			}
		} catch (RuntimeException e) {
			throw new T9RuningTimeException("system.database.error",e);
		}
	}
	

	public void update(BaseDomain... pojos) {
		LogUtil.logInfoPath();
		log.info("save object:" + pojos[0].getClass().getName());
		try {
			for(BaseDomain pojo:pojos){
				hibernateDao.update(pojo);
			}
		} catch (RuntimeException e) {
			throw new T9RuningTimeException("system.database.error",e);
		}
	}
	
	public int delete(String... ids) {		
		String hql=null;
		if(ids.length>1)
			hql="delete from "+getDomainClass().getName()+ " where ID in ("+StringUtil.getInStr(ids)+")";
		else
			hql="delete from "+getDomainClass().getName()+ " where ID ="+StringUtil.getInStr(ids);
		log.info("delete hql:"+hql);
		return hibernateDao.bulkUpdate(hql);
	}
	
	/**
	 * 普通查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> querySql(SQLQuery sqlQuery) {
		LogUtil.logInfoPath();
		List<Map> tempList=hibernateDao.querySql(sqlQuery);
		return tempList;
	}
	
	/**
	 * 普通查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> querySql(SQLQuery sqlQuery,PageInfo pageInfo) {
		LogUtil.logInfoPath();
		if(pageInfo==null){
			return hibernateDao.querySql(sqlQuery);
		}else{
			return hibernateDao.queryPageSql(sqlQuery);
		}
	}
	
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map>  queryPageSql(SQLQuery sqlQuery) {
		LogUtil.logInfoPath();
		return hibernateDao.queryPageSql(sqlQuery);
	}

	
	/**
	 * 执行更新删除
	 * 
	 * @param sql
	 * @return
	 */
	public int execSql(SQLQuery sqlQuery) {
		LogUtil.logInfoPath();
		return hibernateDao.execSql(sqlQuery);
	}
	
}

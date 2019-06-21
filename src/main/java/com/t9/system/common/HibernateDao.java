package com.t9.system.common;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.t9.system.entity.BaseDomain;
import com.t9.system.trans.HibernateHolder;
import com.t9.system.web.PageInfo;
import com.t9.system.web.RequestContext;
import com.t9.system.web.T9RuningTimeException;

/**
 * 
 * 功能描述：HibernateDao工具类
 *
 * @author husq
 * @since 1.0
 * create on: 7/1/2013 
 *
 */
@SuppressWarnings({"unchecked","rawtypes","deprecation"})
@Component
public class HibernateDao {

	private static final Logger log = Logger.getLogger(HibernateDao.class);

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	private HibernateTemplate getHibernateTemplate(){
		return hibernateTemplate;
	}
	
//	@Resource(name="hibernateHolder")
//	private HibernateHolder holder;

	
	public BaseDomain get(Class domainClass,String id){
		return (BaseDomain)getHibernateTemplate().get(domainClass, id);
	}
		

	public void update(BaseDomain... pojos) {
		try {
			for(BaseDomain pojo:pojos){
				getHibernateTemplate().update(pojo);
			}
			getHibernateTemplate().flush();
		} catch (RuntimeException e) {
			log.error("error", e);
			throw new T9RuningTimeException("system.database.error",e);
		}
	}
	
	public void save(BaseDomain... pojos) {
		try {
			for(BaseDomain pojo:pojos){
					getHibernateTemplate().save(pojo);
			}
			getHibernateTemplate().flush();
		} catch (RuntimeException e) {
			log.error("error", e);
			throw new T9RuningTimeException("system.database.error",e);
		}
	}
	
	public int bulkUpdate(String hql) {
		return getHibernateTemplate().bulkUpdate(hql);
	}
	
	/**
	 * 普通查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> querySql(SQLQuery sqlQuery) {
		log.info("exec sql:" + sqlQuery.toString());
		try {
			SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Connection  connection=session.connection();
		//	Connection  connection=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		//	ConnectionProvider cp = ((SessionFactoryImplementor)this.getHibernateTemplate().getSessionFactory()).getConnectionProvider();
		//	Connection  connection = cp.getConnection();
			return DataBaseUtil.querySql(connection, sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
			throw new T9RuningTimeException("system.database.error",e);
		}
	}

	
	
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> queryPageSql(SQLQuery sqlQuery) {
		log.info("exec sql:" + sqlQuery.toString());
		RequestContext requestContext=RequestContext.getContext();
		PageInfo pageInfo=null;
		//测试方法调用时，不会有requestContext
		if(requestContext==null){
			pageInfo=new PageInfo(1,10);
		}else{
			pageInfo=requestContext.getPageInfo();
		}
		if(pageInfo==null)
			throw new T9RuningTimeException("database。error.pageinfo");
		List<Map> tempList=null;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
			Connection  connection=session.connection();
		//	Connection  connection=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		//	ConnectionProvider cp = ((SessionFactoryImplementor)this.getHibernateTemplate().getSessionFactory()).getConnectionProvider();
		//	Connection  connection = cp.getConnection();

			sqlQuery.setCountSql(true);
			List<Map> countList=DataBaseUtil.querySql(connection, sqlQuery);
			Long count=Long.parseLong((String)((HashMap)countList.get(0)).get("COUNT"));
			pageInfo.setCount(count);
			
			sqlQuery.setPageInfo(pageInfo);
			tempList=DataBaseUtil.querySql(connection, sqlQuery);
		} catch (Exception e) {
			throw new T9RuningTimeException("system.database.error",e);
		}
		return tempList;
	}


	/**
	 * 执行更新删除
	 * 
	 * @param sql
	 * @return
	 */
	public int execSql(SQLQuery sqlQuery) {
		log.info("exec sql:" + sqlQuery.toString());
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
			Connection  connection=session.connection();
			return DataBaseUtil.execSql(connection, sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
			throw new T9RuningTimeException("system.database.error",e);
		}
	}
}

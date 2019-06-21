package com.t9.system.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * xml的配置项目
 * @author sky
 *
 */
public class XmlConfig {
		
	//特别添加一个当前时间的毫秒数
	private String boottime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date(System.currentTimeMillis()));
		
	/**
	 * 表和数据源的对应关系
	 */
	private Map  table2DataSourceMap;
	

	public Map getTable2DataSourceMap() {
		return table2DataSourceMap;
	}

	public void setTable2DataSourceMap(Map table2DataSourceMaps) {
		this.table2DataSourceMap = table2DataSourceMaps;
	}

	public String getBoottime() {
		return boottime;
	}

	public void setBoottime(String boottime) {
		this.boottime = boottime;
	}
	
	
	public boolean isMysql(){
		return true;
	}
	
	private String jdbc_xa_driver_key;
	private String hibernateDialect;


	public String getJdbc_xa_driver_key() {
		if(isMysql()){
			return "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource";
		}
		return "oracle.jdbc.xa.client.OracleXADataSource";
	}

	public String getHibernateDialect() {
		if(isMysql()){
			return "org.hibernate.dialect.MySQLInnoDBDialect";
		}
		return "org.hibernate.dialect.OracleDialect";
	}
	
	
	

}

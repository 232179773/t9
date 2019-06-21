package com.t9.system.common;

import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.t9.system.entity.BaseDomain;
import com.t9.system.util.BeanUtil;
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
public class MongoUtil {

	
	private String collectionName; 
	public MongoUtil MongoUtil(String collectionName){
		this.collectionName=collectionName;
		return this;
	}
	
	private BasicDBObject cond=new BasicDBObject();
	public MongoUtil addEqualParam(String column,String value){
		cond.put(column, value);
		return this;
	}
	public MongoUtil addLikeParam(String column,String regex){
		Pattern pattern=Pattern.compile(""+"mainPage"+"");  
		cond.put("LOG_TITLE", pattern);
		return this;
	}
	public List executeQuery(){
		try {
			Mongo mg = new Mongo();
			DB db = mg.getDB("temp");
			// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
			DBCollection collection = db.getCollection(collectionName);
			return collection.find(cond).toArray();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveEntity(Map<String, String> dataMap,HashMap tableMap){
		DBObject obj = new BasicDBObject();
		List tableColumnList=(List)tableMap.get("columnList");
		String tableCode=(String)tableMap.get("TABLE_CODE");
		for (int i = 0; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
			String colCode=(String)columnHashMap.get("COL_CODE");
			String value=dataMap.get(colCode);
			obj.put(colCode, value);
		}
		try {
			Mongo mg = new Mongo();
			DB db = mg.getDB("temp");
			// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
			DBCollection collection = db.getCollection(tableCode);
			collection.save(obj);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveEntity(BaseDomain baseDomain){
		DBObject obj = new BasicDBObject();
		try {
			BeanUtils.copyProperties(obj, baseDomain);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			Mongo mg = new Mongo();
			DB db = mg.getDB("temp");
			Table table=baseDomain.getClass().getAnnotation(Table.class);
			
			String collectionName=table.name();
			// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
			DBCollection collection = db.getCollection(collectionName);
			collection.save(obj);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void updateEntity(BaseDomain baseDomain){
		DBObject obj = new BasicDBObject();
		try {
			BeanUtils.copyProperties(obj, baseDomain);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			Mongo mg = new Mongo();
			DB db = mg.getDB("temp");
			Table table=baseDomain.getClass().getAnnotation(Table.class);
			
			String collectionName=table.name();
			// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
			DBCollection collection = db.getCollection(collectionName);
			collection.save(obj);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

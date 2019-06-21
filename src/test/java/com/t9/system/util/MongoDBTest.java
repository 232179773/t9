package com.t9.system.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class MongoDBTest {

	private Mongo mg = null;
	private DB db;
	private DBCollection users;

	@Before
	public void init() {
		try {
			//				mg = new Mongo();
				mg = new MongoClient("10.0.2.44", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		// 获取temp DB；如果默认没有创建，mongodb会自动创建
		db = mg.getDB("temp");
		// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
		users = db.getCollection("users");
	}

	/**
	 * <b>function:</b> 查询所有数据
	 * @author hoojo
	 * @createDate 2011-6-2 下午03:22:40
	 */
	private void queryAll() {
	    print("查询users的所有数据：");
	    //db游标
	    DBCursor cur = users.find();
	    while (cur.hasNext()) {
	        print(cur.next());
	    }
	}

	@Test
	public void test(){
		
		try {
			DBCollection log = db.getCollection("log");
			long count=log.count();
//			DBCursor cur = log.find();
//		    while (cur.hasNext()) {
//		        print(cur.next());
//		    }
			System.out.println(count);
			

	//	    print("find LOG_CONTENT = ID=1414025643171&: " + log.find(new BasicDBObject("LOG_TITLE", "mainPage")).toArray());
		    BasicDBObject cond=new BasicDBObject();
		    Pattern pattern=Pattern.compile(""+"mainPage"+"");  
		    cond.put("LOG_TITLE", pattern);
		    cond.put("ID", new BasicDBObject("$gte", "1414044850011"));
		    print("find ID >= 1414037807167: " + log.find(cond).toArray());
	//	    print("find LOG_CONTENT = ID=1414025643171&: " + log.find(new BasicDBObject("ID", "ID=1414025643171&")).toArray());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void add() {
	    //先查询所有数据
	    queryAll();
	    print("count: " + users.count());
	    
	    DBObject user = new BasicDBObject();
	    user.put("name", "hoojo");
	    user.put("age", 24);
	    //users.save(user)保存，getN()获取影响行数
	    //print(users.save(user).getN());
	    
	    //扩展字段，随意添加字段，不影响现有数据
	    user.put("sex", "男");
	    print(users.save(user).getN());
	    
	    //添加多条数据，传递Array对象
	    print(users.insert(new BasicDBObject("name", "tom")).getN());
	    
	    //添加List集合
	    List<DBObject> list = new ArrayList<DBObject>();
	    list.add(user);
	    DBObject user2 = new BasicDBObject("name", "lucy");
	    user.put("age", 22);
	    list.add(user2);
	    //添加List集合
	//    print(users.insert(list).getN());
	    
	    //查询下数据，看看是否添加成功
	    print("count: " + users.count());
	    queryAll();
	}
	
	@Test
	public void modify() {
	    print("修改：" + users.update(new BasicDBObject("_id", new ObjectId("4dde25d06be7c53ffbd70906")), new BasicDBObject("age", 99)).getN());
	    print("修改：" + users.update(
	            new BasicDBObject("_id", new ObjectId("4dde2b06feb038463ff09042")), 
	            new BasicDBObject("age", 121),
	            true,//如果数据库不存在，是否添加
	            false//多条修改
	            ).getN());
//	    print("修改：" + users.update(
//	            new BasicDBObject("name", "haha"), 
//	            new BasicDBObject("name", "dingding"),
//	            true,//如果数据库不存在，是否添加
//	            true//false只修改第一天，true如果有多条就不修改
//	            ).getN());
	    
	    //当数据库不存在就不修改、不添加数据，当多条数据就不修改
	    //print("修改多条：" + coll.updateMulti(new BasicDBObject("_id", new ObjectId("4dde23616be7c19df07db42c")), new BasicDBObject("name", "199")));
	}
	
	public void testOthers() {
	    DBObject user = new BasicDBObject();
	    user.put("name", "hoojo");
	    user.put("age", 24);
	    
	    //JSON 对象转换        
	    print("serialize: " + JSON.serialize(user));
	    //反序列化
	    print("parse: " + JSON.parse("{ \"name\" : \"hoojo\" , \"age\" : 24}"));
	    
	    print("判断temp Collection是否存在: " + db.collectionExists("temp"));
	    
	    //如果不存在就创建
	    if (!db.collectionExists("temp")) {
	        DBObject options = new BasicDBObject();
	        options.put("size", 20);
	        options.put("capped", 20);
	        options.put("max", 20);
	        print(db.createCollection("account", options));
	    }
	    
	    //设置db为只读
	    db.setReadOnly(true);
	    
	    //只读不能写入数据
	    db.getCollection("test").save(user);
	}
	
	@Test
	public void query() {
	    //查询所有
	    //queryAll();
	    
	    //查询id = 4de73f7acd812d61b4626a77
	    print("find id = 4de73f7acd812d61b4626a77: " + users.find(new BasicDBObject("_id", new ObjectId("4de73f7acd812d61b4626a77"))).toArray());
	    
	    //查询age = 24
	    print("find age = 24: " + users.find(new BasicDBObject("age", 24)).toArray());
	    
	    DBObject dbObject=new BasicDBObject("age", new BasicDBObject("$gt", 28));
	    print("find age > 28: " + users.find(dbObject).count());
	    DBObject o=new BasicDBObject("age", new BasicDBObject("$lt", 29));
	    //查询age >= 24

	    print("find age <29: " + o.toString());
	    print("find age <29: " + users.find(o).count());
	    print("find age > 24: " + users.find(dbObject).count());
//	    print("find age < 29: " + users.find(new BasicDBObject("age", new BasicDBObject("$lt", 29))).toArray());
//	    
//	    print("查询age!=25：" + users.find(new BasicDBObject("age", new BasicDBObject("$ne", 25))).toArray());
//	    print("查询age in 25/26/27：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.IN, new int[] { 25, 26, 27 }))).toArray());
//	    print("查询age not in 25/26/27：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.NIN, new int[] { 25, 26, 27 }))).toArray());
//	    print("查询age exists 排序：" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.EXISTS, true))).toArray());
//	    
//	    print("只查询age属性：" + users.find(null, new BasicDBObject("age", true)).toArray());
//	    print("只查属性：" + users.find(null, new BasicDBObject("age", true), 0, 2).toArray());
//	    print("只查属性：" + users.find(null, new BasicDBObject("age", true), 0, 2, Bytes.QUERYOPTION_NOTIMEOUT).toArray());
//	    
//	    //只查询一条数据，多条去第一条
//	    print("findOne: " + users.findOne());
//	    print("findOne: " + users.findOne(new BasicDBObject("age", 26)));
//	    print("findOne: " + users.findOne(new BasicDBObject("age", 26), new BasicDBObject("name", true)));
//	    
//	    //查询修改、删除
//	    print("findAndRemove 查询age=25的数据，并且删除: " + users.findAndRemove(new BasicDBObject("age", 25)));
//	    
//	    //查询age=26的数据，并且修改name的值为Abc
//	    print("findAndModify: " + users.findAndModify(new BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));
//	    print("findAndModify: " + users.findAndModify(
//	        new BasicDBObject("age", 28), //查询age=28的数据
//	        new BasicDBObject("name", true), //查询name属性
//	        new BasicDBObject("name", true), //按照age排序
//	        false, //是否删除，true表示删除
//	        new BasicDBObject("name", "Abc"), //修改的值，将name修改成Abc
//	        true, 
//	        true));
	    
	    queryAll();
	}
	
	@After
	public void destory() {
		if (mg != null)
			mg.close();
		mg = null;
		db = null;
		users = null;
		System.gc();
	}

	public void print(Object o) {
		System.out.println(o);
	}
}

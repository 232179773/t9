package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.dao.DemoDao;
import com.t9.system.entity.Demo;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Result;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	private DemoDao demoDao;

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map) {
		return demoDao.queryList(map);
	}
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Demo
	 */
	public Demo queryDemoById(String id) {
		return (Demo)demoDao.get(id);
	}
	/**
	 * 保存数据
	 * @param demo
	 * @return
	 */
	public T9Result saveDemo(Demo demo) {
		T9Result result=new T9Result();
		demoDao.save(demo);
		return result;
	}
	

	/**
	 * 更新数据
	 * @param demo
	 * @return
	 */
	public T9Result updateDemo(Demo demo){
		T9Result result=new T9Result();
		demoDao.update(demo);
		return result;
	}

	public T9Result deleteDemo(String... ids) {
		T9Result result=new T9Result();
		demoDao.delete(ids);
		return result;
	}


}

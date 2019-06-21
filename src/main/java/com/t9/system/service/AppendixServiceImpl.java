package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.AppendixDao;
import com.t9.system.entity.Appendix;
import com.t9.system.web.T9Result;

@Service
public class AppendixServiceImpl implements AppendixService{

	@Autowired
	private AppendixDao appendixDao;

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map) {
		return appendixDao.queryList(map);
	}
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Appendix
	 */
	public Appendix queryAppendixById(String id) {
		return (Appendix)appendixDao.get(id);
	}
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 */
	public T9Result saveAppendix(Appendix appendix) {
		appendixDao.save(appendix);
		T9Result result=new T9Result(appendix);
		return result;
	}
	
	/**
	 * 更新数据
	 * @param appendix
	 * @return
	 */
	@Override
	public T9Result updateAppendix(String[] fileIds,String tableName,String tableId){
		return appendixDao.updateAppendix(fileIds, tableName, tableId);
	}

	/**
	 * 更新数据
	 * @param appendix
	 * @return
	 */
	public T9Result updateAppendix(Appendix appendix){
		T9Result result=new T9Result();
		appendixDao.update(appendix);
		return result;
	}

	public T9Result deleteAppendix(String... ids) {
		T9Result result=new T9Result();
		appendixDao.delete(ids);
		return result;
	}


}

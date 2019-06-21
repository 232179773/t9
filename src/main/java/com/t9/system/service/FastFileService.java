package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Appendix;
import com.t9.system.web.T9Result;

public interface FastFileService {

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map);

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Appendix
	 */
	public Appendix queryAppendixById(String id);

	/**
	 * 保存数据
	 * @param Appendix
	 * @return
	 */
	public T9Result saveAppendix(Appendix appendix);

	
	/**
	 * 更新数据
	 * @param Appendix
	 * @return
	 */
	public T9Result updateAppendix(Appendix appendix);
	
	public T9Result deleteAppendix(String... ids);

	public abstract T9Result updateAppendix(String[] fileIds, String tableName, String tableId);

}
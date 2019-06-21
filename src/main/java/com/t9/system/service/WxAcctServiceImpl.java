package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.WxAcctDaoImpl;
import com.t9.system.entity.WxAcct;
import com.t9.system.web.T9Result;

@Service
public class WxAcctServiceImpl{

	@Autowired
	private WxAcctDaoImpl wxAcctDaoImpl;
	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map) {
		return wxAcctDaoImpl.queryList(map);
	}
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 */
	public T9Result saveWxAcct(WxAcct wxAcct) {
		wxAcctDaoImpl.save(wxAcct);
		T9Result result=new T9Result(wxAcct);
		return result;
	}
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return RenRenLoan
	 */
	public WxAcct queryLoanById(String id) {
		return (WxAcct)wxAcctDaoImpl.get(id);
	}


}

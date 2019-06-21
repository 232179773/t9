package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.RenRenLoanDaoImpl;
import com.t9.system.entity.RenRenLoan;
import com.t9.system.web.T9Result;

@Service
public class RenRenLoanServiceImpl{

	@Autowired
	private RenRenLoanDaoImpl loanDaoImpl;
	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map) {
		return loanDaoImpl.queryList(map);
	}
	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 */
	public T9Result saveLoan(RenRenLoan loan) {
		loanDaoImpl.save(loan);
		T9Result result=new T9Result(loan);
		return result;
	}
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return RenRenLoan
	 */
	public RenRenLoan queryLoanById(String id) {
		return (RenRenLoan)loanDaoImpl.get(id);
	}


}

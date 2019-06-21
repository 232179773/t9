package com.t9.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.RenRenStatDaoImpl;
import com.t9.system.entity.RenRenStat;
import com.t9.system.web.T9Result;

@Service
public class RenRenStatServiceImpl{

	@Autowired
	private RenRenStatDaoImpl statDaoImpl;

	/**
	 * 保存数据
	 * @param appendix
	 * @return
	 */
	public T9Result saveStat(RenRenStat stat) {
		statDaoImpl.save(stat);
		T9Result result=new T9Result(stat);
		return result;
	}
	


}

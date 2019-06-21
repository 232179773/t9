package com.t9.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.p2p.dao.AccountDao;
import com.t9.p2p.entity.Account;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.PageInfo;
import com.t9.system.web.T9Result;

@Service
public class AccountService{
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private SystemUserService systemUserService;
	
	
	public List<Map> queryAccountList(Map<String, String> map,PageInfo pageInfo) {
		return accountDao.queryAccountList(map,pageInfo);
	}

	public T9Result saveAccount(Account account) {
		T9Result result=new T9Result();
		
		accountDao.save(account);
		return result;
	}

	public T9Result updateAccount(Account account) {
		T9Result result=new T9Result();
		accountDao.update(account);
		return result;
	}

	public T9Result deleteAccount(String[] ids) {
		T9Result result=new T9Result();
		accountDao.delete(ids);
		return result;
	}

	public Account queryAccountById(String id) {
		return (Account)accountDao.get(id);

	}
}

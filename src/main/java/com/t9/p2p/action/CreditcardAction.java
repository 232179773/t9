package com.t9.p2p.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.p2p.entity.Account;
import com.t9.p2p.service.AccountService;
import com.t9.system.action.BaseAction;
import com.t9.system.service.SystemUserService;
import com.t9.system.web.PageInfo;
import com.t9.system.web.RequestContext;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class CreditcardAction extends BaseAction{
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SystemUserService systemUserService;
	
	private Account account=new Account();
	
	
	public String queryAccountList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		RequestContext requestContext=RequestContext.getContext();
		PageInfo pageInfo=requestContext.getPageInfo();
		List<Map> list=accountService.queryAccountList(map,pageInfo);
		super.renderToOutput(list);
		return null;
		
	}
	
	public String queryAccountById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Account account=accountService.queryAccountById(id);
		T9Result result=new T9Result(account);
		super.renderToOutput(result);
		return null;
	}
	
	public String saveAccount(){
		
		T9Result result=accountService.saveAccount(account);
		super.renderToOutput(result);
		return null;
	}
	
	public String updateAccount() {
		Account accountOld=accountService.queryAccountById(account.getID());
		try {
			BeanUtils.copyProperties(accountOld, account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		T9Result result=accountService.updateAccount(account);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteAccount() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=accountService.deleteAccount(ids);
		super.renderToOutput(result);
		return null;
	}
	
	
	@Override
	public Object getModel() {
		return account;
	}
	
}

package com.t9.p2p.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.p2p.entity.AccountLog;
import com.t9.p2p.service.AccountLogService;
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
public class AccountLogAction extends BaseAction{
	@Autowired
	private AccountLogService accountLogService;
	
	@Autowired
	private SystemUserService systemUserService;
	
	private AccountLog accountLog=new AccountLog();
	
	
	public String queryCalculatorList() {
		accountLog.setDUE_TIME(new Date());
		List<Map> list=accountLogService.queryCalculatorList(accountLog);
		super.renderToOutput(list);
		return null;
	}
	

	public String queryCalculatorCompList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=accountLogService.queryCalculatorCompList(map);
		super.renderToOutput(list);
		return null;
	}
	
	public String queryAccountLogList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		RequestContext requestContext=RequestContext.getContext();
		PageInfo pageInfo=requestContext.getPageInfo();
		List<Map> list=accountLogService.queryAccountLogList(map,pageInfo);
		super.renderToOutput(list);
		return null;
		
	}
	
	public String queryAccountLogById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		AccountLog accountLog=accountLogService.queryAccountLogById(id);
		T9Result result=new T9Result(accountLog);
		super.renderToOutput(result);
		return null;
	}
	
	public String saveAccountLog(){
		T9Result result=accountLogService.saveAccountLog(accountLog);
		super.renderToOutput(result);
		return null;
	}
	
	public String updateAccountLog() {
		T9Result result=accountLogService.updateAccountLog(accountLog);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteAccountLog() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=accountLogService.deleteAccountLog(ids);
		super.renderToOutput(result);
		return null;
	}
	
	
	@Override
	public Object getModel() {
		return accountLog;
	}
	
}

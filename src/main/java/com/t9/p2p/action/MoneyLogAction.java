package com.t9.p2p.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.p2p.entity.MoneyLog;
import com.t9.p2p.service.MoneyLogService;
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
public class MoneyLogAction extends BaseAction{
	@Autowired
	private MoneyLogService moneyLogService;
	
	@Autowired
	private SystemUserService systemUserService;
	
	private MoneyLog moneyLog=new MoneyLog();
	
	
	public String queryMoneyLogList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		RequestContext requestContext=RequestContext.getContext();
		PageInfo pageInfo=requestContext.getPageInfo();
		List<Map> list=moneyLogService.queryMoneyLogList(map,pageInfo);
		super.renderToOutput(list);
		return null;
		
	}
	
	public String queryMoneyLogById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		MoneyLog moneyLog=moneyLogService.queryMoneyLogById(id);
		T9Result result=new T9Result(moneyLog);
		super.renderToOutput(result);
		return null;
	}
	
	public String saveMoneyLog(){
		if("recharge".equals(moneyLog.getLOG_TYPE())){
			moneyLog.setMONEY(0-moneyLog.getMONEY());
		}
		T9Result result=moneyLogService.saveMoneyLog(moneyLog);
		super.renderToOutput(result);
		return null;
	}
	
	public String updateMoneyLog() {
		MoneyLog moneyLogOld=moneyLogService.queryMoneyLogById(moneyLog.getID());
		T9Result result=moneyLogService.updateMoneyLog(moneyLogOld);
		super.renderToOutput(result);
		return null;
	}
	
	public String deleteMoneyLog() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=moneyLogService.deleteMoneyLog(ids);
		super.renderToOutput(result);
		return null;
	}
	
	
	@Override
	public Object getModel() {
		return moneyLog;
	}
	
}

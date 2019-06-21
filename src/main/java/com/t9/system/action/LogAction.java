package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Log;
import com.t9.system.service.LogService;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class LogAction extends BaseAction {

	@Autowired
	private LogService logService;
	private Log log = new Log();
	
	//列表查询
	public String queryLogList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map>  list=logService.queryList(map);
		super.renderToOutput(list);
		return null;
	}
	
	//PV统计
	public String pvStat() throws T9Exception {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map>  list=logService.pvStat(map);
		super.renderToOutput(list);
		return null;
	}
	
	//PV统计
	public String ipStat() throws T9Exception {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map>  list=logService.ipStat(map);
		super.renderToOutput(list);
		return null;
	}
	
	//
	public String queryLogById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Log log=logService.queryLogById(id);
		T9Result result=new T9Result(log);
		super.renderToOutput(result);
		return null;
	}
	
	//
	public String saveLog(){
		T9Result result=logService.saveLog(log);
		super.renderToOutput(result);
		return null;
	}
	
	//
	public String deleteLog() {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String[] ids=((String)map.get("ID")).split(",");
		T9Result result=logService.deleteLog(ids);
		super.renderToOutput(result);
		return null;
	}


	public Object getModel() {
		return log;
	}
	

}

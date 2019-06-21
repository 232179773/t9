package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Log;
import com.t9.system.service.LogService;
import com.t9.system.web.QueryResult;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
@Results(value={@Result(type=org.apache.struts2.views.freemarker.FreemarkerResult.class, value = "/WEB-INF/ftl/{1}.ftl",name="success")})
public class FreeMarkAction extends BaseAction {

	@Autowired
	private LogService logService;
	
	//列表查询
	
	public String queryLogList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map>  list=logService.queryList(map);
		super.renderToOutput(list);
		return null;
	}

	@Override
	public Object getModel() {
		return null;
	}
}

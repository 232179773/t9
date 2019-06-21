package com.t9.p2p.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.p2p.service.PlatService;
import com.t9.system.action.BaseAction;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class PlatAction extends BaseAction{
	@Autowired
	private PlatService platService;
	
	
	public String queryPlatList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=platService.queryPlatList(map);
		super.renderToOutput(list);
		return null;
	}
	

	
	@Override
	public Object getModel() {
		return null;
	}
	
}

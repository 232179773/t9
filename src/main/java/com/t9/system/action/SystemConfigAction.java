package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.SystemConfig;
import com.t9.system.entity.SystemUser;
import com.t9.system.service.SystemConfigService;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class SystemConfigAction extends BaseAction {

	@Autowired
	private SystemConfigService systemConfigService;
	private SystemConfig systemConfig = new SystemConfig();
	
    public void queryConfigByPage() throws T9Exception {
//    	String paramName, PageInfo pageInfo
    	Map map = ServletUtils.getMapByRequest(this.getRequest());
    	
    	SystemUser currentUser = (SystemUser)getSession().getAttribute(ServletUtils.SEESION_USERINFO);
    	List<Map> list=systemConfigService.searchSystemCongfig(map, currentUser.getID());
	    super.renderToOutput(list);
    }
    
    public void getSystemConfigById() {
    	Map map = ServletUtils.getMapByRequest(this.getRequest());
    	String id = (String)map.get("ID");
//		FieldValidater.varifyNotBlank(id, "系统参数ID");

        SystemConfig config=systemConfigService.querySystemConfigById(id);
		T9Result result=new T9Result(config);
		super.renderToOutput(result);
    }
    
    public void updateConfig() {
//    	Result result=systemConfigService.updateSystemConfig(systemConfig);
//		FieldValidater.varifyNotBlank(systemConfig.getID(), "系统参数ID");
//		FieldValidater.varifyNotBlank(systemConfig.getVALUE(), "系统参数值");

    	T9Result result=systemConfigService.updateSystemConfig(systemConfig.getID(), systemConfig.getVALUE());
		super.renderToOutput(result);
    }

	public Object getModel() {
		return systemConfig;
	}
	
}

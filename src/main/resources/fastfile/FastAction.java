package com.arp.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arp.system.common.ARPRuningTimeException;
import com.arp.system.service.FastBaseService;
import com.arp.system.service.FastFileCache;
import com.arp.system.service.FastFileServiceImpl;
import com.arp.system.service.FastService;
import com.arp.system.service.FastServiceInvoke;
import com.arp.system.util.LogUtil;
import com.arp.system.web.ARPResult;
import com.arp.system.web.RequestContext;
import com.arp.system.web.ServletUtil;

@SuppressWarnings({"serial","unused","rawtypes"})
@Component
@Scope("prototype")
@ParentPackage("arpPackage")
public class FastAction extends BaseAction {
	
	@Autowired
	FastService fastService;

	@Autowired
	FastFileServiceImpl fastFileServiceImpl;
	@Override
	public String execute(){
		HttpServletRequest request=getRequest();
		String requestURI=request.getRequestURI();
		String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
		log.info(pathInfo);
		if(pathInfo.indexOf("!")>0){
			String[] vs=pathInfo.split("!");
			
			Map map = ServletUtil.getMapByRequest(this.getRequest());
			String tableCode=FastFileCache.appPathToTableName(vs[0]);
			if(tableCode==null){
				throw new ARPRuningTimeException(pathInfo +" is error !!!");
			}


			String beanName=vs[0]+"Service";
			ApplicationContext applicationContext=RequestContext.getContext().getWebApplicationContext();
			FastBaseService service=(FastBaseService)applicationContext.getBean("fastBaseService");
			if(applicationContext.containsBean(beanName)){
				try {
					service=(FastBaseService)applicationContext.getBean(beanName);						
				} catch (ClassCastException e) {
					log.error(beanName+" is not extends FastBaseService!!!!");
				}
			}
			FastServiceInvoke serviceInvoke=applicationContext.getBean(FastServiceInvoke.class);
			
			if("queryList".equals(vs[1])){
				List dList=service.queryList(serviceInvoke);
				ARPResult result=new ARPResult(dList);
				super.renderToOutput(result);
			}else if("queryEntity".equals(vs[1])){
				ARPResult result=service.queryById(serviceInvoke);
				super.renderToOutput(result);
			}else if("saveEntity".equals(vs[1])){
				ARPResult result=service.saveEntity(serviceInvoke);
				super.renderToOutput(result);
			}else if("updateEntity".equals(vs[1])){
				ARPResult result=service.updateEntity(serviceInvoke);
				super.renderToOutput(result);
			}else if("deleteEntity".equals(vs[1])){
				ARPResult result=service.deleteEntityById(serviceInvoke);
				super.renderToOutput(result);
			}
			return null;
			
		}
		
		return null;
	}

	
	public Object getModel() {
		return null;
	}


}

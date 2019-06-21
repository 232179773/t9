package com.t9.system.action;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.service.DataCache;
import com.t9.system.service.FastBaseService;
import com.t9.system.service.FastService;
import com.t9.system.service.FastServiceInvoke;
import com.t9.system.util.LogUtil;
import com.t9.system.web.RequestContext;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Result;

@SuppressWarnings({"serial","unused","rawtypes"})
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class FastAction extends BaseAction {
	
	@Autowired
	FastService fastService;

	@Autowired
	private DataCache dateCache;
	@Override
	public String execute(){
		try{
			HttpServletRequest request=getRequest();
			String requestURI=request.getRequestURI();
			String pathInfo=requestURI.substring(requestURI.lastIndexOf("/")+1);
			log.info(pathInfo);
			if(pathInfo.indexOf("!")>0){
				String[] vs=pathInfo.split("!");
				
				Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
				String tableCode=dateCache.appPathToTableName(vs[0]);
				if(tableCode==null){
					T9Result result=new T9Result(false,pathInfo +" is error !!!");
					super.renderToOutput(result);
					return null;
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
					super.renderToOutput(dList);
				}else if("queryById".equals(vs[1])){
					T9Result result=service.queryById(serviceInvoke);
					super.renderToOutput(result);
				}else if("saveEntity".equals(vs[1])){
					T9Result result=service.saveEntity(serviceInvoke);
					super.renderToOutput(result);
				}else if("updateEntity".equals(vs[1])){
					T9Result result=service.updateEntity(serviceInvoke);
					super.renderToOutput(result);
				}else if("deleteEntityById".equals(vs[1])){
					T9Result result=service.deleteEntityById(serviceInvoke);
					super.renderToOutput(result);
				}
				return null;
				
			}
		}catch(Exception e){
			String msg="系统错误，请稍后重试!";
			LogUtil.logException(e);
			String errmessage=e.getMessage();			
			msg=getText(errmessage);	
			T9Result result=new T9Result(false,msg);
			super.renderToOutput(result);
		}
		return null;
	}

	
	public Object getModel() {
		return null;
	}


}

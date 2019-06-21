package com.t9.system.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.t9.system.action.FastAction;

/** 
 * @author husq
 * @version 1.0
 * @datetime 2013-7-2
 */
@SuppressWarnings("serial")
public class SessionInterceptor extends AbstractInterceptor {
	public String allow;//struts.xml中有配置的，不需要进行过滤的action
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext ctx=actionInvocation.getInvocationContext();
		
		Object action = (Action)actionInvocation.getAction();
		String actionName=action.getClass().getSimpleName();

		if("FastAction".equals(actionName)){
			ApplicationContext applicationContext=RequestContext.getContext().getWebApplicationContext();
			FastAction fastAction=(FastAction)applicationContext.getBean("fastAction");
			return fastAction.execute();
		}
		
		if(allow!=null)
			allow=allow.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
		if(allow!=null&&allow.length()>0){
			String allows[]=allow.split(";");
			for(String temp:allows){
				if(actionName.equals(temp)){return actionInvocation.invoke();}
			}
		}
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST); 
		isAjaxRequest(request);
		return actionInvocation.invoke();
	}
	
	//针对jquery的ajax请求
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		//针对flash发起的异步请求
		String flashHeader = request.getHeader("x-flash-version");
		return ((header != null && "XMLHttpRequest".equals(header))||null != flashHeader);
	}  

}
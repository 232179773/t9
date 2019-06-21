package com.t9.system.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.t9.system.entity.Log;
import com.t9.system.service.LogService;
import com.t9.system.service.SystemUserService;
import com.t9.system.util.StringUtil;


/**
 * @author husq
 * @version 1.0
 * @datetime 2013-7-2
 */
@SuppressWarnings({ "rawtypes", "unused","serial","unchecked" })
public class OperationInterceptor extends AbstractInterceptor {


	@Autowired
	private LogService logService;

	@Autowired
	private SystemUserService systemUserService;
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		long beginTime = System.currentTimeMillis();//开始时间
		String forword = actionInvocation.invoke();// 方法代理运行
		try{
			// 得请求上下文环境
			ActionContext ctx = actionInvocation.getInvocationContext();
			// 得到请求对象
			HttpServletRequest request = (HttpServletRequest) ctx
					.get(ServletActionContext.HTTP_REQUEST);
			// 得session
			HttpSession session = request.getSession();
			// 得请求的方法名
		//	actionInvocation.get
			String methodName = actionInvocation.getProxy().getMethod();
			Class classz=actionInvocation.getProxy().getAction().getClass();
			Logger logger=classz.getDeclaredMethod(methodName).getAnnotation(Logger.class);
			long endTime = System.currentTimeMillis();
			String ip=ServletUtils.getIpAddr(request);
			String url=request.getRequestURL().toString();
			String logname=methodName;
			String content=ServletUtils.getParamsByRequest(request);
			if(logger!=null){
				logname=logger.logName();
				content=StringUtil.getRightString(logger.logParam(),ServletUtils.getMapByRequest(request));
			}
			Log log = new Log();
			
			if(systemUserService.getSessionCurrentUser()!=null)//session失效时会发生错误
				log.setSTAFF_NAME(systemUserService.getSessionCurrentUser().getNICKNAME());

			log.setLOG_TIME(new Date());
			log.setURL(url);
			log.setLOG_TITLE(logname);
			log.setCLIENT_IP(ip);
			log.setLOG_CONTENT(content);
			logService.saveLog(log);
		}catch(Exception e){
	//		e.printStackTrace();
		}
		return forword;
	}

}
package com.t9.system.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.t9.system.entity.SystemUser;
import com.t9.system.util.JsonUtil;

@SuppressWarnings({ "rawtypes", "unchecked","unused"})
public class LoginFilter implements Filter {
	private String redirect;
	private String[] allows;
	
	public void init(FilterConfig arg0) throws ServletException {
		redirect=arg0.getInitParameter("redirect");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		allows=arg0.getInitParameter("allow").split(";");
	}
	public void destroy() {

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletResponse res=(HttpServletResponse)response;
			HttpServletRequest req=(HttpServletRequest)request;
			HttpSession session=req.getSession();
			String path = req.getContextPath();	
			if(!req.getRequestURI().endsWith("jsp")){
				chain.doFilter(req, res);
				return;
			}
			if (isAllow(req.getRequestURI())) {
				chain.doFilter(req, res);
				return;
			}
			SystemUser staff=(SystemUser)session.getAttribute(ServletUtils.SEESION_USERINFO);	
			if(staff==null){
				if(ServletUtils.isAjaxRequest(req)){
					HashMap hashmap=new HashMap();
					hashmap.put("statusCode", 301);
					hashmap.put("message", "\u4f1a\u8bdd\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u3002");
					hashmap.put("navTabId", "");
					hashmap.put("callbackType", "");
					hashmap.put("forwardUrl", "");
					String jsonStr=JsonUtil.getJsonString(hashmap);
					PrintWriter out;
					try {
//						out = res.getWriter();
//						out.write(jsonStr);
//						out.flush();
//						out.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new T9RuningTimeException("servlet error");
					}
					request.getRequestDispatcher("/frame/demo/ajaxTimeout.html").forward(request, response);
					return;
		//			res.setStatus(301);
		//			RequestUtil.responseOut("GBK", "", res);
				}else{
					String lastUrl=req.getRequestURI();
					String lastQuerString=req.getQueryString()==null?"":req.getQueryString();//.replace("&", "!P!");
					//System.out.println(path+redirect+"?lastUrl="+lastUrl+"?"+lastQuerString);
					//res.sendRedirect(path+"/"+redirect+"?lastUrl="+lastUrl+"?"+lastQuerString);//׷��ԭ����·�����Ա��¼����ֱ��ת��ԭ����·��
					req.setAttribute("lastUrl", lastUrl+"?"+lastQuerString);
					//System.out.println(redirect+"---");
					path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
					//System.out.println("==="+path+"/"+redirect);
					res.sendRedirect(path+"/"+redirect);
				//	request.getRequestDispatcher(redirect).forward(request, response);
					return;
				}
			}
			chain.doFilter(request, response);
	}
	
	private boolean isAllow(String uri) {
		for (String allowPath : allows) {
			if (uri.indexOf(allowPath) >= 0) {
				return true;
			}
		}
		return false;
	}

}

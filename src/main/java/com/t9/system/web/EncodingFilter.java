package com.t9.system.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.t9.system.entity.Log;
import com.t9.system.service.DataCache;
import com.t9.system.service.FastFileServiceImpl;
import com.t9.system.service.LogService;

public class EncodingFilter implements Filter {
	Pattern pattern=Pattern.compile("[\\u4e00-\\u9fa5]");   
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;  
		HttpServletResponse response = (HttpServletResponse) resp; 
		RequestContext.setActionContext(request, response);
		
//		printHead(request);		
		String url =request.getRequestURL().toString();
		String queryStr = request.getQueryString();
		if(url.endsWith("Edit.jsp")||url.endsWith("List.jsp")){
			String pathInfo=request.getRequestURI();
			String context=request.getContextPath();
			pathInfo=pathInfo.substring(context.length());
			
			String appPath=pathInfo.substring(pathInfo.lastIndexOf("/")+1,pathInfo.length()-8);
			DataCache dateCache=(DataCache)RequestContext.getContext().getWebApplicationContext().getBean(DataCache.class);
			String tableCode=dateCache.appPathToTableName(appPath);
			if(tableCode!=null){
				String realPath=request.getSession().getServletContext().getRealPath(pathInfo);
				File file=new File(realPath);
				if(!file.exists()){
					FastFileServiceImpl fastFileServiceImpl=(FastFileServiceImpl)RequestContext.getContext()
							.getWebApplicationContext().getBean("fastFileServiceImpl");
					
					String pageType=pathInfo.substring(pathInfo.length()-8);
					try {
						String fileName = fastFileServiceImpl.generateFile(tableCode, pageType);

						request.getSession().getServletContext()
								.getRequestDispatcher("/frame/demo/genfile/" + fileName)
								.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
						request.getSession().getServletContext()
						.getRequestDispatcher("404.html")
						.forward(request, response);
					}
					return;
				}
			}
		}
		/*
		if(queryStr != null && queryStr.length() > 0&&(queryStr.indexOf("Action")==-1 && queryStr.indexOf("method")==-1 )){
			request.setCharacterEncoding("GBK");
		}else{
			request.setCharacterEncoding("UTF-8");
		}
		*/
		if(queryStr != null){
			queryStr = new String(queryStr.getBytes("ISO-8859-1"),"GBK");
			Matcher matcher=pattern.matcher(queryStr);
			if(matcher.find()){
				request.setCharacterEncoding("GBK");
			}else{
				request.setCharacterEncoding("UTF-8");
			}
		}else{
			request.setCharacterEncoding("UTF-8");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		

		if(url.endsWith("html")){
			String ip=ServletUtils.getIpAddr(request);
			Log log = new Log();
			log.setLOG_TIME(new Date());
			log.setURL(url);
			log.setCLIENT_IP(ip);
			ServletContext sc=request.getSession().getServletContext();
			LogService logService=WebApplicationContextUtils.getWebApplicationContext(sc).getBean(LogService.class);
			logService.saveLog(log);
		}
		chain.doFilter(request, response);
	}

	public void printHead(HttpServletRequest request){
		Enumeration enumeration=request.getHeaderNames();
		while(enumeration.hasMoreElements()){
			String headerName=(String)enumeration.nextElement();
			String value=request.getHeader(headerName);
			System.out.println(headerName+":"+value);
		}
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

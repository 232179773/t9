/**
 * 
 */
package com.t9.system.web;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 请求设置
 * 
 * @author husq
 *
 */
@SuppressWarnings("rawtypes")
public class RequestContext {
	
	private static ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<RequestContext>();
    /**
     * Get current RequestContext.
     * 
     * @return RequestContext.
     */
	private RequestContext(){
	    
	}

    public static RequestContext getContext() {
        return contextThreadLocal.get();
    }
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext context;
    private PageInfo pageInfo;

    private ApplicationContext applicationContext;

    private String oauth2User;
    /**
     * Initiate all servlet objects as thread local.
     * 
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @param session HttpSession object.
     * @param context ServletContext object.
     */
    public static void setActionContext(HttpServletRequest request, HttpServletResponse response) {
    	RequestContext requestContext=new RequestContext();
    	requestContext.setRequest(request);
    	requestContext.setResponse(response);
    	requestContext.setSession(request.getSession());
    	requestContext.setServletContext(request.getSession().getServletContext());
    	requestContext.setPageInfo();
        contextThreadLocal.set(requestContext);
    }

    /**
     * Remove all servlet objects from thread local.
     */
    static void remove() {
        contextThreadLocal.remove();
    }

    /**
     * Get HttpServletRequest object.
     * 
     * @return HttpServletRequest object.
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    Map paramMap;
	public Map getParamsMap() {
		if(paramMap==null)
			paramMap=ServletUtils.getMapByRequest(request);
        return paramMap;
    }
    
    /**
     * Set HttpServletRequest object.
     * 
     * @param request HttpServletRequest object.
     */
    void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public PageInfo getPageInfo() {
    	return pageInfo;
    }
    
    void setPageInfo(){
    	String pageStr=request.getParameter("page");
    	String pagesizeStr=request.getParameter("pagesize");
    	if(pageStr==null||pagesizeStr==null){
    		pageInfo=null;
    		return;
    	}
    	PageInfo pageInfo=new PageInfo();
    	int page=Integer.parseInt(pageStr);
    	int pagesize=Integer.parseInt(pagesizeStr);
    	pageInfo.setPage(page);
    	pageInfo.setPagesize(pagesize);
    	this.pageInfo=pageInfo;    	
    }
    /**
     * Get HttpServletResponse object.
     * 
     * @return HttpServletResponse object.
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Set HttpServletResponse object.
     * 
     * @param response HttpServletResponse object.
     */
    void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * Get HttpSession object.
     * 
     * @return HttpSession object.
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * Set HttpSession object.
     * 
     * @param session HttpSession object.
     */
    void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * Get ServletContext object.
     * 
     * @return ServletContext object.
     */
    public ServletContext getServletContext() {
        return context;
    }

    /**
     * Set ServletContext object.
     * 
     * @param context ServletContext object.
     */
    void setServletContext(ServletContext context) {
        this.context = context;
    }
    
    public ApplicationContext getWebApplicationContext() {
    	applicationContext=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        return applicationContext;
    }

    public String getOauth2User() {
        return oauth2User;
    }

    public void setOauth2User(String oauth2User) {
        this.oauth2User = oauth2User;
    }

}
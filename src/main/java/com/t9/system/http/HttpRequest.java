package com.t9.system.http;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class HttpRequest {

	public static Integer MAX_CONNECT_TIMEOUT = 30000;
	public static Integer MAX_SOCKET_TIMEOUT = 30000;
	private static int MAX_RETRY_TIMES = 1;

	private static final Logger log = Logger.getLogger(HttpRequest.class);
	
	
	public static HttpResponse getUrlContent(String url){
		return getUrlContent(url,new HashMap(),false);
	}

	public static HttpResponse getUrlContent(String url,HashMap paramMap){
		return getUrlContent(url,paramMap,false);
	}

	public static HttpResponse getUrlContent(String url,boolean isHttps){
		return getUrlContent(url,new HashMap(),isHttps);		
	}
	public static HttpResponse getUrlContent(String url,String content,boolean isHttps){
		HttpResponse response=null;
		HttpClient client = new HttpClient();
		//设置代理服务器地址和端口      
//	    client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
		
		if(isHttps){
			ProtocolSocketFactory fcty = new SecureProtocolSocket();
			Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		}
		PostMethod method = new PostMethod( url );
		method.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		method.addRequestHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.addRequestHeader("accept-language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		method.addRequestHeader("accept-encoding", "gzip, deflate");
	//	method.addRequestHeader("referer", "http://www.baidu.com");
	//	method.addRequestHeader("cookie:JSESSIONID=D969BFE2A7456E444B1948C3C5D29D32", "");
	//	method.addRequestHeader("", "");
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		client.getParams().setParameter("http.socket.timeout", MAX_SOCKET_TIMEOUT );
		client.getParams().setParameter("http.connection.timeout", MAX_CONNECT_TIMEOUT );

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultHttpMethodRetryHandler(MAX_RETRY_TIMES, false));
//		StringEntity params = new StringEntity( json.toString(),"UTF-8");
//		 params.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json")); 
//		 params.setContentType("application/json");
//		 post.addHeader("content-type", "application/json");  
//		 post.setEntity(params); 	
		method.setRequestBody(content);
		int statusCode = 0;
		try {
			statusCode = client.executeMethod(method);
			log.info( "POST TO:" + url + ";status:" + method.getStatusLine() );
//			if (statusCode != HttpStatus.SC_OK) {
//				log.warn("Http请求返回: " + method.getStatusLine());
//			}
			response=new HttpResponse(method);
		} catch (Exception e) {
			log.error("url:" + url + " statusCode:" + statusCode, e);
		} finally {
			method.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
		}
		return response;
	}
	
	public static HttpResponse getUrlContent(String url,HashMap paramMap,boolean isHttps){
		HttpResponse response=null;
		HttpClient client = new HttpClient();
		//设置代理服务器地址和端口      
//	    client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
		
		if(isHttps){
			ProtocolSocketFactory fcty = new SecureProtocolSocket();
			Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		}
		PostMethod method = new PostMethod( url );
		method.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		method.addRequestHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.addRequestHeader("accept-language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		method.addRequestHeader("accept-encoding", "gzip, deflate");
	//	method.addRequestHeader("referer", "http://www.baidu.com");
	//	method.addRequestHeader("cookie:JSESSIONID=D969BFE2A7456E444B1948C3C5D29D32", "");
	//	method.addRequestHeader("", "");
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		client.getParams().setParameter("http.socket.timeout", MAX_SOCKET_TIMEOUT );
		client.getParams().setParameter("http.connection.timeout", MAX_CONNECT_TIMEOUT );

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultHttpMethodRetryHandler(MAX_RETRY_TIMES, false));
		if(paramMap!=null){
			Iterator iter=paramMap.keySet().iterator();
			while(iter.hasNext()){
				String key=(String)iter.next();
				method.addParameter( key, (String)paramMap.get(key));
			}
		}
		int statusCode = 0;
		try {
			statusCode = client.executeMethod(method);
			log.info( "POST TO:" + url + ";status:" + method.getStatusLine() );
//			if (statusCode != HttpStatus.SC_OK) {
//				log.warn("Http请求返回: " + method.getStatusLine());
//			}
			response=new HttpResponse(method);
		} catch (Exception e) {
			log.error("url:" + url + " statusCode:" + statusCode, e);
		} finally {
			method.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
		}
		return response;
	}
	
	
}

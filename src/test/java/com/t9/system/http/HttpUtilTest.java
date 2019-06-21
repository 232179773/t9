package com.t9.system.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.t9.system.util.ExportToExcel;
import com.t9.system.web.MySQLUtils;

@SuppressWarnings("unchecked")
public class HttpUtilTest {

	@Test
	public void testHttp() {
		String url1="http://10.2.36.148:9084/wxChat/netBankBatRequest.action";
		HashMap map=new HashMap();
		map.put("openId", "oZIb9jrLM7ETtqRuG1TID-VHAkQA");//oZIb9jrLM7ETtqRuG1TID-VHAkQA  o2-GNt5p5bEo0Z4PuL6ffd5mP2CY
		map.put("tranCode", "0");
		map.put("channelId", "WX");
		map.put("vendorName", "中文2");
		map.put("goodsNm", "中文1");
		map.put("orderNo", "TYTY");
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1,map);
		String responseText=httpResponse.getResponseBody();
		System.out.println(responseText);
	}
	//账单催收测试
	@Test 
	public void testZDCS() {
		//
		String url1="http://10.2.36.214:9081/perbank/wx/getShortLink.do?app=MEBS&handleCode=6";
		HashMap map=new HashMap();
		map.put("wxCode", "oZIb9jlLsat0-ETEwbatAok70fx8");//oHArDjrGJ-qgRrEvSyclZCAiUVU8
		map.put("tranCode", "WX0098");
		map.put("tranData", "");
		map.put("maxReturn", "6");
		map.put("srcChannel", "WS");//yixin  weixin
		map.put("requestChannel", "WX");
		map.put("rf", "JSON");
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1,map);
		String responseText=httpResponse.getResponseBody();
		System.out.println(responseText);
	}
	
	
	@Test
	public void test214Ibot() {
		long beginTime=System.currentTimeMillis();
		String url1="http://10.2.212.115:8000/cgfb/ask.action";
		HashMap map=new HashMap();
		map.put("attributes", "{}");//oHArDjrGJ-qgRrEvSyclZCAiUVU8
		map.put("brand", "xinyongka");
		map.put("maxReturn", "6");
		map.put("platform", "yixin");//yixin  weixin
		map.put("question", "人工");
		map.put("userId", "7d4f4ac162d12224");
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1,map);
		String responseText=httpResponse.getResponseBody();
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-beginTime)+"\t");
	}
	
	@Test
	public void testQQ() {
		long beginTime=System.currentTimeMillis();
		String url1="http://10.2.37.101:6543/cgi-bin/message/custom/send?access_token=33H2H1TADtHNlECzwvxwp0nmT-MHmhIQ1cczwYvQ59FP-HCSfxaPZRNiOjEfe3gu";
		String content="{'touser':'o2-GNt5p5bEo0Z4PuL6ffd5mP2CY','msgtype':'news','news':{'articles':[{'url':'http://113.108.207.154:8765/wx.do?','picurl':'','description':'aa','title':'dd'}]}}";
		//oZIb9jrLM7ETtqRuG1TID-VHAkQA
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1,content,false);
		String responseText=httpResponse.getResponseBody();
		System.out.println(responseText);
		
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-beginTime)+"\t");
	}
	
	
	Random random=new Random();
	
	public void test29Http() {
		long beginTime=System.currentTimeMillis();
		int msgId=random.nextInt(100000000);
		String url1="http://127.0.0.1:8080/wxChat/weixin.do?openacc=1";
		String content="<xml><ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[oHArDjl1oJE_agiuzuIxWAoZ8CK0]]></FromUserName>" +
				"<CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[你好]]></Content><MsgId>1111</MsgId></xml>";
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1,content,false);
		String responseText=httpResponse.getResponseBody();
		long endTime=System.currentTimeMillis();
	//	System.out.println((endTime-beginTime));
		System.out.println((endTime-beginTime)+"\t"+responseText);
	}
	
	@Test
	public void test29HttpThread() {
		long beginTime=System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {
				public void run() {
					try{
				//		test29Http();
				//		test214Ibot() ;
					}catch(Throwable e){
						e.printStackTrace();
					}
				}
			}).start();
		}
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-beginTime));
	}
	public DataSource getDataSource(){
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		try {
			dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/t9?characterEncoding=UTF-8");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setUser("root");
			dataSource.setPassword("root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	public void testHttpPool() throws Exception{

		testQQ();
		long beginTime=System.currentTimeMillis();
		boolean flag=false;
		StringBuffer buffer=new StringBuffer();
		DataSource dataSource=getDataSource();
		Connection connection=dataSource.getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from  TB_WX_ACCOUNT limit 0,50000;");
		ResultSetMetaData lineInfo = rs.getMetaData();
		int columnCount = lineInfo.getColumnCount();
		List tempList = new ArrayList();
		while(rs.next()){
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (int i = 1; i <= columnCount; i++) {
				String columeName = lineInfo.getColumnLabel(i);
				String columeType = lineInfo.getColumnTypeName(i);
				String columeValue =rs.getString(i);
				if( "datetime".equalsIgnoreCase(columeType)){
					columeValue=MySQLUtils.mysqlDateToString(columeValue);
				}else if( "date".equalsIgnoreCase(columeType)){
			//		System.out.println(columeValue);
			//		columeValue=MySQLUtils.mysqlDateToString(columeValue);
				}
				map.put(columeName, columeValue);
			}
			tempList.add(map);
		}
		HSSFWorkbook workbook =ExportToExcel.export("aa", new String[columnCount], tempList,null);
		OutputStream fos = new FileOutputStream(new File("aa.xls"));
		workbook.write(fos);
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-beginTime));
	}

	private static int MAX_RETRY_TIMES = 1;
	public void testFanlitou(){
		String url="https://www.fanlitou.com/products/getList?filter=platform__short_name:&orderby=duration_days;desc&hot_recommend=0&query_name=&page=1";
		HttpResponse response=null;
		HttpClient client = new HttpClient();
		//设置代理服务器地址和端口
//	    client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
			ProtocolSocketFactory fcty = new SecureProtocolSocket();
			Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		PostMethod method = new PostMethod( url );
		method.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		method.addRequestHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.addRequestHeader("accept-language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		method.addRequestHeader("accept-encoding", "gzip, deflate");
		//	method.addRequestHeader("referer", "http://www.baidu.com");
		//	method.addRequestHeader("cookie:JSESSIONID=D969BFE2A7456E444B1948C3C5D29D32", "");
		//	method.addRequestHeader("", "");
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(MAX_RETRY_TIMES, false));
//		StringEntity params = new StringEntity( json.toString(),"UTF-8");
//		 params.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//		 params.setContentType("application/json");
//		 post.addHeader("content-type", "application/json");
//		 post.setEntity(params);
		method.setRequestBody("");
		int statusCode = 0;
		try {
			statusCode = client.executeMethod(method);
			System.out.println( "POST TO:" + url + ";status:" + method.getStatusLine() );
//			if (statusCode != HttpStatus.SC_OK) {
//				log.warn("Http请求返回: " + method.getStatusLine());
//			}
			response=new HttpResponse(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
		}
		String responseText=response.getResponseBody();
		System.out.println(responseText);
	}
	public  static void main(String[] args) throws Exception{
//		"wxEHtqAlKZbOiAy3S8PvVtyP2I-FlPYcfZQIu4gplpoBlIhylb2m6MwrSfteekQe3UqaAp0Q5xsUsAiIndb6Cd5Jbp4x41y45jpEUJm9K0nyG-ElmU7b5BY0sb8cfo-VlYSOzGPpEbGoKIvdizqEIw"
		new HttpUtilTest().testFanlitou();
//		long a=System.currentTimeMillis();
//		for (int i = 0; i < 1000000; i++) {
//			long k=a-i;
//		}
//		long endTime=System.currentTimeMillis();
//		System.out.println((endTime-a));
	}

}

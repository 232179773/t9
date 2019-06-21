package com.t9.system.http;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class WenZhouHttpClient {


	private static final Logger log = Logger.getLogger(WenZhouHttpClient.class);
	
	String url1="https://www.wzdai.com/invest/index.html?status=10&page=3&order=-5";
	static Pattern patternSessionId = Pattern.compile("JSESSIONID=([a-zA-Z0-9]+);");
	
	public void run() throws Exception{
	//	for (int i = 1; i < 32; i++) {
		int i=29;
		
		HttpResponse httpResponse=HttpRequest.getUrlContent(url1);
		String responseText=httpResponse.getResponseBody();
	//	}
		System.out.println(responseText);
//		System.out.println(map.get("data"));
	}

	

	public static void main(String[] args) throws Exception {
		WenZhouHttpClient client=new WenZhouHttpClient();
		client.run();
	}
	
}

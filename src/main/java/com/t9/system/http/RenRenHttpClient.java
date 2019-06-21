package com.t9.system.http;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class RenRenHttpClient {

	private static final Logger log = Logger.getLogger(RenRenHttpClient.class);
	
	String loginUrl="http://www.renrendai.com/lend/loanList.action";
	static Pattern patternSessionId = Pattern.compile("JSESSIONID=([a-zA-Z0-9]+);");
	
	String nextUrl="http://www.renrendai.com/lend/loanList!json.action?pageIndex=";
	public void run() throws Exception{
	//	for (int i = 1; i < 32; i++) {
		int i=29;
		HttpResponse httpResponse=HttpRequest.getUrlContent(nextUrl);
		String responseText=httpResponse.getResponseBody();
		System.out.println(responseText);
//		FileUtil.writeToFile("D:\\JavaWork2\\t9\\file"+i+".txt", responseText);
			Thread.sleep(1000);
	//	}
	}

	

	public static void main(String[] args) throws Exception {
		RenRenHttpClient client=new RenRenHttpClient();
		client.run();
	}
	
}

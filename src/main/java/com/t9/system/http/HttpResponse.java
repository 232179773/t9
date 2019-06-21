package com.t9.system.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.log4j.Logger;

import com.t9.system.web.T9Exception;
import com.t9.system.web.T9RuningTimeException;

public class HttpResponse {

	private int statusCode;
	private String responseText = null;
	private HttpURLConnection urlConnection;

	private HttpMethod httpMethod;

	private static final Logger log = Logger.getLogger(HttpResponse.class);
	public HttpResponse(HttpMethod method) throws T9Exception {
		this.httpMethod = method;
		this.statusCode = method.getStatusCode();
		this.responseText = getResponseBody();
	}


	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseHeader(String name) {
		if (urlConnection != null)
			return urlConnection.getHeaderField(name);
		else if (httpMethod != null) {
			Header header = httpMethod.getResponseHeader(name);
			if (header == null)
				return null;
			return header.getValue();
		} else
			return null;
	}



	public void disconnect() {
		urlConnection.disconnect();
	}

	private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

	/**
	 * Unescape UTF-8 escaped characters to string.
	 * 
	 * @author pengjianq...@gmail.com
	 * 
	 * @param original
	 *            The string to be unescaped.
	 * @return The unescaped string
	 */
	public static String unescape(String original) {
		Matcher mm = escaped.matcher(original);
		StringBuffer unescaped = new StringBuffer();
		while (mm.find()) {
			mm.appendReplacement(unescaped, Character.toString((char) Integer
					.parseInt(mm.group(1), 10)));
		}
		mm.appendTail(unescaped);
		return unescaped.toString();
	}

	@Override
	public String toString() {
		return "Response{" + "statusCode=" + statusCode + ", responseString='"
				+ getResponseBody() + '\'' + "}";
	}

	public String getResponseBody(){
		if (responseText != null) {
			return responseText;
		}
		try {
			InputStream inputStream=httpMethod.getResponseBodyAsStream();
	//		printResponseHead();
			Header header=httpMethod.getResponseHeader("Content-Encoding");
			if(header!=null&&"gzip".equals(header.getValue())){
				inputStream=new GZIPInputStream(inputStream);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	//		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
	//		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer buf = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
				buf.append(line).append("\n");
			}
			this.responseText = buf.toString();
			inputStream.close();
		} catch (IOException ioe) {
			throw new T9RuningTimeException(ioe.getMessage(), ioe);
		}
		return responseText;
	}

	static Pattern patternSessionId = Pattern.compile("JSESSIONID=([a-zA-Z0-9]+);");
	public String getSessionId(){
		String sessionId=null;
		Header header=httpMethod.getResponseHeader("Set-Cookie");
		if(header!=null){
			String value=header.getValue();
			Matcher matcherValue =patternSessionId.matcher(value);
			if(matcherValue.find()){
				sessionId=matcherValue.group(1);
			}
		}
		return sessionId;
	}
	
	public void printResponseHead(){
		
		Header[] headers=httpMethod.getRequestHeaders();
		for (int i = 0; i < headers.length; i++) {
			Header header=headers[i];
			System.out.println("request Headers:" + header.getName()+" "+header.getValue());
			log.info( "request Headers:" + header.getName()+" "+header.getValue() );
		}
		headers=httpMethod.getResponseHeaders();
		for (int i = 0; i < headers.length; i++) {
			Header header=headers[i];
			System.out.println("response Headers:" + header.getName()+" "+header.getValue());
			log.info( "response Headers:" + header.getName()+" "+header.getValue() );
		}
	}
}

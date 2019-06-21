<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="com.opensymphony.xwork2.*"%>
<%@ page import="com.opensymphony.xwork2.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.t9.system.web.*"%>
<%
ActionContext act = ActionContext.getContext();
ValueStack stack = act.getValueStack();

//获得异常
Throwable exception= (Throwable)stack.findValue("exception");

exception.printStackTrace();
String errmessage="";
if(exception instanceof T9RuningTimeException){
	T9RuningTimeException bre = (T9RuningTimeException)exception;
	errmessage=bre.getMessage();
}
	String json = "{MESSAGE:'"+errmessage+"'}";
	response.setContentType("text/xml; charset=GBK");
	try {
		PrintWriter pw = response.getWriter();
		pw.print(json);
        pw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
%>

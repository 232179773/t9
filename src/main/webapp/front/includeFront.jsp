<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.t9.system.web.ServletUtils"%>
<%@ page import="com.t9.system.util.JsonUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.io.File"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Map<String, String> map = ServletUtils.getMapByRequest(request);
	String jsonStr=JsonUtil.getJsonString(map);
	String url=request.getRequestURL().toString();
%>

<link rel="stylesheet" type="text/css" href="<%=basePath %>front/css/public.css" charset="utf-8">

<link rel="stylesheet" href="<%=basePath %>frame/js/jqueryTreeTable/css/jquery.treetable.css" />
<link rel="stylesheet" href="<%=basePath %>frame/js/jqueryTreeTable/css/jquery.treetable.theme.default.css" />
    
<link href="<%=basePath %>frame/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>frame/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>frame/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath %>frame/js/jbox/jbox.css" rel="stylesheet" />
<link href="<%=basePath %>frame/js/ztree/zTreeStyle.css" rel="stylesheet" />

<script src="<%=basePath %>frame/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=basePath %>frame/js/validator-0.2.0/jquery.validator.css">
<script type="text/javascript" src="<%=basePath %>frame/js/validator-0.2.0/jquery.validator.js"></script>
<script type="text/javascript" src="<%=basePath %>frame/js/validator-0.2.0/local/zh_CN.js"></script>

<script src="<%=basePath %>frame/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/T9.ui.js" type="text/javascript"></script>

<script src="<%=basePath %>frame/js/PageDiv.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/T9.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/jquery-extend.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/jbox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/ztree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/jqueryTreeTable/jquery.treetable.js"></script>
<script src="<%=basePath %>frame/js/jquery-ui/ui/jquery.ui.core.js"></script>
<script src="<%=basePath %>frame/js/jquery-ui/ui/jquery.ui.widget.js"></script>
<script src="<%=basePath %>frame/js/jquery-ui/ui/jquery.ui.tabs.js"></script>
<link rel="stylesheet" href="<%=basePath %>frame/js/jquery-ui/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="<%=basePath %>frame/js/jquery-ui/demos.css">

<link href="<%=basePath %>frame/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- 
<script src="<%=basePath %>frame/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/T9.combox.js" type="text/javascript"></script>
 -->
<script src="<%=basePath %>frame/js/jquery.query.js" type="text/javascript"></script>

<script type="text/javascript">
var urlParam=<%=jsonStr %>;
urlParam.get=function(key){
	return $.query.get(key);
}
var base_path="<%=basePath %>";
$(function(){
	initUI(document);
});
</script>
<%
if(url.endsWith("jsp")){
	String jsUrl=url.substring(0,url.length()-1);
	String jsFielUrl=jsUrl.substring(basePath.length());
	
	String filePath=session.getServletContext().getRealPath(jsFielUrl);
	
	File file=new File(filePath);
	if(file.exists()){
		out.print("<script type='text/javascript' src='"+jsUrl+"'></script>");
	}
}
%>
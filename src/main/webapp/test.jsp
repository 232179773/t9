<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

function viewProfile() {
	typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke && WeixinJSBridge.invoke("profile", {
	username: "gh_0a9932769bf7",
	scene: "123"
	});
}
var username="gh_c2f21d517b15";
function viewProfile2() {
	if(typeof WeixinJSBridge =='undefined')
		return false;
	WeixinJSBridge.invoke('addContact', {   
		webtype:'1',    
		username: username 
	},function(d) {
		alert(d.err_msg);  
		WeixinJSBridge.log(d.err_msg);          
		// 返回d.err_msg取值，d还有一个属性是err_desc            
		 // add_contact:cancel 用户取消            
		  // add_contact:fail　关注失败            
		   // add_contact:ok 关注成功             
		   // add_contact:added 已经关注            
		   // WeixinJSBridge.log(d.err_msg);                    
	});
}
(function(){
    function onBridgeReady() {
        var appId  = '',
            imgUrl = "http://mmbiz.qpic.cn/mmbiz/4GUmGmSUbY494dzhxdheVCZ0btxp47fAH6Vg7aPqvibwHdPcNmr1IYO4mbvicdp6wBDTV4L97Y0aDDpDp9wREaDA/0",
            link   = "http://www.baidu.com",
            title  = "aa",
            desc   = "zz",
            fakeid = "NzIyMjM2OTYz";
            desc   = desc || link;  

        // 发送给好友; 
        WeixinJSBridge.on('menu:share:appmessage', function(argv){
        
                    WeixinJSBridge.invoke('sendAppMessage',{
                                          "appid"      : appId,
                                          "img_url"    : imgUrl,
                                          "img_width"  : "640",
                                          "img_height" : "640",
                                          "link"       : share_scene(link, 1),
                                          "desc"       : desc,
                                          "title"      : title
                    }, function(res) {report(link, fakeid, 1);
                    });
        });

                // 分享到朋友圈;
        WeixinJSBridge.on('menu:share:timeline', function(argv){
                    report(link, fakeid, 2);
                    WeixinJSBridge.invoke('shareTimeline',{
                                          "img_url"    : imgUrl,
                                          "img_width"  : "640",
                                          "img_height" : "640",
                                          "link"       : share_scene(link, 2),
                                          "desc"       : desc,
                                          "title"      : title
                    }, function(res) {
                    });
        
        });

                // 分享到微博;
        var weiboContent = '';
        WeixinJSBridge.on('menu:share:weibo', function(argv){
        
                    WeixinJSBridge.invoke('shareWeibo',{
                                          "content" : title + share_scene(link, 3),
                                          "url"     : share_scene(link, 3) 
                                          }, function(res) {report(link, fakeid, 3);
                                          });
        });

                // 分享到Facebook
        WeixinJSBridge.on('menu:share:facebook', function(argv){
            report(link, fakeid, 4);
            WeixinJSBridge.invoke('shareFB',{
                  "img_url"    : imgUrl,
                  "img_width"  : "640",
                  "img_height" : "640",
                  "link"       : share_scene(link, 4),
                  "desc"       : desc,
                  "title"      : title
            }, function(res) {} );
        });

                // 新的接口
        WeixinJSBridge.on('menu:general:share', function(argv){
            var scene = 0;
            switch(argv.shareTo){
                case 'friend'  : scene = 1; break;
                case 'timeline': scene = 2; break;
                case 'weibo'   : scene = 3; break;
            }
                argv.generalShare({
                                    "appid"      : appId,
                                    "img_url"    : imgUrl,
                                    "img_width"  : "640",
                                    "img_height" : "640",
                                    "link"       : share_scene(link,scene),
                                    "desc"       : desc,
                                    "title"      : title
                }, function(res){report(link, fakeid, scene);
                });
        });
		
        // get network type
		var nettype_map = {
			"network_type:fail" : "fail",
			"network_type:edge": "2g",
			"network_type:wwan": "3g",
			"network_type:wifi": "wifi"
		};
		if (typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke){
			WeixinJSBridge.invoke('getNetworkType',{}, function(res) {
				networkType = nettype_map[res.err_msg];
				initpicReport();
			});
		}        }

    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
    
    })();

function share_scene(){
	return 'vvv';
}
</script>
  </head>
  
  <body>
29  广发银行 gh_4f3340841a00 <br>
gh_c2f21d517b15<br>dd
使用方法：<a href="javascript:viewProfile2();"><span class="text-ellipsis">关注</span></a><br><br>
<a href="weixin://profile/wx8536db86f332ef20">挖段子冷笑话</a><br>
<a href="weixin://contacts/profile/wx8536db86f332ef20">挖段子冷笑话</a><br>
<a href="http://weixin.qq.com/q/eEw9093krwrW2xnh92Af">点我关注乐享会</a><br/>
    This is my JSP page. <br>
     <%
  	java.util.Enumeration enumer=request.getHeaderNames();
  	while(enumer.hasMoreElements()){
  		String key=(String)enumer.nextElement();
  		out.println(key+":"+request.getHeader(key));
  	    out.println("<br/>");
  	}

    out.println("<br/>");
    out.println("<br/>");
    out.println("Protocol: " + request.getProtocol());
    out.println("<br/>");
    out.println("Scheme: " + request.getScheme());
    out.println("<br/>");
    out.println("Server Name: " + request.getServerName() );
    out.println("<br/>");
    out.println("Server Port: " + request.getServerPort());
    out.println("<br/>");
    out.println("Protocol: " + request.getProtocol());
    out.println("<br/>");
    out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo());
    out.println("<br/>");
    out.println("Remote Addr: " + request.getRemoteAddr());
    out.println("<br/>");
    out.println("Remote Host: " + request.getRemoteHost());
    out.println("<br/>");
    out.println("Character Encoding: " + request.getCharacterEncoding());
    out.println("<br/>");
    out.println("Content Length: " + request.getContentLength());
    out.println("<br/>");
    out.println("Content Type: "+ request.getContentType());
    out.println("<br/>");
    out.println("Auth Type: " + request.getAuthType());
    out.println("<br/>");
    out.println("HTTP Method: " + request.getMethod());
    out.println("<br/>");
    out.println("Path Info: " + request.getPathInfo());
    out.println("<br/>");
    out.println("Path Trans: " + request.getPathTranslated());
    out.println("<br/>");
    out.println("Query String: " + request.getQueryString());
    out.println("<br/>");
    out.println("Remote User: " + request.getRemoteUser());
    out.println("<br/>");
    out.println("Session Id: " + request.getRequestedSessionId());
    out.println("<br/>");
    out.println("Request URI: " + request.getRequestURI());
    out.println("<br/>");
    out.println("Servlet Path: " + request.getServletPath());
    out.println("<br/>");
    out.println("Accept: " + request.getHeader("Accept"));
    out.println("<br/>");
    out.println("Host: " + request.getHeader("Host"));
    out.println("<br/>");
    out.println("Referer : " + request.getHeader("Referer"));
    out.println("<br/>");
    out.println("Accept-Language : " + request.getHeader("Accept-Language"));
    out.println("<br/>");
    out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
    out.println("<br/>");
    out.println("User-Agent : " + request.getHeader("User-Agent"));
    out.println("<br/>");
    out.println("Connection : " + request.getHeader("Connection"));
    out.println("<br/>");
    out.println("Cookie : " + request.getHeader("Cookie"));
    out.println("<br/>");
    out.println("Created : " + session.getCreationTime());
    out.println("<br/>");
    out.println("LastAccessed : " + session.getLastAccessedTime());
    out.println("<br/>");
    %>
  </body>
</html>

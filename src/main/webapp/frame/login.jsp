<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<%=basePath %>frame/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/T9.js" type="text/javascript"></script>
<script src="<%=basePath %>frame/js/jquery-extend.js" type="text/javascript"></script>

<title>风险管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript">
var base_path="<%=basePath %>";

if (window.top !== window.self) { 
	window.top.location.href=window.location.href;
}
function login(){
	var param=$('#logonForm').serializeJSON();
	T9.post('/service/login!userLogin',param,function(result){
		if(result.success){
			T9.navigate('/service/login!mainPage');
		}else{
			T9.alert(result.message);
			$('#imgCodeSpan').setCodeImage();
			$('#verifyCode').val('');
		}
	});
}
$(function(){
//	$('#imgCodeSpan').setCodeImage();
})
</script>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://demo.dwzjs.com"><img src="themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="http://bbs.dwzjs.com">反馈</a></li>
						<li><a href="doc/dwz-user-guide.pdf" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form id="logonForm" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" id="LOGIN_CODE" name="LOGIN_CODE" size="20" class="login_input" value="superadmin"/>
					</p>
					<p>
						<label>密码：</label>
						<input type="password" id="PASSWORD" name="PASSWORD" size="20" class="login_input"  value="1234qwer"/>
					</p>
					<p style="display:none">
						<label>验证码：</label>
						<input class="code" id="verifyCode" name ="verifyCode" type="text" size="5" />
						<span id="imgCodeSpan"></span>
					</p>
					<div class="login_bar">
						<input class="sub" onclick="login()" type="button" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">下载驱动程序</a></li>
					<li><a href="#">如何安装密钥驱动程序？</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2009 www.dwzjs.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>
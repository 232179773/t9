<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="zh-cn" http-equiv="Content-Language"><title></title>
<#include "/WEB-INF/front/include/_include.ftl"/>
<script>

$(function(){
	$('#imgCodeSpan').setCodeImage();
	
	
	$('#loginBtn').click(function (){
		var param=$('#logonForm').serializeJSON();
		T9.post('service/loginControl!userLogin',param,function(result){
			if(result.success){
				T9.navigate('');
			}else{
				T9.alert(result.message);
				$('#imgCodeSpan').setCodeImage();
				$('#verifyCode').val('');
			}
		});
	});
})
</script>
</head>
<body>
	<#include "/WEB-INF/front/include/head.ftl"/>
		

	<div class="content">
		<!-- head -->
		<div class="loginHead">
				<strong>欢迎登录</strong>
				<p>还没帐号，现在就<a href="#">免费注册</a></p>
		</div>
		<!-- head end -->

		<div class="loginBox">
				
				<form id="logonForm" method="post">
				<div class="inputBox">
					<span><i class="redStar">*</i>账户名：</span>
					<p class="account"><input type="text" class="text" name="LOGIN_CODE" /></p>
				</div>
				<div class="inputBox">
					<span><i class="redStar">*</i>密码：</span>
					<p class="password"><input type="password" class="text" name="PASSWORD" /></p>
				</div>

				<div class="inputBox mb15">
					<span><i class="redStar">*</i>验证码：</span>
					<p class="code"><input type="text" name="verifyCode" id="verifyCode" class="text" /></p>
					<span id="imgCodeSpan"></span>
				</div>
				<div class="inputBox mb15">
					<span></span>
					<label><input type="checkbox" class="checkbox" />自动登录  <a href="#">忘记密码？</a></label>
				</div>
				<div class="inputBox">
					<span></span>
					<b class="loginBtn" id="loginBtn"></b>
				</div>
				</form>

		</div>

	</div>

</body>
</html>

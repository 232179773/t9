<div class="topMenu">
			<div class="con">
				<div class="wel">HI，欢迎来到喜来登订餐网！</div>  
				<#if userinfo?exists> 
				<div class="links">${userinfo.LOGIN_CODE}<a href="javascript:T9.navigate('service/loginControl!out');">退出</a></div> 
				<#else> 
				<div class="links"><a href="javascript:T9.navigate('service/loginControl!enter');" id="loginA">登录</a><a href="#">免费注册</a></div> 
				</#if> 
			</div>
		<!-- 登录后 显示 用户中心       我的收藏       意见反馈 -->
</div>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="zh-cn" http-equiv="Content-Language"><title></title>
<%@include file="../includeFront.jsp" %>
<script type="text/javascript">

</script>
</head>
<body>
	<!-- topMenu -->
		<div class="topMenu">
			<div class="con">
				<div class="wel">HI，欢迎来到喜来登订餐网！</div>  
				<div class="links"><a href="#">登录</a><a href="#">免费注册</a></div> 
			</div>
		<!-- 登录后 显示 用户中心       我的收藏       意见反馈 -->
		</div>
		<!-- topMenu end -->
	<div class="content">
		

		<!-- head -->
		<div class="head">
			<div class="topSe">
				<input type="text" />
				<span></span>
			</div>
		</div>
		<!-- head end -->
		
		<!-- userBox -->
			<div class="userBox">
				
				<div class="userRight" id="newList">
					<ul class="titleList">
						<li class="act">收藏餐厅</li>
						<li>收藏菜式</li>
					</ul>
					
						<div id="collectionStorePageDiv" class="pageContent" style="display:none">
							<ul class="fdList" id="collectionStoreUI"></ul>
						</div>
					
					<!-- 收藏餐厅 end -->
				
					<!-- 收藏菜式 -->
					
						<div id="collectionMenuPageDiv" class="pageContent">
							
							   
							
						</div>
					
					
					
				</div>

			</div>
		<!-- userBox end -->	

	</div>

</body>
</html>

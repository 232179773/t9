<!DOCTYPE html>
<html>
<head>
<#assign basepath= "com.t9.system.web.BasePathDirective"?new()>

<base href="<@basepath/>/frame/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统管理平台</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="bin/dwz.min.js" type="text/javascript"></script>

<script src="js/T9.js" type="text/javascript"></script>
<script src="js/jquery-extend.js" type="text/javascript"></script>

<script type="text/javascript">
var base_path="<@basepath/>";
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
//			setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">
					<li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
						<ul>
							<li><a href="sidebar_1.html">北京</a></li>
							<li><a href="sidebar_2.html">上海</a></li>
							<li><a href="sidebar_2.html">南京</a></li>
							<li><a href="sidebar_2.html">深圳</a></li>
							<li><a href="sidebar_2.html">广州</a></li>
							<li><a href="sidebar_2.html">天津</a></li>
							<li><a href="sidebar_2.html">杭州</a></li>
						</ul>
					</li>
					<li><a href="https://me.alipay.com/dwzteam" target="_blank">捐赠</a></li>
					<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
					<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
					<li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
					<li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li>
					<li><a href="../service/login!logonOut">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		
			<div id="navMenu">
				<ul>
					<li class="selected"><a href="sidebar_1.html"><span>资讯管理</span></a></li>
					<li><a href="sidebar_2.html"><span>订单管理</span></a></li>
					<li><a href="sidebar_1.html"><span>产品管理</span></a></li>
					<li><a href="sidebar_2.html"><span>会员管理</span></a></li>
					<li><a href="sidebar_1.html"><span>服务管理</span></a></li>
					<li><a href="sidebar_2.html"><span>系统设置</span></a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
						
							<#list menutree as col>
								 <li><a <#if col.URL??> href="<@basepath/>${col.URL}" rel="${col.ID}" target="navTab"</#if>>${col.NAME}</a>
								<#if col.children??> 
									<ul>
									<#list col.children as cols1>
								  		<li><a <#if cols1.URL??> href="<@basepath/>${cols1.URL}" rel="${cols1.ID}" target="navTab" external="true"</#if>>${cols1.NAME}</a></li>
								  		<#if cols1.children??> 
											<ul>
											<#list cols1.children as cols2>
										  		<li><a <#if cols2.URL??>href="<@basepath/>${cols2.URL}"  rel="${cols2.ID}" target="navTab"</#if> >${cols2.NAME}</a></li>
											</#list>
											</ul>
										</#if>
									</#list>
									</ul>
								</#if>
								</li>
							</#list>
							<li><a>T9组件</a>
								<ul>
									<li><a href="demo/demoList.jsp" target="navTab" rel="demo" external="true">分页demo</a></li>
									<li><a href="<@basepath/>frame/demo/nice_validation.jsp" target="navTab"  external="true" rel="demo">nice validator表单验证</a></li>
									<li><a href="demo/w_tabs.jsp" target="navTab" rel="w_tabs" external="true">选项卡面板</a></li>
									<li><a href="demo/tableList.jsp" target="navTab" rel="w_tabs" external="true">快速开发</a></li>
									<!-- 
									<li><a href="demo/w_alert.html" target="navTab" rel="demo" external="true">提示</a></li>
									<li><a href="demo/w_editor.html" target="navTab" rel="demo" external="true">编辑器</a></li>
									<li><a href="demo/w_validation.jsp" target="navTab" rel="demo" external="true">表单验证</a></li>
									<li><a href="demo/w_datepicker.html" target="navTab" rel="demo" external="true">日期</a></li>
									<li><a href="demo/w_panel.html" target="navTab" rel="w_panel" external="true">面板</a></li>
									<li><a href="demo/w_tree.html" target="navTab" rel="w_tree" external="true">树形菜单</a></li>
									 -->
								</ul>
							</li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>待删除菜单</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							
						</ul>
					</div>
				</div>

			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div>
							<p><span>DWZ富客户端框架</span></p>
							<p>DWZ官方微博:<a href="" target="_blank">http://weibo.com/dwzui</a></p>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2013 by T9 team</div>

</body>
</html>
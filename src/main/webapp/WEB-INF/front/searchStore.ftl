<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="zh-cn" http-equiv="Content-Language"><title></title>
<#include "/WEB-INF/front/include/_include.ftl"/>
<script>

function storeInformation(id,name){
/*
	T9.post('service/store!addCookiesStore',{ID:id,STORE_NAME:name},function(result){
		
	});
	*/
	var url = 'service/store!addCookiesStore?ID='+id+'&STORE_NAME='+name;
    T9.navigate(url, true);
}

function searchStoreByName(){
	var storeName=$('#storeNameId').val();
	T9.post('service/store!queryStorePage',{STORE_NAME:storeName},function(result){
		
	});
}
$(function(){	
	$('#loginbtn').click(function(){
		T9.navigate('service/store!queryStorePage');
	});
});

</script>
</head>
<body>
	<!-- topMenu -->
	<#include "/WEB-INF/front/include/head.ftl"/>
		<!-- topMenu end -->
	<div class="content">
		

		<!-- head -->
		<div class="head">
			<div class="topSe">
				<input type="text" id="storeNameId"/>
				<a onClick="searchStoreByName();"><span></span></a>
			</div>
		</div>
		<!-- head end -->
		
		<!-- leftCon -->
		<div class="leftCon">
			<div class="listTitle">
				<p>所在区域饭店名称</p>
				<span>店铺地址</span>
			</div>
			<ul class="dinerList" id="store">
				<#list searchStore as gridData>
				<li onclick="storeInformation('${gridData.ID}','${gridData.STORE_NAME}')"><p class="act">${gridData.STORE_NAME}</p><span><em>${gridData.STORE_ADDRESS}</em></span></li>
				</#list>
			</ul>

		</div>
		<!-- leftCon end -->

		<!-- rightCon -->
		<div class="rightCon">

			<div class="FBox">
				<h4>推荐的餐厅</h4>
				<ul>
					<#assign x=1>
					<#list recommendStore as gridData>
						<li><span>${x}</span><a href="#">${gridData.storeName}</a></li>
						<#assign x = x + 1>
					</#list>
					
				</ul>
			</div>

			<div class="FBox">
				<h4>上次浏览的餐厅</h4>
				<ul>
				<#assign x=1>
				<#list historyStore as gridData>
					<li><span>${x}</span>${gridData.NAME}</li>
					<#assign x = x + 1>
				</#list>
				</ul>
				
			</div>

		</div>
		<!-- rightCon end -->
	</div>

</body>
</html>

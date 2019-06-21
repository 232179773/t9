<%@ page language="java" pageEncoding="UTF-8"%>

<HTML>
<HEAD>
<TITLE>randomValidateCode</TITLE>
<%@include file="/frame/_include.jsp" %>
</HEAD>

<SCRIPT type=text/javascript>
$(function (){
	
		getCodeImage();
});

function getCodeImage(){
	
	var imgUrl=base_path+'service/randomValidateCode!getRandcode?randtime='+new Date();
	$('#codeId').html('<img src="'+imgUrl+'">' );
}

function checkCode(){
	var params={name:$("#codeInputId").val()};
	T9.post('service/randomValidateCode!checkCode',params,function(result){
		if(result){
			if(result.success){
				alert("验证成功");
			}else{
				alert("验证失败");
			}
		}
	});
}

</SCRIPT>
<body>
	<input type="text" id="codeInputId">
	<div id="codeId"></div>
	<a href="javascript:getCodeImage();">换一个</a>
	<input type="button" onClick="checkCode();" value="提交">
</body>
</HTML>

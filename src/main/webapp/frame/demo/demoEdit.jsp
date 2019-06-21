<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/demo!queryDemoById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#demoEditForm").setData(result.obj);
			}
		});
	};
});
function saveData() {
	var param=$('#demoEditForm').serializeJSON();
	var url='service/demo!saveDemo';
	if(urlParam.ID){
		url='service/demo!updateDemo';
	}
	T9.post(url,param,function(result){
			if(result.success){
				T9.alert("保存成功！");
				T9.dialogClose();
				$("#demoPageDiv").gridReload();
			}
		});
}
function uploadSuccess(fileId,fileUrl){
//	alert(fileId+","+fileUrl);
	$('#pictView').html('<img width="200",height="200" src="'+T9.getFileAppendix(fileUrl)+'"/>');
}
</script>
<div class="pageContent">
	<form id="demoEditForm">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input type="text" id="USER_NAME" name="USER_NAME" maxlength="25"/>
			</p>
			<p>
				<label>电话号码：</label>
				<input name="PHONE_NO" class="required" type="text" value="" alt="请输入客户名称"/>
			</p>
			<p>
				附件<input id="testFileInput11" class="fileUpload" type="file" tableName="TB_SYSTEM_USER" tableId="1777"
					 multi="false" fileType="PICTURE" success="uploadSuccess(fileId,fileUrl)"/>
				<div id="pictView"></div>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="saveData()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

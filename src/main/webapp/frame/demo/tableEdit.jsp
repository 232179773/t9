<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/fastFile!queryTableById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#tableEditForm").setData(result.obj);
	//			$('#MANAGER_NAME').val(decodeURIComponent(urlParam.MANAGER_NAME));
			}
		});
	}
	
});

function saveData() {
	var param=$('#tableEditForm').serializeJSON();
	var url =  'service/fastFile!saveTable' ;
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#tableListDiv").gridReload(urlParam.ID);
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="tableEditForm" data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<input type="hidden" id="APP_ID" name="APP_ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
		    <p>
				<label>表单名称：</label>
				<input data-rule="required" type="text" id="APP_NAME" name="APP_NAME" maxlength="25"/>
			</p>
		    <p>
				<label>唯一索引：</label>
				<input type="text" id="UNIQUE_COL" name="UNIQUE_COL"/>
			</p>
		    <p>
				<label>扩展按钮：</label>
				<input type="text" id="TOOLBAR" name="TOOLBAR"/>
			</p>
		    <p>
				<label>表备注：</label>
				<input type="text" id="TABLE_COMMENT" name="TABLE_COMMENT"/>
			</p>
		    <p>
				<label>应用路径：</label>
				<input type="text" id="APP_PATH" name="APP_PATH"/>
			</p>
		    <p>
				<label>类路径：</label>
				<input type="text" id="CLASS_PATH" name="CLASS_PATH"/>
			</p>
		    <p>
				<label>包名：</label>
				<input type="text" id="PACKAGE" name="PACKAGE"/>
			</p>
		    <p>
				<label>日志模块：</label>
				<input type="text" id="LOG_ID" name="LOG_ID"/>
			</p>
		    <p>
				<label>主键字段：</label>
				<input type="text" id="KEY_COLUMN" name="KEY_COLUMN"/>
			</p>
		    <p>
				<label>生成文件：</label>
				<input type="text" id="GEN_FILE" name="GEN_FILE"/>
			</p>
			
		</div>
		
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>
<script>
$(function() {
	$('#dictOptionEditForm').setUrlParam();
	if(urlParam.ID){
		T9.post('service/dictOption!queryDictOptionById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#OPTION_CODE").attr("readOnly","true");
				$("#OPTION_VALUE").attr("readOnly","true");
				$("#dictOptionEditForm").setData(result.obj);
			}
		});
	};
});
function saveData() {
	var param=$('#dictOptionEditForm').serializeJSON();
	T9.post('service/dictOption!saveDicOption',param,function(result){
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#dictOptionPageDiv").gridReload();
				T9.dialogClose();
			}else{
				T9.alert(result.message);
			}
		});
}

</script>

<div class="pageContent">
	<form id="dictOptionEditForm">
		<input type="hidden" id="ID" name="ID"/>
		<input type="hidden" id="OPTION_LEVEL" name="OPTION_LEVEL" value="1"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>字典类编码：</label>
				<input type="text" id="DICT_CODE" name="DICT_CODE" readonly/>
			</p>
			<p>
				<label>字典项值：</label>
				<input type="text" id="OPTION_VALUE" name="OPTION_VALUE" maxlength="50"/>
			</p>
			<p>
				<label>字典项名称：</label>
				<input type="text" id="OPTION_NAME" name="OPTION_NAME" maxlength="50"/>
			</p>
			<p>
				<label>字典项排序：</label>
				<input type="text" id="ORDER_NO" name="ORDER_NO" maxlength="10"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="saveData()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

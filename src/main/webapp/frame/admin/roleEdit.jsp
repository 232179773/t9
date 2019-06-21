<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp"%>
<script>
$(function() {
	if(urlParam.get('PARENTROLEID')){
		$("#PARENTROLEID").val(urlParam.get('PARENTROLEID'));
		$("#PARENTROLENAME").val(urlParam.get('PARENTROLENAME'));
		$("#parentRole").show();
	}
	if (urlParam.ID) {
		T9.post('service/role!queryRoleById', {
			ID : urlParam.ID
		}, function(result) {
			if (result.success) {
				$("#roleEditForm").setData(result.obj);

			}
		});
	}
	;
});
function saveData() {
	var param = $('#roleEditForm').serializeJSON();
	if (!$("#roleEditForm").isValid()) {
		return false;
	} 
	var url = "";
	if (urlParam.ID) {
		url = "service/role!updateRole";
	} else {
		url = "service/role!saveRole";
	}
	T9.post(url, param, function(result) {
		if (result.success) {
			T9.alert("保存成功！");
			parent.$("#rolePageDiv").gridReload();
			T9.dialogClose();
		}
	});
}
</script>

<div class="pageContent">
	<form id="roleEditForm" data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25" />
		<div class="pageFormContent" layoutH="56">
			<p style="display:none" id="parentRole">
				<label>父权限名称：</label> 
				<input type="hidden" id="PARENTROLEID" name="PARENTROLEID" />				
				<input type="text" id="PARENTROLENAME" name="PARENTROLENAME" />
				<span class="info"></span>
			</p>
			<p>
				<label>权限名称：</label> <input type="text" id="NAME" name="NAME"
					maxlength="30"  data-rule="required"/> <span class="info"></span>
			</p>
			<p>
				<label>备注：</label> <input type="text" id="REMARK" name="REMARK"
					maxlength="250" />
			</p>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

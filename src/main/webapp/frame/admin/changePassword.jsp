<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp"%>

<script>
$(function(){
	T9.post('service/systemUser!getCurrentUser',{},function(result){
		if(result.success){
			
			$("#systemUserPasswordForm").setData(result.obj);
		}
	});
	
});


	jQuery.validator.addMethod("confirmPassword", function(value, element) {
		return $('#PASSWORD').val() == $('#V_PASSWORD').val();
	}, "两次密码输入不一致!");
	
	function saveData() {
		if(!$("#systemUserPasswordForm").valid()) {
			return false;
		} else {
			var param = $('#systemUserPasswordForm').serializeJSON();
			T9.post('service/systemUser!changePassword', param, function(result) {
				if (result.success) {
					T9.alert("保存成功！");
				} else{
					T9.alert(result.message);
				}
			});
		}
		

	}

	
</script>

<div class="pageContent">
	<form id="systemUserPasswordForm">
		<div class="pageFormContent" layoutH="56">

		    <p>
				<label>登录名：</label> <input readonly type="text" id="LOGIN_CODE" name="LOGIN_CODE" maxlength="30" />
			</p>
		    <p>
				<label>名称：</label> <input readonly type="text" id="NAME" name="NAME" maxlength="30" />
			</p>
			<p>
				<label>输入旧密码：</label> <input class="required" type="password" id="OLD_PASSWORD" name="OLD_PASSWORD" maxlength="30" />
			</p>
			
			<p>
				<label>新密码：</label> <input class="required confirmPassword" type="password" id="PASSWORD" name="PASSWORD" maxlength="30" />
			</p>

			<p>
				<label>确认新密码：</label> 
				<input class="required" type="password" id="V_PASSWORD" name="V_PASSWORD"  maxlength="30" />
			</p>

		</div>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button onclick="saveData()">提交</button>
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

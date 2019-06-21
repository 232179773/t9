<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/account!queryAccountById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#accountEditForm").setData(result.obj);
			}
		});
	}
	
});

function saveData() {
	var param=$('#accountEditForm').serializeJSON();
	var url =  'service/account!saveAccount' ;
	if(urlParam.ID){
		url = 'service/account!updateAccount';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#accountPageDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="accountEditForm" data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">	
			<p>
				<label>网站名称：</label>
				<input type="text" id="SITE_NAME" name="SITE_NAME" maxlength="100" data-rule="required"/>
				<span class="info"></span>
			</p>
			<p>
				<label>网站中文名：</label>
				<input type="text" id="PLAT" name="PLAT" maxlength="100"/>
				<span class="info"></span>
			</p>
		    <p>
				<label>网站URL：</label>
				<input   type="text" id="NAME" name="NAME" maxlength="100"/>
			</p>
			
		    <p>
				<label>帐号：</label>
				<input data-rule="required" type="text" id="SITE_ACCOUNT" name="SITE_ACCOUNT" maxlength="30"/>
			</p>
			
		    <p>
				<label>昵称：</label>
				<input type="text" id="NICK_NAME" name="NICK_NAME" maxlength="30"/>
			</p>
		    <p>
				<label>邮箱：</label>
				<input type="text" id="EMAIL" name="EMAIL" maxlength="30"/>
			</p>
		    <p>
				<label>手机号：</label>
				<input type="text" id="MOBILE_PHONE" name="MOBILE_PHONE" maxlength="30"/>
			</p>
		    <p>
				<label>登录密码：</label>
				<input type="text" id="LOGIN_PASSWORD" name="LOGIN_PASSWORD" maxlength="30"/>
			</p>
		    <p>
				<label>支付密码：</label>
				<input type="text" id="PAY_PASSWORD" name="PAY_PASSWORD" maxlength="30"/>
			</p>
		    <p>
				<label>支付渠道：</label>
				<input type="text" id="PAY_TYPE" name="PAY_TYPE" maxlength="30"/>
			</p>
		    <p>
				<label>注册时间：</label>
				<input data-rule="required" type="text" id="REGISTER_TIME" name="REGISTER_TIME" class="my97Date"  readonly="true" />
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

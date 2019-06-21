<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	if(urlParam.ID){
		if(urlParam.get('ROLE_ID')!=true&&urlParam.get('ROLE_NAME')!=true){
			$("#ROLE_ID").val(urlParam.get('ROLE_ID'));
			$("#ROLE_NAME").val(urlParam.get('ROLE_NAME'));
		}
		T9.post('service/systemUser!getSystemUserById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#systemUserForm").setData(result.obj);
			}
		});
	}
	
	$('#USER_RIGHTTYPE').change(function(){
		if(this.value=="1"){
			$('#roleLine').hide();
		}else{
			$('#roleLine').show();
		}
	});
});

function saveData() {
	var param=$('#systemUserForm').serializeJSON();
	var url =  'service/systemUser!addUser' ;
	if(urlParam.ID){
		url = 'service/systemUser!editUser';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#systemUserListDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

function chooseRole(){	
	var param={url : 'frame/admin/roleList.jsp?type=select',
			width:500,
			height:400,
			callback:function(result){
				$('#ROLE_NAME').val(result[0].NAME);	
				$('#ROLE_ID').val(result[0].ID);				
			}
	};
	T9.dialog(param);
}
</script>

<div class="pageContent">
	<form id="systemUserForm" data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
		    <p>
				<label>登录帐号：</label>
				<input data-rule="required" type="text" id="LOGIN_CODE" name="LOGIN_CODE" maxlength="25"/>
			</p>
		    <p>
				<label>名称：</label>
				<input data-rule="required"  type="text" id="NAME" name="NAME" maxlength="100"/>
			</p>
			
		    <p>
				<label>昵称：</label>
				<input data-rule="required" type="text" id="NICKNAME" name="NICKNAME" maxlength="30"/>
			</p>
			
		    <p>
				<label>状态：</label>
				<select name="STATUS" id="STATUS" showNull="false" 
				dataSrc="type=DICT_OPTION&DICT_CODE=USER_STATE"  style="width:120px"></select>
				
			</p>
			
		    <p>
				<label>有效日期：</label>
				
				<input type="text" name="VALIDITY_DATE" id="VALIDITY_DATE" class="my97Date"  readonly="true" />
				
			</p>
			
		    <p>
				<label>账户类型：</label>
				<select name="USER_RIGHTTYPE" id="USER_RIGHTTYPE" showNull="false" 
				dataSrc="type=DICT_OPTION&DICT_CODE=USER_RIGHTTYPE" style="width:120px"></select>
			</p>
				<p id="roleLine">
					<label>所属角色：</label> 
					<input type="hidden" name="ROLE_ID" id="ROLE_ID"/>
					<input data-rule="required" type="text" name="ROLE_NAME" id="ROLE_NAME"/>
					<input type="button" value="选 择" onClick="chooseRole()">
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

<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>
</head>
<script>
$(function() {
	if(urlParam.ID){
		T9.post('service/menu!queryMenuById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#menuEditForm").setData(result.obj);
				$('#PARENT_MENU_MANE').val(urlParam.get('PARENT_MENU_NAME'));
			}
		});
	};
	$('#savebutton').click(function(){
		saveData();
	});
	
});
function saveData() {
	var param=$('#menuEditForm').serializeJSON();
	var url='service/menu!saveMenu';
	if(urlParam.ID){
		url='service/menu!updateMenu';
	}
	T9.post(url,param,function(result){
		if(result.success){
			T9.alert("保存成功！");
			parent.$("#menuPageDiv").gridReload();
			T9.dialogClose();
		}else{
			T9.alert(result.message);
		}
	});
}
function selectMenu(){
	var param={url : '/frame/admin/menuTree.jsp',width:400,height:300};///service/menu!queryMenuTree
	T9.dialog(param);
}
</script>
<div class="pageContent">
	<form id="menuEditForm">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<input type="hidden" id="CODE" name="CODE" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>父菜单名称：</label>
				<input type="hidden" id="PARENT_MENU_ID" name="PARENT_MENU_ID" maxlength="25"/>
				<input type="text" id="PARENT_MENU_MANE" name="PARENT_MENU_MANE" maxlength="25"/>
				<a class="btnLook" onclick="selectMenu()">选择</a>
			</p>
			<p>
				<label>菜单名称：</label>
				<input type="text" id="NAME" name="NAME" maxlength="25"/>
			</p>
			<p>
				<label>菜单地址：</label>
				<input name="URL" class="required" type="text" value=""/>
			</p>
			<p>
				<label>菜单顺序：</label>
				<input name="MENU_ORDER" class="required" type="text" value=""/>
			</p>
			<p>
				<label>菜单状态：</label>
				<select name="ISUSED" dataSrc="type=DICT_OPTION&DICT_CODE=MENU_STATE">
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="savebutton">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

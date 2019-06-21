<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>


<script>
$(function() {
	T9.tree('menuTree','/service/menu!queryMenuTree',{
		id:'ID',pId:'PARENT_MENU_ID',name:'NAME',t:'NAME',
		onClick:function (event, treeId, treeNode, clickFlag) {
			parent.$('#PARENT_MENU_ID').val(treeNode.id);
			parent.$('#PARENT_MENU_MANE').val(treeNode.name);
			T9.dialogClose();
		}	
	});
});

</script>
<div class="pageContent">
<div>
	<ul id="menuTree" class="ztree"></ul>
</div>


<div class="formBar">
	<ul>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
		</li>
	</ul>
</div>
</div>
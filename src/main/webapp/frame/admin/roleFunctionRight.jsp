<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp"%>


<script>
var roleFunctionRightStr="";
	$(function() {
		T9.post('service/role!queryRoleById',{ID : urlParam.ROLE_ID},
				function(result) {
						if (result.success) {
						var params="";
						if(result.obj.parentroleid){
							params={ROLE_ID:result.obj.parentroleid};
						}else{
							params={ROLE_ID:null};
						}
						if(params.ROLE_ID==null){
							T9.tree('menuTree','/service/menu!queryMenuTree',{
								id:'ID',pId:'PARENT_MENU_ID',name:'NAME',t:'NAME',
								checkbox:true,
								onClick:function (event, treeId, treeNode, clickFlag) {
							//		parent.$('#PARENT_MENU_ID').val(treeNode.id);
							//		parent.$('#PARENT_MENU_MANE').val(treeNode.name);
							//		T9.dialogClose();
								}	
							});
							T9.post('service/roleFunctionRight!querySelectedRoleById',{ROLE_ID :  urlParam.ROLE_ID},
									function(result) {
								var objArray=new Array();
								var objectMenu=result.obj;
								for(var i=0,objLength=objectMenu.length;i<objLength;i++){
									objArray.push(objectMenu[i].MENU_ID);
								}
								
								T9.setTreeChecked('menuTree',objArray);
								
							});
						}else{
							T9.post('service/roleFunctionRight!queryTreeByParentId',params,
									function(result){
										if(result.success){
											var objectResult=result.obj;
											var result="";
											for(var i=0;i<objectResult.length;i++){
												result +=objectResult[i].menu_ID+",";
											}
											var params={ID:result};
											T9.tree('menuTree','/service/menu!queryMenuTree?ID='+params.ID,{
												id:'ID',pId:'PARENT_MENU_ID',name:'NAME',t:'NAME',
												checkbox:true,
												onClick:function (event, treeId, treeNode, clickFlag) {
											//		parent.$('#PARENT_MENU_ID').val(treeNode.id);
											//		parent.$('#PARENT_MENU_MANE').val(treeNode.name);
													T9.dialogClose();
												}	
											});
											T9.post('service/roleFunctionRight!querySelectedRoleById',{ROLE_ID :  urlParam.ROLE_ID},
													function(result) {
												var objArray=new Array();
												var objectMenu=result.obj;
												for(var i=0,objLength=objectMenu.length;i<objLength;i++){
													objArray.push(objectMenu[i].MENU_ID);
												}
												
												T9.setTreeChecked('menuTree',objArray);
											});
										}
							});	
						}
						
							
		}
	});
	});
	function saveData() {
		var selectMenuIdArray=T9.getTreeChecked('menuTree');
		var selectMenuIdStr=selectMenuIdArray.join();
		var param={	ROLE_ID : urlParam.ROLE_ID,
				MENU_ID : selectMenuIdStr
			}
		T9.post('service/roleFunctionRight!saveRoleFunctionRight',param, function(result) {
			if (result.success) {
				T9.alert("保存成功");
				T9.dialogClose();
			}else{
				T9.alert(result.message);
			}
		});
	}
</script>
<div class="pageContent">
	<div>
		<ul id="menuTree" class="ztree"></ul>
	</div>


	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button onclick="saveData();">保存</button>
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
</div>
$(function(){

	 $('#rolePageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'radio',width:'10%',key:"ID"},
		    {header:'角色名称',key:"NAME",width:'30%'},
		    {header:'备注',key:"REMARK",width:'20%'},
		    {header:'创建时间',key:"CREATETIME",width:'20%'},
		    {header:'创建人',key:"CREATER",width:'20%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryRole},      
	//	   {name:"新增",bclass: 'add', onpress : addRole},
		   {name:"新增子项",bclass: 'addsub', onpress : addRoleSub,checkedRow:'single'},
		   {name:"修改",bclass: 'edit', onpress : editRole,checkedRow:'single'},
		   {name:"删除",bclass: 'delete', onpress : deleteRole,checkedRow:'multiple'},
		   {name:"角色权限",bclass: 'right', onpress : queryRoleMenu,checkedRow:'single'},
		   {name:"人员管理",bclass: 'detail', onpress : queryRoleUser,checkedRow:'single'}
		],
    	treegrid:'ID,PARENTROLEID',
		url : 'service/role!queryRoleListByPage'
	});
});

function queryRole(value,record){
	var param=$("#roleForm").serializeJSON();
	$("#rolePageDiv").gridOptions(param);
	$("#rolePageDiv").gridReload();
}

function addRole(value,record){
	var param={url : 'frame/admin/roleEdit.jsp',
			title:'添加权限'
	};
	T9.dialog(param);
}

function addRoleSub(value,record){
	var param={url : 'frame/admin/roleEdit.jsp?PARENTROLEID='+value+'&PARENTROLENAME='+record[0].NAME,
			title:'添加权限'
	};
	T9.dialog(param);
}
function editRole(value,record){
	var param={url : 'frame/admin/roleEdit.jsp?ID='+value,
			title:'编辑权限'
	};
	T9.dialog(param);
}
function deleteRole(value,record){
	if($('tr[data-tt-id='+value+'].branch').length!=0){//说明是树干，有子节点
		T9.alert('当前角色还有子节点，不能删除！');
		return;
	}
	T9.confirm("确认要删除选中的信息吗?",function(){
		var params={ID:value};
		T9.post('service/role!deleteRole',params,function(result){
			if(result.success){
				T9.alert("删除成功");
				$("#rolePageDiv").gridReload();
			}else{
				T9.alert(result.message);
			}
		});
	});
	
}

function queryRoleMenu(value,record){
	var param={url : 'frame/admin/roleFunctionRight.jsp?ROLE_ID='+value,
			title:'角色权限'
	};
	T9.dialog(param);
}

function queryRoleUser(value, record){
	var param={url : 'frame/admin/systemUserList_b.jsp?ROLE_ID='+value+'&ROLE_NAME='+record[0].NAME,
			title:'人员管理'
	};
	T9.dialog(param);
}

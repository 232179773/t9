$(function(){	
	if(urlParam.get('ROLE_ID')){
		$("#ROLE_ID").val(urlParam.get('ROLE_ID'));
		$("#ROLE_NAME").val(urlParam.get('ROLE_NAME'));
	}
    $('#systemUserListDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
    	    {header:'账户类型',key:"USER_RIGHTTYPE",render:'dict:USER_RIGHTTYPE',width:'20%'},
    	    {header:'登录帐号',key:"LOGIN_CODE",width:'20%'},
    	    {header:'名称',key:"NAME",width:'20%'},
    	    {header:'状态',key:"STATUS", render:'dict:USER_STATE', width:'10%'},
    	    {header:'有效日期',key:"VALIDITY_DATE",width:'20%'},
    	    {header:'角色ID',key:"ROLE_ID",width:'40%',hide:true},
    	    {header:'角色',key:"ROLE_NAME",width:'20%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryList},
    	   {name:"新增",bclass: 'add', onpress : addUser},
    	   {name:"修改",bclass: 'edit', onpress : editUser,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : delUser,checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'}
    	],
   // 	onSuccess:dataLoadBack
    	params:$("#systemUserListForm").serializeJSON(),
    	
    	url : 'service/systemUser!queryUsersByPage'
    //	autoload:false,
    //	showTotal:false,
   // 	defaultPageSize:5
    });
});


function chooseRole(){	
	var param={url : 'frame/admin/roleList.jsp?type=select',
			width:500,
			height:400,
			callback:function(result){
				$('#ROLE_ID').val(result[0].ID);		
				$('#ROLE_NAME').val(result[0].NAME);			
			}
	};
	T9.dialog(param);
}

function queryList(){
	$("#systemUserListDiv").gridReload();
}

function addUser(value,record){
	var param={url : '/frame/admin/systemUserEdit.jsp',
			title:'新增用户'
		};
	T9.dialog(param);
	//window.location.href="frame/admin/systemUserEdit.jsp";
}

function editUser(value,record){
	var uparam="&ROLE_ID="+record[0].ROLE_ID+"&ROLE_NAME="+record[0].ROLE_NAME;
	var param={url : "/frame/admin/systemUserEdit.jsp?ID="+value+uparam,
			title:'编辑用户'
		};
	T9.dialog(param);
}

function delUser(value,record){
	T9.confirm("确认要删除选中的用户吗?",function(){
		var param={ID:value};
		T9.post('service/systemUser!deleteUser',param,function(result){
				if(result.success){
					T9.alert("删除成功");
					$("#systemUserListDiv").gridReload();
				}else{
					T9.error(result.message);
				}
			});
	});
}

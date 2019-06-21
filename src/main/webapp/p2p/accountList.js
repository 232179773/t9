$(function(){

	 $('#accountPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'网站名称',key:"SITE_NAME",width:'10%'},
		    {header:'帐号',key:"SITE_ACCOUNT",width:'10%'},
		    {header:'邮箱',key:"EMAIL",width:'10%'},
		    {header:'手机号',key:"MOBILE_PHONE",width:'10%'},
		    {header:'登录密码',key:"LOGIN_PASSWORD",width:'10%'},
		    {header:'支付渠道',key:"PAY_TYPE",width:'20%'},
		    {header:'支付密码',key:"PAY_PASSWORD",width:'10%'}
//		    {header:'其他信息',key:"REMARK",width:'10%'},
//		    {header:'创建时间',key:"CREATETIME",width:'3%'},
//		    {header:'修改时间',key:"UPDATETIME",width:'3%'},
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccount},      
		   {name:"新增",bclass: 'add', onpress : addAccount},
		   {name:"修改",bclass: 'edit', onpress : editAccount,checkedRow:'single'},
		   {name:"删除",bclass: 'delete', onpress : deleteAccount,checkedRow:'multiple'}
		],
		pageSize:30,
		url : 'service/account!queryAccountList'
	});
});


function queryAccount(value,record){
	var param=$("#accountForm").serializeJSON();
	$("#accountPageDiv").gridOptions(param);
	$("#accountPageDiv").gridReload();
}

function addAccount(value,record){
	var param={url : 'p2p/accountEdit.jsp?',
			title:'新增'
	};
	T9.dialog(param);
}
function editAccount(value,record){
	
	var param={url : 'p2p/accountEdit.jsp?ID='+value+'&MANAGER_NAME='+encodeURIComponent(record[0].NAME),
			title:'修改'
	};
	T9.dialog(param);
}
function deleteAccount(value,record){
	T9.alert("不能删除该信息！！！");
}
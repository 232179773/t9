$(function(){

	 $('#accountLogPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'网站名称',key:"SITE_NAME",width:'10%'},
		    {header:'流水类型',key:"LOG_TYPE",render:'dict:P2P_ACCOUNT_LOG_TYPE',width:'5%'},
		    {header:'还款状态',key:"GATHER_STATE",render:'dict:P2P_GATHER_STATE',width:'5%'},
		    {header:'发生时间',key:"DUE_TIME",cellRenderer:function(value,record){return value.substring(0,10);},width:'8%'},
		    {header:'金额',key:"MONEY",width:'7%'},
		    {header:'还款方式',key:"REPAMENT_TYPE",render:'dict:P2P_REPAMENT_TYPE',width:'10%'},
		    {header:'投标周期',key:"TENDER_DURATION",width:'3%'},
		    {header:'应收本金',key:"COLLECTION_MONEY",width:'7%'},
		    {header:'应收利息',key:"EARN_MONEY",width:'7%'},
		    {header:'投标标题',key:"BID_TITLE",width:'20%'},
		    {header:'投标URL',key:"BID_URL",width:'20%'}
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccountLog},      
		   {name:"新增",bclass: 'add', onpress : addAccountLog},
		   {name:"修改",bclass: 'edit', onpress : editAccountLog,checkedRow:'single'},
		   {name:"删除",bclass: 'delete', onpress : deleteAccountLog,checkedRow:'single',confirm:'确认要删除选中的信息吗?'}
		],
    	params:$("#accountLogForm").serializeJSON(),
		pageSize:30,
		url : 'service/accountLog!queryAccountLogList'
	});
});


function queryAccountLog(value,record){
	var param=$("#accountLogForm").serializeJSON();
	$("#accountLogPageDiv").gridOptions(param);
	$("#accountLogPageDiv").gridReload();
}

function addAccountLog(value,record){
	var param={url : 'p2p/accountLogEdit.jsp?',
			width:500,
			height:400,
			title:'增加记录'
	};
	T9.dialog(param);
}
function editAccountLog(value,record){
	if(record[0].GATHER_STATE=="1"){
		alert("已还款记录不允许修改！！");
		return;
	}
	var param={url : 'p2p/accountLogEdit.jsp?ID='+value+'&MANAGER_NAME='+encodeURIComponent(record[0].NAME),
			width:500,
			height:400,
			title:'修改记录'
	};
	T9.dialog(param);
}
function deleteAccountLog(value,record){
	var param={ID:value};
	T9.post('service/accountLog!deleteAccountLog',param,function(result){
			if(result){
				T9.alert("删除成功");
				$("#accountLogPageDiv").gridReload();
			}
	});
}
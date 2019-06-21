$(function(){

	 $('#moneyLogPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'网站名称',key:"SITE_NAME",width:'10%'},
		    {header:'金额',key:"MONEY",width:'10%'},
		    {header:'发生时间',key:"DUE_TIME",cellRenderer:function(value,record){return value.substring(0,10);},width:'10%'},
		    {header:'支付银行',key:"PAY_BANK",width:'10%'},
		    {header:'支付渠道',key:"PAY_TYPE",width:'10%'},
		    {header:'支付订单号',key:"PAY_ORDERNO",width:'10%'}
//		    {header:'配送范围',key:"DISTRIBUTION_RANGE",render:'dict:STORE_STATE',width:'20%'},
//		    {header:'其他信息',key:"REMARK",width:'10%'},
//		    {header:'创建时间',key:"CREATETIME",width:'3%'},
//		    {header:'修改时间',key:"UPDATETIME",width:'3%'},
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryMoneyLog},      
		   {name:"新增",bclass: 'add', onpress : addMoneyLog},
		   {name:"修改",bclass: 'edit', onpress : editMoneyLog,checkedRow:'single'},
		   {name:"删除",bclass: 'delete', onpress : deleteMoneyLog,checkedRow:'multiple'},
		   {name:"导出",bclass: 'exp'}
		],
    	params:$("#moneyLogForm").serializeJSON(),
		pageSize:30,
		url : 'service/moneyLog!queryMoneyLogList'
	});
});


function queryMoneyLog(value,record){
	var param=$("#moneyLogForm").serializeJSON();
	$("#moneyLogPageDiv").gridOptions(param);
	$("#moneyLogPageDiv").gridReload();
}

function addMoneyLog(value,record){
	var param={url : 'p2p/moneyLogEdit.jsp?',
			title:'账户添加'
	};
	T9.dialog(param);
}
function editMoneyLog(value,record){
	
	var param={url : 'p2p/moneyLogEdit.jsp?ID='+value+'&MANAGER_NAME='+encodeURIComponent(record[0].NAME),
			title:'店铺修改'
	};
	T9.dialog(param);
}
function deleteMoneyLog(value,record){
	T9.alert("不能删除该信息！！！");
}
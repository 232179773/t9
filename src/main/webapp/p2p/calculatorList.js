$(function(){

	 $('#calculatorPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'发生期数',key:"DUE_TIME",cellRenderer:function(value,record){return value;},width:'8%'},
		    {header:'金额',key:"MONEY",width:'7%'},
		    {header:'应收本金',key:"COLLECTION_MONEY",width:'7%'},
		    {header:'应收利息',key:"EARN_MONEY",width:'7%'},
		    {header:'收入',key:"EARN_TOTAL_MONEY",width:'6%'},
		    {header:'代收本金',key:"DAI_COLLECTION_MONEY",width:'7%'},
		    {header:'代收利息',key:"DAI_EARN_MONEY",width:'7%'}
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccountLog}
		],
    	params:$("#calculatorForm").serializeJSON(),
		pageSize:30,
		url : 'service/accountLog!queryCalculatorList'
	});
});


function queryAccountLog(value,record){
	var param=$("#calculatorForm").serializeJSON();
	$("#calculatorPageDiv").gridOptions(param);
	$("#calculatorPageDiv").gridReload();
}

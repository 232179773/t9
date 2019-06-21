$(function(){

	 $('#calculatorPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'发生期数',key:"DUE_TIME",width:'8%'},
		    {header:'本金总额',key:"COLLECTION_MONEY",width:'7%'},
		    {header:'当期利息',key:"EARN_MONEY",width:'7%'},
		    {header:'利息总额',key:"EARN_MONEY_TOTAL",width:'7%'},
		    {header:'总金额',key:"TOTAL_MONEY",width:'20%'}
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccountLog}
		],
    	params:$("#calculatorForm").serializeJSON(),
		pageSize:30,
		url : 'service/accountLog!queryCalculatorCompList'
	});
});


function queryAccountLog(value,record){
	var param=$("#calculatorForm").serializeJSON();
	$("#calculatorPageDiv").gridOptions(param);
	$("#calculatorPageDiv").gridReload();
}

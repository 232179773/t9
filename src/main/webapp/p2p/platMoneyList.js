$(function(){

	 $('#platPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
		    {header:'平台',key:"plat",width:'7%'},
		    {header:'时间',key:"yifei",width:'20%'}
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccountLog},
		   {name:"详情",bclass: 'view', onpress : viewPlatHist2,checkedRow:'single'}
		],
    	params:$("#calculatorForm").serializeJSON(),
		pageSize:30,
		autoload:true,
		dbCheckedRow:viewPlatHist,
		url : 'service/plat!queryPlatList'
	});
});


function queryAccountLog(value,record){
	var param=$("#platForm").serializeJSON();
	$("#platPageDiv").gridOptions(param);
	$("#platPageDiv").gridReload();
}

function viewPlatHist2(value,record){
	viewPlatHist(record[0]);
}

function viewPlatHist(record){
	var param={url : 'p2p/platHistList.jsp?plat='+encodeURIComponent(record.plat),
			title:'详情'
	};
	T9.dialog(param);
}

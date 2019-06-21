$(function(){
	var plat=urlParam.plat;
	$("#PLAT").val(plat);
	 $('#platPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'checkbox',width:'10px',key:"ID"},
		    {header:'MONTH',key:"MONTH",cellRenderer:function(value,record){return value;},width:'50px'},
		    {header:'平台',key:"plat",width:'60px'},
		    {header:'rank',key:"rank",width:'30px'},
		    {header:'投之家',key:"tzj",width:'50px'},
		    {header:'天眼',key:"eye",width:'40px'},
		    {header:'融360',key:"rong",width:'40px'},
		    {header:'奕飞',key:"yifei",width:'40px'}
//		    {header:'创建人',key:"CREATER",width:'3%'},
//		    {header:'修改人',key:"UPDATER",width:'3%'},
		],
		buttons:[
		   {name:"查询",bclass: 'query', onpress : queryAccountLog}
		],
    	params:$("#platForm").serializeJSON(),
		pageSize:30,
		url : 'service/plat!queryPlatList'
	});
});


function queryAccountLog(value,record){
	var param=$("#platForm").serializeJSON();
	$("#platPageDiv").gridOptions(param);
	$("#platPageDiv").gridReload();
}

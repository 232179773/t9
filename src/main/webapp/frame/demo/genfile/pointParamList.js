$(function(){
    $('#pointParamDiv').frontgrid({
    	columns:[    	
    	   		{header:'性能参数标识',key:"I_PARAM_ID",width:'10%'},
    	   		{header:'性能参数分组名称',key:"S_PARAM_GROUP_NAME",width:'10%'},
    	   		{header:'性能参数名称',key:"S_PARAM_NAME",width:'10%'},
    	   		{header:'性能参数英文名称',key:"S_PARAM_EN_NAME",width:'10%'},
    	   		{header:'性能参数说明',key:"S_PARAM_DESC",width:'10%'},
    	   		{header:'性能参数小数点精度',key:"I_DECIMAL_DIGITS",width:'10%'},
    	   		{header:'性能参数单位',key:"S_PARAM_UNIT",width:'10%'},
    	   		{header:'性能值允许最大值',key:"I_PARAM_VALUE_MAX",width:'10%'},
    	   		{header:'性能值允许最小值',key:"I_PARAM_VALUE_MIN",width:'10%'},
    	   		{header:'性能参数补0值',key:"I_FILL_ZERO_VALUE",width:'10%'},
    	   		{header:'最大值设置是否有效',key:"I_ENABLE_THRES_MAX",width:'10%'},
    	   		{header:'最小值设置是否有效',key:"I_ENABLE_THRES_MIN",width:'10%'},
    	   		{header:'数据域ID',key:"I_DATA_DOMAIN_ID",width:'10%'},
    	   		{header:'最后更新时间',key:"D_UPDATE_TIME",width:'10%'},
    	   		{header:'聚合算法',key:"I_POLY_ALGORITHM",width:'10%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'},
    	   {name:"新增",bclass: 'add',onpress : addPointParam},
    	   {name:"修改",bclass: 'edit', onpress : editPointParam,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : deletePointParam,checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'},
    	   {name:"导入",bclass: 'imp'},
    	   {name:"导出",bclass: 'exp'}
    	],
    	height:0,
   // 	onSuccess:dataLoadBack,
     	pageSize:15,
     	params:{'type':1},
     	formId:'pointParamForm',
    	url : 'service/demo!queryDemoList'
    //	autoload:false,
    //	showTotal:false,
    });
});

function queryPointParam(){
//	var params=$("#staffQueryForm").serializeJSON();
//	$("#pointParamDiv").gridOptions(params);	
	$("#pointParamDiv").gridReload();
}
function addPointParam(value,records){
	var param={url : '/frame/demo/demoEdit.jsp',
			title:'人员编辑'
		};
	T9.dialog(param);
}
function editPointParam(value,records){
	var param={url : "/frame/demo/demoEdit.jsp?ID="+value};
	T9.dialog(param);
}
function deletePointParam(value,records){
	var param={ID:value};
	T9.post('service/demo!deleteDemo',param,function(result){
			if(result){
				T9.alert("删除成功");
				$("#pointParamDiv").gridReload();
			}
	});
}
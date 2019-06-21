$(function(){
    $('#${APP_PATH}PageDiv').frontgrid({
    	columns:[    	
		   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
			<#list columnList as col>
    	   		{header:'${col.COL_NAME}',key:"${col.COL_CODE}",<#if col.DATA_TYPE??><#if col.DATA_TYPE=="open" && col.DATA_SOURCE?index_of("{")==-1 >render:"table:${col.DATA_SOURCE}",<#elseif col.DATA_TYPE=="dict">render:"dict:${col.DATA_SOURCE}",<#elseif col.DATA_TYPE=="sql">render:"sql:${col.DATA_SOURCE}",</#if></#if><#if col.SHOW_LIST!="1">hide:true,</#if><#if col.COL_USE=="2">name:true,</#if>width:'10%'}<#if col_index!=(columnList?size-1)>,</#if>
			</#list>
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'},
    	   {name:"新增",bclass: 'add',onpress : add${CLASS_PATH}},
    	   {name:"修改",bclass: 'edit', onpress : edit${CLASS_PATH},checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : delete${CLASS_PATH},checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'}
    	],
    	height:0,
   // 	onSuccess:dataLoadBack,
     	pageSize:30,
     	params:{'type':1},
     	formId:'${APP_PATH}QueryForm',
     	url : 'service/${APP_PATH}!queryList',
    //	autoload:false,
    //	showTotal:false,
    });
});

function query${CLASS_PATH}(){
//	var params=$("#${APP_PATH}QueryForm").serializeJSON();
//	$("#${APP_PATH}PageDiv").gridOptions(params);	
	$("#${APP_PATH}PageDiv").gridReload();
}
function add${CLASS_PATH}(value,records){
	var param={url : '/frame/demo/${APP_PATH}Edit.jsp'};
	T9.dialog(param);
}
function edit${CLASS_PATH}(value,records){
	var param={url : '/frame/demo/${APP_PATH}Edit.jsp?ID='+value};
	T9.dialog(param);
}
function delete${CLASS_PATH}(value,records){
	var param={ID:value};
	T9.post('service/${APP_PATH}!deleteEntityById',param,function(result){
			if(result){
				T9.alert("删除成功");
				$("#${APP_PATH}PageDiv").gridReload();
			}
	});
}
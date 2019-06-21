$(function(){
    $('#systemConfigListDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
    	    {header:'编码',key:"CODE",width:'20%'},
    	    {header:'名称',key:"NAME",width:'20%'},
    	    {header:'值',key:"VALUE",width:'50%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryList},
    	   {name:"修改",bclass: 'edit', onpress : editSystemConfig,checkedRow:'single'}
    	],
   // 	onSuccess:dataLoadBack,
    	url : 'service/systemConfig!queryConfigByPage'
    //	autoload:false,
    //	showTotal:false,
   // 	defaultPageSize:5
    });
});

function queryList(){
	var param=$("#systemConfigListForm").serializeJSON();
	$("#systemConfigListDiv").gridOptions(param);
	$("#systemConfigListDiv").gridReload();
}


function editSystemConfig(value,record){
	var param={url : "/frame/admin/systemConfigEdit.jsp?ID="+value,
		close:function(){
			$("#systemConfigListDiv").gridReload();
			return true;}
		};
	T9.dialog(param);
}

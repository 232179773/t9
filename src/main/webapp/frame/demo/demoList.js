$(function(){
    $('#demoPageDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'radio',width:'10%',key:"ID"},
    	    {header:'用户名称',key:"USER_NAME",width:'20%',cellRenderer:function(value,record){return '<a>'+value+'</a>';}},
    	    {header:'电话号码',key:"PHONE_NO",width:'40%'},
    	    {header:'更新时间',key:"CREATE_TIME",width:'30%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'},
    	   {name:"新增",bclass: 'add',onpress : addDemoMain},
    	   {name:"修改",bclass: 'edit', onpress : editDemoMain,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : deleteDemo,checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'},
    	   {name:"导入",bclass: 'imp'},
    	   {name:"导出",bclass: 'exp'},
    	   {name:"全部导出",bclass: 'expAll'},
    	   {name:"查看子项",bclass: 'querysub'},
    	   {name:"新增子项",bclass: 'addsub'},
    	   {name:"选择",bclass: 'select'},
    	   {name:"详情",bclass: 'detail'},
    	   {name:"确定",bclass: 'sure'},
    	   {name:"审核",bclass: 'audit'},
    	   {name:"权限",bclass: 'right'},
    	   {name:"取消",bclass: 'cancel'}
    	],
    	height:0,
   // 	onSuccess:dataLoadBack,
     	pageSize:15,
     	params:{'type':1},
     	formId:'staffQueryForm',
    	url : 'service/demo!queryDemoList'
    //	autoload:false,
    //	showTotal:false,
    });
});

function queryDemo(){
//	var params=$("#staffQueryForm").serializeJSON();
//	$("#demoPageDiv").gridOptions(params);	
	$("#demoPageDiv").gridReload();
}
function addDemoMain(value,records){
	var param={url : '/frame/demo/demoEdit.jsp',
			title:'人员编辑'
		};
	T9.dialog(param);
}
function editDemoMain(value,records){
	var param={url : "/frame/demo/demoEdit.jsp?ID="+value};
	T9.dialog(param);
}
function deleteDemo(value,records){
	var param={ID:value};
	T9.post('service/demo!deleteDemo',param,function(result){
			if(result){
				T9.alert("删除成功");
				$("#demoPageDiv").gridReload();
			}
	});
}
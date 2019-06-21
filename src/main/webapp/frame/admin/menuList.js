$(function(){	
	$('#menuPageDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'5%',key:"ID"},
    	    {header:'菜单名称',key:"NAME",width:'20%'},
    	    {header:'菜单地址',key:"URL",width:'20%'},
    	    {header:'菜单顺序',key:"MENU_ORDER",width:'5%'},
    	    {header:'父菜单名称',key:"PARENT_MENU_NAME",width:'10%'},
    	    {header:'状态',key:"ISUSED",render:'dict:MENU_STATE',width:'5%'},
    	    {header:'备注',key:"REMARK",width:'20%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryMenu},
    	   {name:"新增",bclass: 'add', onpress : addMenu},
    	   {name:"修改",bclass: 'edit', onpress : editMenu,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : deleteMenu,checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'}
    	],
    	treegrid:'ID,PARENT_MENU_ID',
    	height:0,
   // 	onSuccess:dataLoadBack,
    	url : 'service/menu!queryMenuList'
    //	autoload:false,
    //	showTotal:false,
    });
});

function queryMenu(){
	var param=$("#menuQueryForm").serializeJSON();
	$("#menuPageDiv").gridOptions(param);
	$("#menuPageDiv").gridReload();
}
function addMenu(value,record){
	var param={url : '/frame/admin/menuEdit.jsp'};
	T9.dialog(param);
}
function editMenu(value,record){
	var param={url : "/frame/admin/menuEdit.jsp?ID="+value+"&PARENT_MENU_NAME="+record[0].PARENT_MENU_NAME};
	T9.dialog(param);
}
function deleteMenu(value,record){
	var param={ID:value};
	T9.post('service/menu!deleteMenu',param,function(result){
			if(result){
				T9.alert("删除成功");
				$("#menuPageDiv").gridReload();
			}
		});
}
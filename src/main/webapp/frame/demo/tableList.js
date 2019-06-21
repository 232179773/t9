$(function(){

    $('#columnListDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'radio',width:'10%',key:"ID"},
    	    {header:'字段编码',key:"COL_CODE",width:'15%'},
    	    {header:'字段名称',key:"COL_NAME",width:'20%'},
    	    {header:'顺序',key:"COL_ORDER",width:'3%'},
    	    {header:'Java类型',key:"JAVA_TYPE",width:'4%'},
    	    {header:'大小',key:"DATA_LENGTH",width:'3%'},
    	    {header:'小数位数',key:"DATA_SCALE",width:'3%'},
    	    {header:'是否为空',key:"NULLABLE",width:'3%'},
    	    {header:'字段用途',key:"COL_USE",render:'dict:FAST_COL_USE',width:'5%'},
    	    {header:'控件类型',key:"HTML_TYPE",width:'5%'},
    	    {header:'字段类型',key:"DATA_TYPE",width:'5%'},
    	    {header:'字段来源',key:"DATA_SOURCE",width:'20%'},
    	    {header:'影响对象',key:"REF_COL",width:'10%'},
    	    {header:'列表显示',key:"SHOW_LIST",width:'3%'},
    	    {header:'新增显示',key:"SHOW_ADD",width:'3%'},
    	    {header:'编辑显示',key:"SHOW_EDIT",width:'3%'},
    	    {header:'查询显示',key:"SHOW_QUERY",width:'3%'},
    	    {header:'其他设置',key:"SHOW_REMARK",width:'10%'},
    	    {header:'ID',key:"ID",hide:true,width:'10%'},
    	    {header:'COL_TYPE',key:"COL_TYPE",hide:true,width:'10%'},
    	    {header:'JAVA_COL',key:"JAVA_COL",hide:true,width:'10%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'},
    	   {name:"修改",bclass: 'edit', onpress : editTableColumn,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : deleteColumn,checkedRow:'multiple',confirm:'确认要删除选中的信息吗?'}
    	],
    	height:0,
    //	pageSize:8,
     	formId:'tableQueryForm',
    	autoload:false,
    	url : 'service/fastFile!queryColumn'
    //	showTotal:false,
    });
    
    $('#tableListDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'radio',width:'10%',key:"ID"},
    	    {header:'表英文名',key:"TABLE_CODE",width:'20%',cellRenderer:function(value,record){return '<a>'+value+'</a>';}},
    	    {header:'表中文名',key:"APP_NAME",width:'40%'},
    	    {header:'唯一索引',key:"UNIQUE_COL",width:'10%'},
    	    {header:'表扩展',key:"COL_USE",width:'10%'},
    	    {header:'表备注',key:"TABLE_COMMENT",width:'10%'},
    	    {header:'扩展按钮',key:"TOOLBAR",width:'10%'},
    	    {header:'应用路径',key:"APP_PATH",width:'10%'},
    	    {header:'类路径',key:"CLASS_PATH",width:'10%'},
    	    {header:'包名',key:"PACKAGE",width:'10%'},
    	    {header:'日志模块',key:"LOG_ID",hide:true,width:'10%'},
    	    {header:'主键字段',key:"KEY_COLUMN",hide:true,width:'10%'},
    	    {header:'生成文件',key:"GEN_FILE",hide:true,width:'10%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'},
    	   {name:"导入",bclass: 'add',onpress : pdmImport},
    	   {name:"新增",bclass: 'add',onpress : addDemoMain},
    	   {name:"修改",bclass: 'edit', onpress : editDemoMain,checkedRow:'single'},
    	   {name:"删除",bclass: 'delete', onpress : deleteTable,checkedRow:'single',confirm:'确认要删除选中的信息吗?'},
    	   {name:"预览",bclass: 'edit', onpress : previewTable,checkedRow:'single'},
    	   {name:"下载",bclass: 'edit', onpress : downLoadCode,checkedRow:'single'}
    	],
    	height:130,
    	pageSize:5,
     	formId:'tableQueryForm',
    	checkedRow:initTableColumn,
    	url : 'service/fastFile!queryTable'
    //	autoload:false,
    //	showTotal:false,
    });
    
});
function initTableColumn(tableRecord){
	$("#columnListDiv").setNoData();
	if(tableRecord==undefined){
		return;
	}
	var param={TABLE_CODE:tableRecord.TABLE_CODE};//测试用的数据写死的
	$("#columnListDiv").gridOptions(param);
	$("#columnListDiv").gridReload();
}

function pdmImport(value,records){
	var param={url : '/frame/demo/pdmInfo.jsp',
			title:'PDM导入',
			callback:function(result){
				alert(result.tableName);	
				var param={"TABLE_PARENT":result.PARENT,"TABLE_NAME":result.tableName,"TABLE_CODE":result.tableCode};
				T9.post('service/fastFile!savePdm',param,function(result){
					if(result.success){
						T9.alert("导入成功");
						$("#tableListDiv").gridReload();
					}else{
						T9.alert(result.message);
					}
				});
			}
		};
	T9.dialog(param);
}

function addDemoMain(value,records){
	var param={url : '/frame/demo/pdmInfo.jsp',
			title:'人员编辑'
		};
	T9.dialog(param);
}
function editDemoMain(value,records){
	var param={url : "/frame/demo/tableEdit.jsp?ID="+value};
	T9.dialog(param);
}

function editTableColumn(value,records){
	var param={url : "/frame/demo/tableColumnEdit.jsp?ID="+value};
	T9.dialog(param);
}

function deleteColumn(value,records){
	var param={TABLE_CODE:records[0].TABLE_CODE};
	T9.post('service/fastFile!deleteColumn',param,function(result){
			if(result.success){
				T9.alert("删除成功");
				$("#tableListDiv").gridReload();
			}else{
				T9.alert(result.message);
			}
	});
}
function deleteTable(value,records){
	var param={TABLE_CODE:records[0].TABLE_CODE};
	T9.post('service/fastFile!deleteTable',param,function(result){
			if(result.success){
				T9.alert("删除成功");
				$("#tableListDiv").gridReload();
			}else{
				T9.alert(result.message);
			}
	});
}
function previewTable(value,records){
	var param={url : "/service/fastFile!pageView?TABLE_CODE="+records[0].TABLE_CODE};
	T9.openWindow(param);
}

function downLoadCode(value,records){
	$("#downLoadForm").attr("action",base_path+"/service/fastFile!downLoadFile?TABLE_CODE="+records[0].TABLE_CODE);
	$("#downLoadForm").submit();
}
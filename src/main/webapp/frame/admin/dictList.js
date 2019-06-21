$(function(){

	 $('#dictOptionPageDiv').frontgrid({
		columns:[
		   	{header:'',ctrl:'radio',width:'5%',key:"ID"},
	//	    {header:'字典类编码',key:"DICT_CODE",width:'20%'},
		    {header:'字典项值',key:"OPTION_VALUE",width:'20%'},
		    {header:'字典项名称',key:"OPTION_NAME",width:'20%'},
		    {header:'字典项顺序',key:"ORDER_NO",width:'10%'},
		    {header:'是否可编辑',key:"IS_MODIFI",width:'10%'}
		],
		buttons:[
		   {name:"新增",bclass: 'add', onpress : addDictOption,id:'addDictOptionId'},
		   {name:"修改",bclass: 'edit', onpress : editDictOption,checkedRow:'single',id:'editDictOptionId'},
		   {name:"删除",bclass: 'delete', onpress : deleteDictOption,checkedRow:'multiple',id:'deleteDictOptionId',confirm:'确认要删除选中的信息吗?'},
		],
		autoload:false,
		url : 'service/dictOption!queryDictOptionList'
	});
	 
    $('#dictPageDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'radio',width:'10%',key:"ID"},    	    
    	    {header:'字典类编码',key:"DICT_CODE",width:'20%'},
    	    {header:'字典类名',key:"DICT_NAME",width:'30%'},
    	    {header:'字典项类型',key:"DICT_TYPE", render:'dict:SYS_DICT_TYPE',width:'30%'},
    	    {header:'字典描述',key:"REMARK",width:'30%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryDict},
		   {name:"新增",bclass: 'add', onpress : addDict},
		   {name:"修改",bclass: 'edit', onpress : editDict,checkedRow:'single'},
		   {name:"删除",bclass: 'delete', onpress : deleteDict,checkedRow:'single',confirm:'确认要删除选中的信息吗?'}
    	],
    	height:200,
    	pageSize:8,
    	checkedRow:initDictOption,
    	url : 'service/dict!queryDictList'
    });
	  
});

function queryDict(){
	var param=$("#dictForm").serializeJSON();
	$("#dictPageDiv").gridOptions(param);
	$("#dictPageDiv").gridReload();
}

function addDict(value,record){
	var param={url : 'frame/admin/dictEdit.jsp',
			title:'属性编辑'
	};
	T9.dialog(param);
}

function editDict(value,record){
	var param={url : 'frame/admin/dictEdit.jsp?ID='+value,
			title:'属性编辑'
	};
	T9.dialog(param);
}

function deleteDict(value,record){
	var dictCodes=$.getValueFromArray(record,"DICT_CODE");
	var params={DICT_CODE:dictCodes,ID:value};
	T9.post('service/dict!deleteDict',params,function(result){
		if(result){
			T9.alert("删除成功");
			$("#dictPageDiv").gridReload();
		}
	});
}

function initDictOption(dictRecord){
	$("#dictOptionPageDiv").setNoData();
	if(dictRecord==undefined){
		return;
	}
	var param={DICT_CODE:dictRecord.DICT_CODE};
	$("#dictOptionPageDiv").gridOptions(param);
	$("#dictOptionPageDiv").gridReload();
}

function addDictOption(value,record){
	var dictCode=$("#dictPageDiv").getCheckedArray()[0]["DICT_CODE"];
	var param={url : 'frame/admin/dictOptionEdit.jsp?DICT_CODE='+dictCode,
			title:'字典项添加'
	};
	T9.dialog(param);
}


function editDictOption(value,record){
	var param={url : 'frame/admin/dictOptionEdit.jsp?ID='+value,
			title:'字典项修改'
	};
	T9.dialog(param);
}

function deleteDictOption(value,record){
	var params={ID:value};
	T9.post('service/dictOption!deleteDictOption',params,function(result){
		if(result.success){
			T9.alert("删除成功");
			$("#dictOptionPageDiv").gridReload();
		}else{
			T9.alert(result.message);
		}
	});
}



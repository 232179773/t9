<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<SCRIPT type=text/javascript>
$(function(){
    $('#appendixPageDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
    	    {header:'表名',key:"TABLE_NAME",width:'10%'},
    	    {header:'记录ID',key:"DATA_ID",width:'10%'},
    	    {header:'文件名',key:"FILE_NAME",width:'10%'},
    	    {header:'文件大小',key:"FILE_SIZE",width:'10%'},
    	    {header:'创建时间',key:"CREATETIME",width:'30%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryRole},
    	   {name:"删除",bclass: 'delete', onpress : deleteRole,checkedRow:'multiple'}
    	],
   // 	onSuccess:dataLoadBack,
    	url : 'service/appendix!queryAppendixList'
    //	autoload:false,
    //	showTotal:false,
   // 	defaultPageSize:5
    });
});

function queryRole(){
	var param=$("#appendixQueryForm").serializeJSON();
	$("#appendixPageDiv").gridOptions(param);
	$("#appendixPageDiv").gridReload();
}
function deleteRole(value,record){
	T9.confirm("确认要删除选中的信息吗?",function(){
		var param={ID:value};
		T9.post('service/appendix!deleteAppendix',param,function(result){
				if(result){
					T9.alert("删除成功");
					$("#appendixPageDiv").gridReload();
				}
			});
	});
}
</SCRIPT>
<div class="pageHeader">
	<form id="appendixQueryForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					表名:<input type="text" name="TABLE_NAME" />
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="appendixPageDiv" class="pageContent"></div>

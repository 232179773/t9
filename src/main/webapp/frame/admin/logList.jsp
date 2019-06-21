<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<SCRIPT type=text/javascript>
$(function(){	
	 $('#queryLogForm').setUrlParam();
    $('#logPageDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'10%',key:"ID"},
    	    {header:'用户名称',key:"STAFF_NAME",width:'10%'},
    	    {header:'操作IP',key:"CLIENT_IP",width:'10%'},
    	    {header:'操作时间',key:"LOG_TIME",width:'10%'},
    	    {header:'操作模块',key:"LOG_TITLE",width:'10%'},
    	    {header:'操作内容',key:"LOG_CONTENT",width:'30%'}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query', onpress : queryRole},
    	   {name:"删除",bclass: 'delete', onpress : deleteRole,checkedRow:'multiple'}
    	],
   // 	onSuccess:dataLoadBack,
    	url : 'service/log!queryLogList'
    //	autoload:false,
    //	showTotal:false,
   // 	defaultPageSize:5
    });
});

function queryRole(){
	var param=$("#queryLogForm").serializeJSON();
	$("#logPageDiv").gridOptions(param);
	$("#logPageDiv").gridReload();
}
function deleteRole(value,record){
	T9.confirm("确认要删除选中的信息吗?",function(){
		var param={ID:value};
		T9.post('service/log!deleteLog',param,function(result){
				if(result){
					T9.alert("删除成功");
					$("#logPageDiv").gridReload();
				}
			});
	});
}
</SCRIPT>
<div class="pageHeader">
	<form id="queryLogForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：<input type="text" name="STAFF_NAME" />
				</td>
				<td>
					操作IP：<input type="text" name="CLIENT_IP" />
				</td>
				<td>
					操作时间：<input type="text" name="LOG_TIME_START" id="LOG_TIME_START" class="my97Date" maxDate="#F{$dp.$D('LOG_TIME_END')}" readonly="true"/>
				</td>
				<td>~</td>
				<td>
					<input type="text" name="LOG_TIME_END" id="LOG_TIME_END" class="my97Date" minDate="#F{$dp.$D('LOG_TIME_START')}" readonly="true"/>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="logPageDiv" class="pageContent"></div>

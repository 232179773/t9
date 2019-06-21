<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script>
$(function() {
	$('#pdmTableDiv').frontgrid({
    	columns:[
    	   	{header:'',ctrl:'checkbox',width:'5%',key:"ID"},
    	    {header:'表中文名',key:"tableName",width:'30%'},
    	    {header:'表英文名',key:"tableCode",width:'20%'},
    	    {header:'备注',key:"REMARK",width:'20%'}
    	],
    	treegrid:'NAME,PARENT',
    	height:0,
    	dbCheckedRow:dbCheckedRow,
    	buttons:[
    	   {name:"查询",bclass: 'query'}    	   
    	],
   // 	onSuccess:dataLoadBack,
    	url : 'service/fastFile!findTableList'
    //	autoload:false,
    //	showTotal:false,
    });
});
function dbCheckedRow(record) {
	window.parent.returnResult=record;
	T9.dialogClose();
}
</script>
<div class="pageContent">
	<p>
		<label>文件名：</label>
		<input type="text" id="USER_NAME" name="USER_NAME" maxlength="25"/>
	</p>
	
	<div id="pdmTableDiv" class="pageContent"></div>
</div>

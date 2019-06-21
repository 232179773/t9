<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<SCRIPT type=text/javascript>
$(function(){
    $('#logPageDiv').frontgrid({
    	columns:[
    	    {header:'操作时间',key:"PVSTAT_TYPE",width:'10%'},
    	    {header:'PV值',key:"PVSTAT_COUNT",width:'10%',cellRenderer:function(value,record){
    	    	var type=$(':radio[checked]').val();
    	    	if(type=='month'||type=='year'){
    	    		return '<a onclick="openDetail(\''+type+'\',\''+record.PVSTAT_TYPE+'\')">'+value+'</a>';
    	    	}
    	    	return value;
    	    }}
    	],
    	buttons:[
    	   {name:"查询",bclass: 'query'}
    	],
   		formId:'queryLogForm',
    	url : 'service/log!pvStat'
    });
    
    $('#STAT_CYCLE_DAY').click(function(){
    	$('#timeEndSpan').hide();
    	$("#logPageDiv").gridReload();
    });
    
    $('#STAT_CYCLE_MONTH').click(function(){
    	$('#timeEndSpan').show();
    	$("#logPageDiv").gridReload();
    });
    
    $('#STAT_CYCLE_YEAR').click(function(){
    	$('#timeEndSpan').hide();
    	$("#logPageDiv").gridReload();
    });
});

function openDetail(type,value){
	var param={};
	if(type=='month'){
		var param={url : 'frame/admin/logList.jsp?LOG_TIME_START='+value+'&LOG_TIME_END='+value,
				title:value+'明细'
		};
	}else if(type=='year'){
		var param={url : 'frame/admin/logList.jsp?LOG_TIME_START='+value+'01'+'&LOG_TIME_END='+value,
				title:value+'明细'
		};
	}
	T9.dialog(param);
}
</SCRIPT>
<div class="pageHeader">
	<form id="queryLogForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					统计周期：<input type="radio" id="STAT_CYCLE_DAY" name="STAT_CYCLE" checked value="day">天
					<input type="radio" id="STAT_CYCLE_MONTH" name="STAT_CYCLE" value="month">月
					<input type="radio" id="STAT_CYCLE_YEAR" name="STAT_CYCLE" value="year">年
				</td>
				<td>
					统计时间：<input type="text" name="LOG_TIME_START" id="LOG_TIME_START" class="my97Date" maxDate="#F{$dp.$D('LOG_TIME_END')||'%y-%M-%d'}" readonly="true"/>
					<span id="timeEndSpan" style="display:none">&nbsp;&nbsp;~&nbsp;&nbsp;<input type="text" name="LOG_TIME_END" id="LOG_TIME_END" class="my97Date" minDate="#F{$dp.$D('LOG_TIME_START')}" readonly="true"/></span>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="logPageDiv" class="pageContent"></div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">

</script>
<div class="pageHeader">
	<form id="accountLogForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					网站名称:<input type="text" name="SITE_NAME" />
				</td>
				<td>
					流水类型： <!--  initVal="gather" -->
					<select name="LOG_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_ACCOUNT_LOG_TYPE"
						showNull="true" style="width:120px">
					</select>
				</td>
				<td>
					还款状态： 
					<select name="GATHER_STATE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_GATHER_STATE"
						showNull="true" style="width:120px">
					</select>
				</td>
				
				<td>
					发生时间：<input type="text" name="DUE_TIME_START" id="DUE_TIME_START" class="my97Date" maxDate="#F{$dp.$D('DUE_TIME_END')}" readonly="true"/>
				</td>
				<td>~</td>
				<td>
					<input type="text" name="DUE_TIME_END" id="DUE_TIME_END" class="my97Date" minDate="#F{$dp.$D('DUE_TIME_START')}" readonly="true"/>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="accountLogPageDiv" class="pageContent"></div>
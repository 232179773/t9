<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<script type="text/javascript">

</script>
<div class="pageHeader">
	<form id="accountForm">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					网站名称:<input type="text" name="SITE_NAME" />
				</td>
				<td>
					支付渠道:<input type="text" name="PAY_TYPE" />
				</td>
				<td>
					注册时间：<input type="text" name="REGISTER_TIME_START" id="REGISTER_TIME_START" class="my97Date" maxDate="#F{$dp.$D('REGISTER_TIME_END')}" readonly="true"/>
				</td>
				<td>~</td>
				<td>
					<input type="text" name="REGISTER_TIME_END" id="REGISTER_TIME_END" class="my97Date" minDate="#F{$dp.$D('REGISTER_TIME_START')}" readonly="true"/>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div id="accountPageDiv" class="pageContent"></div>
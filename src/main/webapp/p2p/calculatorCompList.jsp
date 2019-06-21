<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>

<div class="pageContent">
	<form id="calculatorForm" data-validator-option="{valid:saveData}">
		<div class="pageFormContent" layoutH="56">	
			<p>
				<label>本金：</label>
				<input type="text" id="MONEY" name="MONEY" maxlength="25" value="100000"/>
			</p>
            <p>
                <label>月存：</label>
                <input type="text" id="MONTH_MONEY" name="MONTH_MONEY" maxlength="100" value="10000"/>
            </p>
		    <p>
				<label>利率：</label>
				<input type="text" id="INTEREST_RATE" name="INTEREST_RATE" maxlength="100" value="12%"/>
			</p>
            <p>
                <label>期限：</label>
                <input type="text" id="TENDER_DURATION" name="TENDER_DURATION" maxlength="100" class="required"  value="36"/>
            </p>
						
		</div>
	</form>
</div>
<div id="calculatorPageDiv" class="pageContent"></div>
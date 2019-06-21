<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/frame/_include.jsp" %>
<script>
var typeId="";
$(function() {
	if(urlParam.ID){
		if(typeId==""){
			typeId="typeId";
		}else{
			typeId=urlParam.ID;
		}
		T9.post('service/moneyLog!queryMoneyLogById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#moneyLogEditForm").setData(result.obj);
			}
		});
	};
});
function saveData() {
	var param=$('#moneyLogEditForm').serializeJSON();
	var url =  'service/moneyLog!saveMoneyLog' ;
	if(urlParam.ID){
		url = 'service/moneyLog!updateMoneyLog';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#moneyLogPageDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="moneyLogEditForm"   data-validator-option="{valid:saveData}">
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">		
			<p>
				<label>网站名称：</label>
				<input type="text" id="SITE_NAME" name="SITE_NAME" maxlength="100" class="required"/>
				<span class="info"></span>
			</p>
			<p>
				<label>流水类型：</label>
				<select name="LOG_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_MONEY_LOG_TYPE">
				</select>
			</p>
			<p>
				<label>金额：</label>
				<input type="text" id="MONEY" name="MONEY" maxlength="25"/>
			</p>
			<p>
				<label>发生时间：</label>
				<input type="text" name="DUE_TIME" id="DUE_TIME" class="my97Date"  dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" />
			</p>
			<p>
				<label>支付银行：</label>
				<input type="text" id="PAY_BANK" name="PAY_BANK" maxlength="25"/>
			</p>
			<p>
				<label>支付卡号：</label>
				<input type="text" id="PAY_CARD" name="PAY_CARD" maxlength="25"/>
			</p>
			<p>
				<label>支付渠道：</label>
				<input type="text" id="PAY_TYPE" name="PAY_TYPE" maxlength="25"/>
			</p>
			<p>
				<label>支付订单号：</label>
				<input type="text" id="PAY_ORDERNO" name="PAY_ORDERNO" maxlength="25"/>
			</p>
			<p>
				<label>二级支付渠道：</label>
				<input type="text" id="SECOND_PAYTYPE" name="SECOND_PAYTYPE" maxlength="25"/>
			</p>
			<p>
				<label>二级支付订单号：</label>
				<input type="text" id="SECOND_ORDERNO" name="SECOND_ORDERNO" maxlength="25"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

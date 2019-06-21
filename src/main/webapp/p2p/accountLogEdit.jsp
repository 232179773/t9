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
		T9.post('service/accountLog!queryAccountLogById',{ID:urlParam.ID},function(result){
			if(result.success){
				$("#accountLogEditForm").setData(result.obj);
	//			$('#MANAGER_NAME').val(decodeURIComponent(urlParam.MANAGER_NAME));
			}
		});
	};
	
	$('#GATHER_STATE_DIV').hide();
	$('#GATHER_STATE').change(function(){
		var va=$(this).val();
		if(va=="0"){
			$('#GATHER_STATE_DIV').hide();
		}else{
			if($('#LOG_TYPE').val()=='bid'){
				$('#GATHER_STATE_DIV').show();
			}
		}
	});
	
});

function saveData() {
	var param=$('#accountLogEditForm').serializeJSON();
	var url =  'service/accountLog!saveAccountLog' ;
	if(urlParam.ID){
		url = 'service/accountLog!updateAccountLog';
	}
	T9.post(url, param, function(result){
		    //{"message":"database.error","obj":null,"success":false}
			if(result.success){
				T9.alert("保存成功！");
				parent.$("#accountLogPageDiv").gridReload();
				T9.dialogClose();
			} else {
				T9.error(result.message);
			}
	});
}

</script>

<div class="pageContent">
	<form id="accountLogEditForm"  data-validator-option="{valid:saveData}" >
		<input type="hidden" id="ID" name="ID" maxlength="25"/>
		<input type="hidden" id="REMARK" name="REMARK" maxlength="25"/>
		<input type="hidden" id="COLLECTION_MONEY" name="COLLECTION_MONEY" maxlength="25"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>网站名称：</label>
				<input type="text" id="SITE_NAME" name="SITE_NAME" maxlength="100" class="required"/>
				<span class="info"></span>
			</p>
			<p>
				<label>流水类型：</label>
				<select id="LOG_TYPE" name="LOG_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_ACCOUNT_LOG_TYPE">
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
				<label>标类型：</label>
				<select name="TENDER_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_BID_TYPE">
				</select>
			</p>
			<p>
				<label>投标期限：</label>
				<input type="text" id="TENDER_DURATION" name="TENDER_DURATION" maxlength="100" class="required"/>
			</p>
			<p>
				<label>还款方式：</label>
				<select name="REPAMENT_TYPE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_REPAMENT_TYPE">
				</select>
			</p>
			<p>
				<label>利率：</label>
				<input type="text" id="INTEREST_RATE" name="INTEREST_RATE" maxlength="100"/>
			</p>
			<p>
				<label>奖励：</label>
				<input type="text" id="REWARD" name="REWARD" maxlength="100"/>
			</p>
			<p>
				<label>利息管理费：</label>
				<input type="text" id="INTEREST_COST" name="INTEREST_COST" maxlength="100"/>
			</p>
			<p>
				<label>投标标题：</label>
				<input type="text" id="BID_TITLE" name="BID_TITLE" maxlength="100"/>
			</p>
			<p>
			 	<label>投标URL：</label>
				<input type="text" id="BID_URL" name="BID_URL" maxlength="100"/>
			</p>
			<p>
				<label>还款状态：</label>
				<select id="GATHER_STATE" name="GATHER_STATE" dataSrc="type=DICT_OPTION&DICT_CODE=P2P_GATHER_STATE">
				</select>
			</p>
			<div id="GATHER_STATE_DIV">
			<p>
				<label>投资收入：</label>
				<input type="text" id="EARN_MONEY" name="EARN_MONEY" maxlength="25"/>
			</p>
			<p>
				<label>还款时间：</label>
				<input type="text" name="REPAYMENT_TIME" id="REPAYMENT_TIME" class="my97Date"  dateFmt="yyyy-MM-dd HH:mm:ss"  readonly="true" />
			</p>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="zh-cn" http-equiv="Content-Language"><title></title>
<#include "/WEB-INF/front/include/_include.ftl"/>
<script>
var type="";
var paramsId="";
function addAddress(){
	var addressee=$('#consignee_name').val();
	var detail_address=$('#consignee_address').val();
	var mobile=$('#consignee_mobile').val();
	var telephone=$('#consignee_phone').val();
	var email=$('#consignee_email').val();
	//var region=$('#').val();
	var region="1234";
	var isdefault="0";
	if($('input[@name="consignee_radio"][checked]').val()=="1"){
		isdefault="1";
	}
	var params={ADDRESSEE:addressee,DETAIL_ADDRESS:detail_address,MOBILE:mobile,TELEPHONE:telephone,EMAIL:email,REGION:region,ISDEFAULT:isdefault};
	if(type=="update"){
		type="";
		params.ID=paramsId;
			T9.post('service/receiveAddress!updateReceiveAddress',params,function(result){
			if(result.success){
				$('#consignee_name').val("");
				$('#consignee_address').val("");
				$('#consignee_mobile').val("");
				$('#consignee_phone').val("");
				$('#consignee_email').val("");
				alert("修改成功");
			}
		});
	}else{
		T9.post('service/receiveAddress!saveReceiveAddress',params,function(result){
			if(result.success){
				alert("保存成功");
			}
		});
	}

}

function editAddress(object){
	type="update"
	// object=object.replace(new RegExp(/(=)/g),':');
	 //object=object.replace(new RegExp(/(:,)/g),': ,');
	//var json = eval('(' + object + ')'); 
	var objectLength=object.length;
	var obj=object.substring(1,objectLength-1);
	var objectArray=obj.split(",");
	for(var i=0;i<objectArray.length;i++){
		var objectNewArray=objectArray[i].trim().split("=");
		if(objectNewArray[0]=="ADDRESSEE"){
			$('#consignee_name').val(objectNewArray[1])
		}else if(objectNewArray[0]=="DETAIL_ADDRESS"){
			$('#consignee_address').val(objectNewArray[1])
		}else if(objectNewArray[0]=="MOBILE"){
			$('#consignee_mobile').val(objectNewArray[1]);
		}else if(objectNewArray[0]=="TELEPHONE"){
			$('#consignee_phone').val(objectNewArray[1]);
		}else if(objectNewArray[0]=="EMAIL"){
			$('#consignee_email').val(objectNewArray[1]);
		}else if(objectNewArray[0]=="ID"){
			paramsId=objectNewArray[1];
		}
		//else if(objectNewArray[0]=="REGION"){
			//$('#consignee_email').val(objectNewArray[1]);
		//}
	}
}
function deleteAddress(id){
		T9.post('service/receiveAddress!deleteReceiveAddress',{ID:id},function(result){
			if(result.success){
				alert("删除成功");
			}
		});
}

function searchStoreByName(){
	var storeName=$('#storeNameId').val();
	T9.post('service/store!queryStorePage',{STORE_NAME:storeName},function(result){
		
	});
}
</script>
</head>
<body>
	<!-- topMenu -->
		<div class="topMenu">
			<div class="con">
				<div class="wel">HI，欢迎来到喜来登订餐网！</div>  
				<div class="links"><a href="#">登录</a><a href="#">免费注册</a></div> 
			</div>
		<!-- 登录后 显示 用户中心       我的收藏       意见反馈 -->
		</div>
		<!-- topMenu end -->
	<div class="content">
		

		<!-- head -->
		<div class="head">
			<div class="topSe">
				<input type="text" />
				<span></span>
			</div>
		</div>
		<!-- head end -->
		
		<!-- order -->
			<div class="order">
					<h2>填写并核对订单信息</h2>
					<div class="orderTitle">收货人信息</div>
					<!-- orderContent -->
					<div class="orderContent">
					
					<#list queryReceiveAddressList as gridData>
						<#if "${gridData.ISDEFAULT}"== "1">
							<p class="item"><input type="radio" class="hookbox" name="consignee_radio" value="${gridData.ID}" checked="checked"><label><span>${gridData.ADDRESSEE}</span>&nbsp;${gridData.DETAIL_ADDRESS}&nbsp;${gridData.MOBILE}&nbsp;${gridData.TELEPHONE}<a href="javascript:editAddress('${gridData}');">编辑</a><a href="javascript:deleteAddress('${gridData.ID}');">删除</a></label></p>
						<#else>
							<p class="item"><input type="radio" class="hookbox" name="consignee_radio" value="${gridData.ID}"><label><span>${gridData.ADDRESSEE}</span>&nbsp;${gridData.DETAIL_ADDRESS}&nbsp;${gridData.MOBILE}&nbsp;${gridData.TELEPHONE}<a href="javascript:editAddress('${gridData}');">编辑</a><a href="javascript:deleteAddress('${gridData.ID}');">删除</a></label></p>
						</#if>
					</#list>
						<p class="item"><input type="radio" class="hookbox" name="consignee_radio" value="1"><label>使用新地址</label></p>
						<div class="consignee-form" style="padding-left: 12px; display: block;">
							
						   <div class="list" id="name_div">
							   <span class="label"><em>*</em>收货人：</span>
								 <div class="field">
								   <input type="text" class="textbox" id="consignee_name" name="consigneeParam.name" maxlength="20" >
								 </div>
								 <span class="status error" id="name_div_error"></span>
						   </div>
						   <div class="list select-address" id="area_div">
							   <span class="label"><em>*</em>所在地区：</span>
								 <div class="field">
								  <span id="span_area"><span id="span_province"><select id="consignee_province" name="consigneeParam.provinceId" ><option value="">请选择：</option><option value="1">北京*</option><option value="2">上海*</option><option value="3">天津*</option><option value="4">重庆*</option><option value="5">河北*</option><option value="6">山西*</option><option value="7">河南*</option><option value="8">辽宁*</option><option value="9">吉林*</option><option value="10">黑龙江*</option><option value="11">内蒙古*</option><option value="12">江苏*</option><option value="13">山东*</option><option value="14">安徽*</option><option value="15">浙江*</option><option value="16">福建*</option><option value="17">湖北*</option><option value="18">湖南*</option><option value="19">广东*</option><option value="20">广西*</option><option value="21">江西*</option><option value="22">四川*</option><option value="23">海南*</option><option value="24">贵州*</option><option value="25">云南*</option><option value="26">西藏*</option><option value="27">陕西*</option><option value="28">甘肃*</option><option value="29">青海*</option><option value="30">宁夏*</option><option value="31">新疆*</option><option value="32">台湾*</option><option value="42">香港*</option><option value="43">澳门*</option><option value="84">钓鱼岛*</option></select></span><span id="span_city"><select id="consignee_city" name="consigneeParam.cityId"><option value="" selected="">请选择：</option></select></span><span id="span_county"><select id="consignee_county" name="consigneeParam.countyId" ><option value="" selected="">请选择：</option></select></span><span id="span_town" style="display: none;"><select id="consignee_town" name="consigneeParam.townId" onblur="check_Consignee('area_div')"><option value="" selected="">请选择：</option></select></span></span>
								  
								</div>
								<span class="status error" id="area_div_error"></span>
						   </div>
						   <div class="list full-address" id="address_div">
							   <span class="label"><em>*</em>详细地址：</span>
								 <div class="field">
								 <input type="text" class="textbox" id="consignee_address" name="consigneeParam.address" maxlength="50" >
								 </div>
								 <span class="status error" id="address_div_error"></span>
						   </div>
						   <div class="list" id="call_div">
							   <span class="label"><em>*</em>手机号码：</span>
								<div class="field">
								  <div class="phone">
									<input type="text" class="textbox" id="consignee_mobile" name="consigneeParam.mobile"  maxlength="11" >
									<em>或</em>
									<span>固定电话：</span>
									<input type="text" class="textbox" id="consignee_phone" name="consigneeParam.phone"  maxlength="20">
								  </div>
								 <span class="status error" id="call_div_error"></span>
							  </div>
						   </div>
						   <div class="list" id="email_div">
							   <span class="label"><em></em>邮箱：</span>
								 <div class="field">
								   <input type="text" class="textbox" id="consignee_email" name="consigneeParam.email" maxlength="50" >
								   <span class="form-tip">用来接收订单提醒邮件，便于您及时了解订单状态</span>
								 </div>
								 <span class="status error" id="email_div_error"></span>
						   </div>
						</div>
						<div class="orderBtns">
							<a href="javascript:addAddress();" class="addrBtn"></a>
						</div>
					</div>
					<!-- orderContent end -->


					<div class="orderTitle">菜品清单</div>

					<div class="orderContent">
						<table>
							<tbody>
								<tr>
									<th>餐厅名称</th>
									<th>菜名</th>
									<th>价格</th>
									<th>口味</th>
									<th>份数</th>
								</tr>
							   <#list lstMenu as data> 
							   	<tr>
									<td>${store.STORE_NAME}</td>
									<td>${data["MENU_NAME"]}</td>
									<td>${data["MENU_PRICE"]}</td>
									<td><select><option>清淡</option><option>微辣</option></select></td>
									<td>${data["NUMBER"]}</td>
								</tr>
                               </#list>
                       
							</tbody>
						</table>

						<div class="statistic">
							<div class="list"><span><em id="span-skuNum">6</em> 件菜品，总金额：</span><em class="price" id="warePriceId" v="14199">￥14199.00</em></div>
							<div class="list"><span>返现：</span><em class="price" id="cachBackId" v="0"> -￥0.00</em></div>
							<div class="list" id="showFreightPrice">
									<span ><font color="#000000">外卖费：</font></span> 
									<em class="price" id="freightPriceId"><font color="#000000"> ￥0.00</font></em>
							</div>
							<div class="list"><span>应付总额：</span><em id="sumPayPriceId" class="price">￥14199.00</em></div>			
						</div>
					</div>

					<div class="orderBtns">
							<a href="#" class="subBtn"></a>
					</div>
			

			</div>
		<!-- order end -->	

	</div>

</body>
</html>

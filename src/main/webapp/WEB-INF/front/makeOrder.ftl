<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="zh-cn" http-equiv="Content-Language"><title></title>
<#include "/WEB-INF/front/include/_include.ftl"/>

  <script>

   
   $(function(){	
      var logoURL = T9.getFileAppendix(${store.STORE_LOGO});
      $("#storeLogo").attr('src', logoURL);
      
      
   });
   
   function addMenu(menuId, menuName, price){
      //设置左边菜单列表的数量
      var numberNode = 'number_'+menuId;
      var number = parseInt($('#'+numberNode).html());
      number = number + 1;
      $('#'+numberNode).html(number);
      
      //设置美食篮中的数量
      var liID = "li_"+menuId;
      var $node=$("#"+liID);
      if($node && $node.length>0){    //menu already exists in basket
         $('#bnumber_'+menuId).html(number);
         
      } else {
         var str = "<li id='li_"+menuId +"' name='li_"+menuId+"' menuId='"+menuId+"'>";
         str += ' <em>'+menuName +'</em>';
         str += " <span><b class='reduceBtn' onclick='reduceMenu(\""+menuId+"\",\""+menuName+"\")'></b>";
         str += " <i id='bnumber_"+menuId +"' name='bnumber_"+menuId +"' >1</i>";
         str += " <b class='addBtn' onclick='addMenu(\""+menuId+"\",\""+menuName+"\","+price+")'></b></span>";
         str += " <span id='bprice_"+menuId+"' name='bprice_"+menuId+"' >" + price + "</span>";
         str += " <b class='delBtn' onclick='delMenu(\"" + menuId+ "\")'></b>";
         str += "</li>";
         $('#ul_basket').append(str);
      }
      
      //计算总价
      $('#bTotalAmt').html(calTotalAmt());
      
   }

   function reduceMenu(menuId, menuName){
      //设置左边菜单列表的数量
      var numberNode = 'number_'+menuId;
      var number = parseInt($('#'+numberNode).html());
      if(number == 0){
         return;
      }
      number = number - 1;
      $('#'+numberNode).html(number);
      
      if(number == 0){
         $("#li_"+menuId).remove();
      } else {
         $('#bnumber_'+menuId).html(number);
      }
      
      //计算总价
      $('#bTotalAmt').html(calTotalAmt());
   }
   
   function delMenu(menuId){
        //菜单列表数量清0
        var numberNode = 'number_'+menuId;
        $('#'+numberNode).html("0");
        
        //清空美食篮
        $("#li_"+menuId).remove();
        
         //计算总价
        $('#bTotalAmt').html(calTotalAmt());
   }
   
      
   function calTotalAmt(){
      //循环美食篮菜单， 价格*数量
      var amt = 0;
      var liNodes = $("#ul_basket").children();
      if(liNodes && liNodes.length){
         for(var i=0; i<liNodes.length; i++){
            var liNode = liNodes[i];
            var menuId = liNode.getAttribute("menuId");
            
            var price = parseFloat($("#bprice_"+menuId).html());
            var number = parseFloat($("#bnumber_"+menuId).html());
            amt += number * price;
         }
      }
      return amt;
   }
   
   function showAllMenus(){
      alert('show all menus');
   }
   
   function showMenuClass(menuClass){
      alert('show selected class menu');
   }
   
   function doSubmit(){
      alert('submit order');
      var url = 'service/store!submitOrder?storeId=${store.ID}';
      
      var liNodes = $("#ul_basket").children();
      if(liNodes && liNodes.length){
         for(var i=0; i<liNodes.length; i++){
            var liNode = liNodes[i];
            var menuId = liNode.getAttribute("menuId");
            var number = parseFloat($("#bnumber_"+menuId).html());
            
            url += '&menuId='+menuId + '&number='+number;
            
            T9.navigate(url);
         }
         
      } else {
        alert('请先选择菜单');
      }
      
      
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
				<input type="text" id="storeNameId"/>
				<a onClick="searchStoreByName();"><span></span></a>
			</div>
		</div>
		<!-- head end -->
		
	<!-- dinerDoc -->	
		<div class="dinerDoc"> 
			<img alt="logo" id="storeLogo" name="storeLogo"  />
			<h5>${store.STORE_NAME}</h5>
			<p>地址：<b>${store.STORE_ADDRESS}</b></p> 
			<!--
			<p>电话：<b></b></p>
			<p>标签：<b>软件园  科韵路  台湾铁板烧</b></p>
			-->
		</div>
		<!-- dinerDoc end -->	
		<!-- dinerLeft  -->	
		<div class="dinerLeft">
			<h4>菜单列表</h4>
			<div class="box">
				
				<div class="sort">
					<span><a onclick="showAllMenus()">分类</a></span>
					<#list menuClassMap?keys as key> 
                      <a onclick="showMenuClass('${key}')">${key}</a>
                    </#list>
				</div>
				<ul>
				    <#list menuClassMap?keys as key> 
                      <p id="s1" menuClass="${key}" >${key}</p>
                      
                      <#assign classListData = menuClassMap[key]> 
                      <#list classListData as data> 
                          <li><img class="img" src="../images/bg/lo.png">
                              <span>${data["MENU_NAME"]}</span>
                              <label><b menuId='${data["ID"]}' menuName='${data["MENU_NAME"]}' class="reduceBtn" onclick="javascript:reduceMenu('${data["ID"]}', '${data["MENU_NAME"]}')"></b>
                                <i id="number_${data["ID"]}" name="number_${data["ID"]}" >0</i>
                                <b menuId='${data["ID"]}' menuName='${data["MENU_NAME"]}' class="addBtn" onclick="javascript:addMenu('${data["ID"]}', '${data["MENU_NAME"]}', ${data["MENU_PRICE"]})"></b>
                              </label>
                              <em>${data["MENU_PRICE"]}</em>
                          </li>
                       </#list>
                    </#list>
                    
				</ul>

			</div>
		</div>
		<!-- dinerLeft end -->	
		<!-- dinerRight  -->	
		<div class="dinerRight">
			<h4>美食筐</h4>
			<div class="box">
				<div class="ht"><em>菜品</em><span>份数</span><span>单价</span></div>
				<ul id="ul_basket" name="ul_basket" >
				    <!--
					<li id="li_menuId" name="li_menuId">
						<em>香辣排骨香辣排骨香辣排骨香辣排骨香辣排骨香辣排骨</em>
						<span><b class="reduceBtn"></b>999<b class="addBtn"></b></span>
						<span>11.00</span>
						<b class="delBtn"></b>
					</li>
			        -->
				</ul>
				<div class="ht"><em>总价</em><span>&nbsp;</span><span id="bTotalAmt" name="bTotalAmt">0.00</span></div>
				<p class="orderBtn" onclick="doSubmit()"></p>
			</div>
		</div>
		<!-- dinerRight end -->	

	</div>

</body>
</html>

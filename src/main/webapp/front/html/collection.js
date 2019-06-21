var type="";
$(function(){
	type=2;
	if(type==2){
		$('#collectionMenuPageDiv').frontgrid({
			columns:[
			    	   	{header:'',ctrl:'checkbox',width:'10%',key:"collectId"},
			    	    {header:'菜单图片',key:"MENU_PICTURE",width:'20%'},
			    	    {header:'菜名信息',key:"MENU_NAME",width:'20%'},
			    	    {header:'口味',key:"FLAVOUR",render:'dict:STOREMENU_FLAVOUR',width:'10%'},
			    	    {header:'价格',key:"MENU_PRICE",width:'10%'},
			    	    {header:'收藏时间',key:"create_time",width:'10%'},
			    	    {header:'分类',key:"MENU_STYLE",render:'dict:STOREMENU_CLASS',width:'10%'},
			    	    {header:'操作',key:"collectId",cellRenderer:opRenderer111,width:'10%'}
			    	],
			//onSuccess:collectionMenuHtml,
			url : 'service/userCollect!queryUserCollectListByPage?TYPE=2'
		});
	}else{
	$('#collectionStorePageDiv').frontgrid({
		onSuccess:collectionStoreHtml,
		url : 'service/userCollect!queryUserCollectListByPage'
	});
	}
});

function collectionStoreHtml(record,pageInfo){
	var html="";
	$(record).each(function(){
		html +='<li>';
		html +='<div class="L">';
		html +='<img src="'+getDefultViewIsNull(this.STORE_LOGO,"")+'" alt="logo">';
		html +='<input type="checkbox" />';
		html +='<a href="javascript:deleteCollection(\''+getDefultViewIsNull(this.collectId,"")+'\');">删除</a>';
		html +='</div>';
		html +='<h4><a href="#">'+getDefultViewIsNull(this.STORE_NAME,"")+'</a></h4>';
		html +='<p>地&nbsp;&nbsp;&nbsp;&nbsp;址：'+getDefultViewIsNull(this.STORE_ADDRESS,"")+'</p>';
		html +='<p>营业时间：'+getDefultViewIsNull(this.BEGIN_TIME,"")+' - '+getDefultViewIsNull(this.END_TIME,"")+'</p>';
		html +='<p>'+getDefultViewIsNull(this.DISTRIBUTION_RANGE,"")+'</p>';
		html +='</li>';
	});
	
	$("#collectionStoreUI").html(html);
}
function opRenderer111(value,record){
	alert(value);
	return '<span><a href="javascript:deleteCollection(\''+getDefultViewIsNull(value,"")+'\')">删除</a></span>';
};
function collectionMenuHtml(record,pageInfo){
	var html="";
	html +='<tr>';
	html +='<th width="24px"></th>';
	html +='<th width="76px"></th>';
	html +='<th>菜名信息</th>';
	html +='<th>口味</th>';
	html +='<th>价格</th>';
	html +='<th>收藏时间</th>';
	html +='<th>分类</th>';
	html +='<th>操作</th>';
	html +='</tr>';
	$(record).each(function(){
		html +='<tr>';
		html +='<td><input type="checkbox"></td>';
		html +='<td><img src="'+getDefultViewIsNull(this.MENU_PICTURE,"")+'" alt="logo"></td>';
		html +='<td><h4><a href="#">'+getDefultViewIsNull(this.MENU_NAME,"")+'</a></h4><p></p></td>';
		html +='<td>'+getDefultViewIsNull(this.FLAVOUR,"")+'</td>';
		html +='<td><b>'+getDefultViewIsNull(this.MENU_PRICE,"")+'</b></td>';
		html +='<td>'+getDefultViewIsNull(this.create_time,"")+'</td>';
		html +='<td>'+getDefultViewIsNull(this.MENU_STYLE,"")+'</td>';
		html +='<td><a href="javascript:deleteCollection(\''+getDefultViewIsNull(this.collectId,"")+'\')">删除</a></td>';
		html +='</tr>';
		
	});
	$("#collectionMenuTable").html(html);
}



function deleteCollection(id){
	alert(id);
	T9.post('service/userCollect!deleteUserCollect',{ID:id},function(result){
		if(result.success){
			alert("删除成功");
			if(type==2){
				$("#collectionMenuPageDiv").gridReload();
			}else{
				$("#collectionStorePageDiv").gridReload();
			}
		}
	});
}





function getDefultViewIsNull(value,defaultValue){
	if(null==value){
		return defaultValue;
	}else{
		return value;
	}
	
}
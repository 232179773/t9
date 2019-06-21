$(function() {
	$('button').click(function(){
		$.data(document.body, 'eventTarget', $(this));
	});
});

jQuery.isString = function(o) {
	return (typeof o==="string");
};


jQuery.getValueFromArray = function(dataArray,code) {
	var resultValue="";
	for(var i=0;i<dataArray.length;i++){
		var vv=(i==(dataArray.length-1)?"":",");
		resultValue+=dataArray[i][code]+vv;
	}
	return resultValue;
};

jQuery.paramStringToJson = function(paramStr) {
	var resultValue={};
	var params=paramStr.split("&");
	for(var i=0;i<params.length;i++){
		var vv=params[i].split("=");
		resultValue[vv[0]]=vv[1];
	}
	return resultValue;
};


jQuery.fn.serializeJSON = function() {
	var json = {};
	jQuery.map($(this).serializeArray(), function(n, i){
		json[n['name']] = n['value'];
	});
	return json;
};
jQuery.fn.setData = function(data) {
	for(var p in data){
		data[p.toUpperCase()]=data[p];
	}
	$(this).find(':input').each(function (i,obj){
		var name=obj.name;
		$obj=$(obj);
		if(data[name]){
			obj.value=data[name];
		}
		if($obj.get(0).tagName=='SELECT'){
			if($obj.val()!=data[name]){
				$obj.attr('initVal',data[name]);
			}else{
				$obj.trigger("change");
			}
		}		
		if($obj.get(0).tagName=='INPUT'&&$obj.attr('dataSrc')){
			$obj.dataTableInit();
		}
		if($obj.attr('class')=='my97Date'){
			var dateFmt='yyyy-MM-dd';
			if ($obj.attr("dateFmt"))
				dateFmt=$obj.attr("dateFmt");
			var vv=data[name];
			if(vv&& (vv.length > dateFmt.length)){
				$obj.val(vv.substring(0,dateFmt.length));
			}
		}
	});
};

jQuery.fn.setUrlParam = function() {
	$(this).find(':input').each(function (i,obj){
		var name=obj.name;
		$obj=$(obj);
		if(name&&urlParam.get(name)){
			obj.value=urlParam.get(name);
		}
	});
};

jQuery.fn.setCodeImage = function() {
	var imgUrl=base_path+'service/randomValidateCode!getRandcode?randtime='+new Date();
	$(this).html($('<img src="'+imgUrl+'">'));
	$(this).click(function (){
		$(this).find('img').attr('src',base_path+'service/randomValidateCode!getRandcode?randtime='+new Date());
	});
};

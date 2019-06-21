/**
 * @author HUSQ
 */

(function($){
	$.extend($.fn, {
		dataInit:function(){
			
			return this.each(function(i){
				var $this = $(this);
				var htmlName = $this.attr("name");
				var htmlId = $this.attr("id");
				var dataSrc=$this.attr("dataSrc");
				var dataObj=$.paramStringToJson(dataSrc);
				if($this.is('input')){
					if(dataObj.type=="open"){
						var tableParam=dataObj.src;
						var html='<input type="hidden" name="'+htmlName+'" id="'+htmlId+'" dataSrc="'+dataSrc+'" value="'
							+$this.val()+'"/><input type="text" name="NAME_OF_'+htmlId+'" id="NAME_OF_'+htmlId+'"/>';
						$this.before(html);
						$this.after($('<input type="button"value="选 择">').click(function(){
							var param={url : 'service/fastFile!pageView?type=select&TABLE_CODE='+tableParam,
											callback:function(result){
												$('#NAME_OF_'+htmlId).val(result[0].NAME_OF_ID);	
												$('#'+htmlId).val(result[0].ID);				
											}
										};
							T9.dialog(param);
						}));						
						$this.remove();
						$('#'+htmlId).dataTableInit();
					}
				} 
				if($this.is('select')){
					$this.attr("style","width:120px");
					if(dataSrc.indexOf("{")>0){
						var begin=dataSrc.indexOf("{");
						var end=dataSrc.indexOf("}");
						var beRefID=dataSrc.substring(begin+1,end);
						var $beRef = $("#"+beRefID);
						
						function _onchange(event){
							var beRefVal=$beRef.val();
							if(!beRefVal){
								$this.empty();
								$this.change();
								return false;
							}
							T9.ajax('service/common!queryOptions?'+dataSrc.replace("{"+beRefID+"}", encodeURIComponent(beRefVal)),{
								type:'GET', dataType:"json",  cache: true,
								data:{},
								callback: function(jsonr){
									if (!jsonr) return;
									if(!jsonr.success) return;
									var json=jsonr.obj;
									var html = '';
									if($this.attr("showNull")!="false"){
										html += '<option></option>';
									}
									$.each(json, function(i){
										if (json[i]){
											html += '<option value="'+json[i]['OPTION_KEY']+'">' + json[i]['OPTION_VALUE'] + '</option>';
										}
									});
									$this.html(html);
									
									if($this.attr('initVal')){
										$this.val($this.attr('initVal'));
										$this.removeAttr('initVal');
							//			$this.trigger("change");
									}else{
							//			$this.change();
									}
									$this.trigger("change");//;
								}
							});
						}					
						$beRef.bind("change", _onchange);
						return;
					}
					T9.ajax('service/common!queryOptions?'+dataSrc,{
						type:'GET', dataType:"json",  cache: true,
						data:{},
						callback: function(jsonr){
							if (!jsonr) return;
							if(!jsonr.success) return;
							var json=jsonr.obj;
							var html = '';
							if($this.attr("showNull")!="false"){
								html += '<option></option>';
							}
							$.each(json, function(i){
								if (json[i]){
									html += '<option value="'+json[i]['OPTION_KEY']+'">' + json[i]['OPTION_VALUE'] + '</option>';
								}
							});							
							$this.html(html);
							if($this.attr('initVal')){
								$this.val($this.attr('initVal'));
								$this.removeAttr('initVal');
							}
							$this.change();
						}
					});
				}
				
			});
		},
		
		dataTableInit:function(){			
			return this.each(function(i){
				var $this = $(this);
				var dataId=$this.val();
				if(dataId==""){
					return;
				}
				var dataSrc=$this.attr("dataSrc");
				var dataObj=$.paramStringToJson(dataSrc);
				var tableParam=dataObj.src;
				T9.ajax('service/common!renderTable?tableParam='+tableParam+'&dataId='+dataId,{
					type:'GET', dataType:"json",  cache: true,
					callback: function(jsonr){
						if (!jsonr) return;
						if(!jsonr.success) return;
						var resultObj=jsonr.obj;
						if(dataSrc.indexOf("{")>0){
							for(var p in resultObj){
								$('#'+p).val(resultObj[p]);
							}
						}else{
							$('#NAME_OF_'+$this.attr("id")).val(resultObj.NAME_OF_ID);
						}
					}
				});
			});
		}
		
	});
})(jQuery);

/**
 * @author HUSQ
 */

(function($){
	$.extend($.fn, {
		combox:function(){
			
			return this.each(function(i){
				var $this = $(this).removeClass("combox");
				$this.attr("style","width:120px");
				var name = $this.attr("name");
				var ref = $this.attr("ref");
				var refUrl = $this.attr("refUrl") || "";
				var sefUrl = $this.attr("sefUrl") || "";

				if(ref && !refUrl){
					var tRefUrl=  $("#"+ref).attr("sefUrl") || "";
					tRefUrl=tRefUrl.replace("{"+name+"}","{value}");
					refUrl=tRefUrl;
				}
				if (ref && refUrl) {
					function _onchange(event){
						var $ref = $("#"+ref);
						if ($ref.size() == 0) return false;
						if ($this.attr("value") == ''){
							$ref.empty();
							$ref.change();
							return false;
						}
						
						T9.ajax('service/common!queryOptions?'+refUrl.replace("{value}", encodeURIComponent($this.attr("value"))),{
							type:'GET', dataType:"json",  cache: true,
							data:{},
							callback: function(jsonr){
								if (!jsonr) return;
								var json=jsonr.gridData;
								var html = '';
								if($ref.attr("showNull")!="false"){
									html += '<option></option>';
								}
								$.each(json, function(i){
									if (json[i]){
										html += '<option value="'+json[i]['OPTION_KEY']+'">' + json[i]['OPTION_VALUE'] + '</option>';
									}
								});
								
								$ref.html(html);
								if($ref.attr('initVal')){
									$ref.val($ref.attr('initVal'));
									$ref.removeAttr('initVal');
								//	$ref.change();
									$ref.combox().trigger("change");
								}else{
									$ref.change();
								}
						//		$ref.combox().trigger("change");//;
							}
//							,
//							error: DWZ.ajaxError
						});
					}					
					$this.unbind("change").bind("change", _onchange);
				}
				if(sefUrl){
					if(sefUrl.indexOf("{")>0){
						return;
					}
					T9.ajax('service/common!queryOptions?'+sefUrl,{
						type:'GET', dataType:"json",  cache: true,
						data:{},
						callback: function(jsonr){
							if (!jsonr) return;
							var json=jsonr.gridData;
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
		}
	});
})(jQuery);

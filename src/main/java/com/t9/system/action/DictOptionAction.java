package com.t9.system.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Demo;
import com.t9.system.entity.DictOption;
import com.t9.system.service.DictOptionService;
import com.t9.system.service.DictService;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class DictOptionAction extends BaseAction {

	@Autowired
	private DictOptionService dictOptionService;
	private DictOption dictOption = new DictOption();

	/**
	 * 查询 字典项
	 * 
	 * @param e
	 * @return
	 */
	public List<Map> queryDictOptionList() {
		Map<String, String> map = ServletUtils.getMapByRequest(this
				.getRequest());
		List<Map> list= dictOptionService.queryDictOption(map,true);
		super.renderToOutput(list);
		return null;
	}

	/**
	 * 增加指点项 修改
	 * 
	 * @param dict
	 * @return
	 * @throws T9Exception 
	 */
	public T9Result saveDicOption() throws T9Exception {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		
		if(null!=map.get("ID") && !"".equals(map.get("ID"))){
			T9Result result = dictOptionService.updateDicOption(map);
			super.renderToOutput(result);
		}else{
			T9Result result = dictOptionService.saveDictOption(dictOption);
			super.renderToOutput(result);
		}
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param dict
	 */
	public T9Result updateDicOption() {
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @throws T9Exception 
	 */
	public T9Result deleteDictOption() throws T9Exception {
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		T9Result result = dictOptionService.deleteDicOption(id);
		super.renderToOutput(result);
		return null;
	}
	
	public String queryDictOptionById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		DictOption dictOption=dictOptionService.queryDictOptionById(id);
		T9Result result=new T9Result(dictOption);
		super.renderToOutput(result);
		return null;
	}
	
	public Object getModel() {

		return dictOption;
	}

}

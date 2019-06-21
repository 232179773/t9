package com.t9.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.entity.Dict;
import com.t9.system.entity.DictOption;
import com.t9.system.entity.Log;
import com.t9.system.service.DictOptionService;
import com.t9.system.service.DictService;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
/**
 * 
 * @author t9
 * @Description:TODO
 * @date:2013-7-9
 * @version:
 */
public class DictAction extends BaseAction{
	@Autowired
	private DictService dictService;
	@Autowired
	private DictOptionService dictOptionService;
	
	private Dict dict=new Dict();
	
	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryDictList(){
		Map<String, String> map = ServletUtils.getMapByRequest(this.getRequest());
		List<Map> list=dictService.queryDictList(map);
		super.renderToOutput(list);
		return null;
		
	}
	

	public String queryDictById(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		Dict dict=dictService.queryDictById(id);
		T9Result result=new T9Result(dict);
		super.renderToOutput(result);
		return null;
	}

	/**
	 * 保持字典类
	 * @return
	 */
	public String saveDict(){
		T9Result result=dictService.saveDict(dict);
		super.renderToOutput(result);
		return null;
	}
	/**
	 * 删除字典类
	 * @return
	 */
	public String deleteDict(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String id=(String)map.get("ID");
		T9Result result=dictService.deleteDict((String)map.get("DICT_CODE"),id);
		super.renderToOutput(result);
		return null;
	}
	/**
	 * 修改字典类
	 * @return
	 */
	public String updateDict(){
		T9Result result=dictService.updateDict(dict);
		super.renderToOutput(result);
		return null;
		
	}
	
	public Object getModel() {
		
		return dict;
	}

	
}

package com.t9.system.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class BuildTree {
	/**
	 * 构造树型数据，此方法会将原数据结构改变
	 * 
	 * @param list
	 *            从数据库查出的原数据
	 * @param parentCol
	 *            父ID列名
	 * @param selfCol
	 *            主键列名
	 * @return
	 */
	public static List<Map> createTree(List<Map> list,
			String parentCol, String selfCol) {
		List<Map>  tree = new ArrayList<Map>();
		//完全循环一次,是子节点的最终被替换为null
		for (Iterator<Map> it = list.iterator(); it.hasNext();) {
			Map mp = it.next();
			if (mp == null)
				continue;//为空,说明已经被设为一个子节点了
			if(mp.get(selfCol)!=null&&mp.get(selfCol).equals(mp.get(parentCol))){
				continue;//如果当前节点ID和父ID一样，避免无限循环
			}
			setChilds(list, parentCol, selfCol, mp);
		}

		for (Iterator<Map> it = list.iterator(); it.hasNext();) {
			Map mp = it.next();
			if (mp != null) {//不为空则说明是一个根节点
				tree.add(mp);
			}
		}
		return tree;
	}

	/**
	 * 根据当前节点找子节点
	 * 
	 * @param list
	 *            数据库查出的原数据
	 * @param parentCol
	 *            父ID列名
	 * @param selfCol
	 *            主键列名
	 * @param mp
	 *            当前节点
	 */
	@SuppressWarnings("unchecked")
	private static void setChilds(List<Map> list, String parentCol, String selfCol,
			Map mp) {
		String sid = (String) mp.get(selfCol);//当前节点主键
		for (int i = 0; i < list.size(); i++) {
			Map temp = list.get(i);
			if (temp == null)
				continue;//为空,说明已经被设为一个子节点了
			String pid = (String) temp.get(parentCol);//循环中的节点父ID
			//当循环节点的父ID=当前结点的主键时,说明循环节点就是一个子节点
			if (!"true".equals(temp.get("_isChildren"))&&
					(pid == sid || (sid != null && sid.equals(pid)))) {
				temp.put("_isChildren", "true");//若已构成其他节点的子节点，则打上标记，避免无限循环
				//递归,寻找叶节点
				setChilds(list, parentCol, selfCol, temp);
				//获取子节点集合
				List<Map> arr = (List<Map>) mp.get("children");
				if (arr == null)//如果还没有子节点集合,则新建一个
					arr = new ArrayList<Map>();
				arr.add(temp);//节点集合添加此子节点
				mp.put("children", arr);//得新赋给当前结点
				list.set(i, null);//被设置为子节点后,进行清空标记,不能删除
			}
		}
	}
}

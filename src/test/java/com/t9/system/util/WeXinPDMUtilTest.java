package com.t9.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

import com.t9.system.entity.AppForm;


@SuppressWarnings({ "rawtypes", "unused" })
public class WeXinPDMUtilTest {

	@Test
	public void testPDM() {
		PDMParser pdmParser=PDMParser.getInstance("E:\\baiduyun\\work\\微信文档\\wexin.pdm");
		List<Map> nodeList=pdmParser.findTableList();
		
		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			StringBuffer selectBuffer=new StringBuffer();
			AppForm sysTable=(AppForm)map.get("sysTable");
			if(!(sysTable.getTABLE_CODE().equals("WX_SHOP_SIGN")||sysTable.getTABLE_CODE().equals("WX_BLESSBAG_GIFT_LOG")
					||sysTable.getTABLE_CODE().equals("WX_BLESSBAG_SEND")||sysTable.getTABLE_CODE().equals("WX_BLESSBAG_DRAW")
					||sysTable.getTABLE_CODE().equals("WX_BLESSBAG_GIFT")||sysTable.getTABLE_CODE().equals("WX_BLESSBAG_NOVISA"))){
				continue;
			}
			selectBuffer.append("select ");
			ArrayList tableColumnMapList=(ArrayList)map.get("columnListMap");
			for (int i = 0; i < tableColumnMapList.size(); i++) {
				HashMap columnMap=(HashMap)tableColumnMapList.get(i);
				String colCode=(String)columnMap.get("colCode");
				String colName=(String)columnMap.get("colName");
				String colComment=(String)columnMap.get("colComment");
				String colType=(String)columnMap.get("colNcolTypeame");
				if(colComment.trim().length()!=0){
					colComment="("+colComment+")";
				}
				System.out.println("comment on column "+sysTable.getTABLE_CODE()+"."+colCode+" is '"+colName+colComment+"';");
	//			System.out.println(colName);
				selectBuffer.append(colCode).append(",");
			}
			System.out.println();
			selectBuffer.deleteCharAt(selectBuffer.length()-1);
			selectBuffer.append("from "+sysTable.getTABLE_CODE()+" WITH UR");
	//		System.out.println(selectBuffer.toString());
		}
	}
	
	@Test
	public void testPattern() {
		Pattern patternRen=Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
		boolean flag=patternRen.matcher("2014-03-12T15:13:11").find();
		System.out.println(flag);
		
	}
	
	

}

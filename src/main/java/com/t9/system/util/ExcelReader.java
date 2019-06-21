/**
* 
*/
package com.t9.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.t9.system.web.T9RuningTimeException;


/**
 * @功能/模块 ：文件上传导入组件
 * @author husq
 * @version 1.0 2014-11-15
 * @类描述  Excel文件解析类
 * @修订历史：
 */
public class ExcelReader{
	
	public void paras(File file){
		List list=new ArrayList();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			// 创建对工作表的引用。  
			// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）  
			// 也可用getSheetAt(int index)按索引引用，  
			HSSFSheet sheet = workbook.getSheetAt(0);  
			int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			
			for(int i=1;i<=totalRow;i++){  
				List rowlist=new ArrayList();
				HSSFRow row=sheet.getRow(i);  
				int totalCell=row.getLastCellNum();
				for (int j = 0; j < totalCell; j++) {
				     HSSFCell cell=row.getCell(1);  
				     String value=cell.getStringCellValue();
				     rowlist.add(value);
				}
				list.add(rowlist);
			}
		}catch (IOException e) {
			LogUtil.logException(e);
			throw new T9RuningTimeException("system.DBIDUtil.getLocalIP",e);
		}  
	}
    
}

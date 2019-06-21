package com.t9.system.util.file;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

@SuppressWarnings("unchecked")
public class FileDirDiff {
	
	public HashMap compare(File file1,File file2){
		HashMap resultMap=new HashMap();
		HashMap diffMap=new LinkedHashMap();
		HashMap noExistsMap=new LinkedHashMap();
		resultMap.put("diffMap", diffMap);
		resultMap.put("noExistsMap", noExistsMap);
		File[] flist1=file1.listFiles();
		String fileName1=file1.getAbsolutePath();
		String fileName2=file2.getAbsolutePath();
		System.out.println("正在检查目录："+fileName1+"...");
		for (int i = 0; i < flist1.length; i++) {
			File filet=flist1[i];
			String fileName=filet.getAbsolutePath();
			String fileShortName=filet.getName();
			if(fileShortName.equals(".svn")){
				continue;
			}else if(filet.isDirectory()){
				HashMap tMap=compare(new File(fileName1+"\\"+fileShortName),new File(fileName2+"\\"+fileShortName));
				HashMap tdiffMap=(HashMap)tMap.get("diffMap");
				diffMap.putAll(tdiffMap);
				HashMap tnoExistsMap=(HashMap)tMap.get("noExistsMap");
				noExistsMap.putAll(tnoExistsMap);
			}else{
				File filet2=new File(fileName2+"\\"+fileShortName);
				
				if(!filet2.exists()){
					noExistsMap.put(fileName,"");
					continue;
				}
				String md51=FileUtil.Md5(FileUtil.getFileContent(filet));
				String md52=FileUtil.Md5(FileUtil.getFileContent(filet2));
				if(!md51.equals(md52)){
					diffMap.put(fileName,filet.getName()+".html");
				//	System.out.println(filet.getAbsolutePath());
				}
				
			}			
		}
		return resultMap;
	}
	
	public void compDirFiles(String dir1,String dir2) throws Exception{

//		String fileName1="D:\\test\\perbank_test.war\\errorpage\\error_receipt.html";
//		String fileName2="D:\\test\\perbank_prod.war\\errorpage\\error_receipt.html";
//		FileDiff.getDiffResultFile(fileName1, fileName2);

//		System.exit(-1);
		File file1=new File(dir1);
		File file2=new File(dir2);
		if(!file1.isDirectory()){
			System.out.println(dir1+" is not a directory!");
			System.exit(-1);
		}
		if(!file2.isDirectory()){
			System.out.println(dir2+" is not a directory!");
			System.exit(-1);
		}

		HashMap map=compare(file1,file2);
		map.put("templateFtl", "fileList.html.ftl");
		int noExistsFiles=((HashMap)map.get("noExistsMap")).keySet().size();
		int noEqualFiles=((HashMap)map.get("diffMap")).keySet().size();
		System.out.println(noExistsFiles);
		System.out.println(noEqualFiles);
		if(((HashMap)map.get("noExistsMap")).keySet().size()==0){
			System.out.println("测试文件夹所有文件都在生成文件夹中存在！");
	//		System.exit(0);
		}
		System.out.println("测试文件夹中以下文件在生成文件夹中不存在！");
		
		HashMap diffMap=(HashMap)map.get("diffMap");
		Iterator diffIter=diffMap.keySet().iterator();
		while(diffIter.hasNext()){
			String fileName=(String)diffIter.next();
			String fileName2=dir2+fileName.substring(dir1.length());
			
			
			FileDiff.getDiffResultFile(fileName, fileName2);
		}
    	HtmlFileReport.printResultHtml(map);
    	
    	
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		if(args.length!=2){
//			System.out.println("args errors!");
//			System.exit(-1);
//		}
//		String dir1=args[0];
//		String dir2=args[1];
		String dir1="D:\\t1";
		String dir2="D:\\t2";
		FileDirDiff dirDiff=new FileDirDiff();
		dirDiff.compDirFiles(dir1, dir2);
	}
}

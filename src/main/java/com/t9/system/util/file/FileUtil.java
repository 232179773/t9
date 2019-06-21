package com.t9.system.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import com.t9.system.web.T9RuningTimeException;

@SuppressWarnings("unchecked")
public class FileUtil {

	public static void writeToFile(String fileName,String content){
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeToFile(String fileName,byte[] bytes){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void copyFiles(String sourcePath,String destPath){
		File srcFile = new File(sourcePath);
		File destFile = new File(destPath);
		if (!srcFile.exists()){
			System.err.println(sourcePath + "不存在！");
			return;
	//		throw new RuntimeException(sourcePath + "不存在！");
		}
		if (srcFile.isDirectory()&&!destFile.exists()){
			destFile.mkdirs();
		}else{
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
		}
		
		if(srcFile.isDirectory()){
			File[] files=srcFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				String destFileName=destPath+File.separatorChar+files[i].getName();
				copyFiles(files[i].getAbsolutePath(),destFileName);
			}
		}else{
			try {
				FileInputStream fileInputStream=new FileInputStream(srcFile);
				FileOutputStream fileOutputStream=new FileOutputStream(destFile);
				byte[] bytes=new byte[1024];
				int bi=0;
				while(true){
					bi=fileInputStream.read(bytes);
					if(bi==-1){
						break;
					}
					fileOutputStream.write(bytes, 0, bi);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("copy "+sourcePath + " error！");
			}
		}
	}

	public ArrayList listFiles(File dirFile){
		ArrayList result=new ArrayList();
		if (!dirFile.exists())
			throw new RuntimeException(dirFile.getAbsolutePath() + "不存在！");
		if (dirFile.isFile()){
			result.add(dirFile.getAbsolutePath());
			return result;
		}
		
		if(dirFile.isDirectory()){
			File[] files=dirFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				ArrayList tResult=listFiles(files[i]);
				result.addAll(tResult);
			}
		}
		return result;
	}
	


	public static void deleteDirFiles(File destFile){
		if (destFile.isDirectory()) {
			for (File temp : destFile.listFiles())
				deleteDirFiles(temp);
		}
		destFile.delete();
	}
	
	public static void compress(String sourcePath,String destFile){
		compress(sourcePath,destFile,false);
	}
	public static void compress(String sourcePath,String destFile,boolean lowerFlag){
		File srcdir = new File(sourcePath);
		if (!srcdir.exists())
			throw new RuntimeException(sourcePath + "不存在！");

		Project prj = new Project();
		Zip zip = new Zip();
		// 设置压缩的时候文件名编码为gb2312
	//	zip.setEncoding("gb2312");
		zip.setProject(prj);
		zip.setDestFile(new File(destFile));
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		// fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
		// eg:zip.setIncludes("*.java");
		// fileSet.setExcludes(...); 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		
		zip.execute(); 
	}

	public static String Md5(String[] plainTexts) {
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < plainTexts.length; i++) {
			stringBuffer.append(plainTexts[i]);
		}
		String plainText=stringBuffer.toString();
		return Md5(plainText);
	}
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
	//		System.out.println("result: " +buf.toString() );// 32位的加密
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}	

	public static String[] getFileContent(String fileName){
		return getFileContent(new File(fileName));
	}
	public static String[] getFileContent(File file){
		String content=null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuffer buf = new StringBuffer();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line).append("\r\n");
            }
            content = buf.toString();
		} catch (FileNotFoundException e) {
			throw new T9RuningTimeException(file.getAbsolutePath()+" is not exists!");
		}catch (IOException e) {
			throw new T9RuningTimeException(e);
		}
		return content.split("\r\n");
	}
	public static byte[] getFileBytes(String fileName){
		return getFileBytes(new File(fileName));
	}

	public static byte[] getFileBytes(File file){
		try {
			byte[] bytes=new byte[(int)file.length()];
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytes);
			return bytes;
		}catch (IOException e) {
			throw new T9RuningTimeException(e);
		}
	}
	
}

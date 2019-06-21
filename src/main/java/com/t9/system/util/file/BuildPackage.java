package com.t9.system.util.file;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.t9.system.util.EncrytpUtils;

public class BuildPackage {
	String version="20140718";
	String projectDir="D:\\work\\weixin\\workspace\\Evertalk-wechat201407";

	SimpleDateFormat dateFormat=new SimpleDateFormat("MM.dd");
	String currentDate=dateFormat.format(new Date());
	public void run(){
		String baseFileDir=projectDir+"\\target\\wx_"+version+"_"+currentDate;
		String packageDir="wxChat_"+version;
		String appDir=packageDir+File.separatorChar+"apps";
		String appPackageInstall=appDir+File.separatorChar+"wxChat_"+version+"_install";
		String packageWar=appPackageInstall+File.separatorChar+"wxChat.war";
		String webInf=packageWar+File.separatorChar+"WEB-INF";
		String libDir=webInf+File.separatorChar+"lib";
		
		String packageWarFullPath=baseFileDir+File.separatorChar+packageWar;
		String libDirFullPath=baseFileDir+File.separatorChar+libDir;
		
		File file=new File(libDirFullPath);
		if(!file.exists()){
			file.mkdirs();
		}
	//	System.out.println(libDir);
		
		SVNLog svnLog=new SVNLog(projectDir+File.separatorChar+"svnLog.xml");
		HashMap fileSvnMap=svnLog.getSvnFileMap();
		Iterator fileIter=fileSvnMap.keySet().iterator();
		while(fileIter.hasNext()){
			String projFile=(String)fileIter.next();
			
//			if(projFile.startsWith("/src")||projFile.startsWith("/conf")){
//				continue;
//			}
			String webFile=projFile;
//			if(projFile.startsWith("/WebRoot")){
//				webFile=webFile.substring(8);
//			}
			FileUtil.copyFiles(projectDir+projFile, packageWarFullPath+webFile);
		}
//		FileUtil.copyFiles(projectDir+"\\target\\wxchat.jar",libDirFullPath+"\\wxchat.jar");
//		String appDirZip=baseFileDir+File.separatorChar+appPackageInstall+".zip";
//		String sourcePath=baseFileDir+File.separatorChar+appPackageInstall;
//		FileUtil.compress(sourcePath,appDirZip);
//		FileUtil.deleteDirFiles(new File(sourcePath));
//		
//		appDirZip=baseFileDir+File.separatorChar+packageDir+".zip";
//		sourcePath=baseFileDir+File.separatorChar+packageDir;
//		FileUtil.compress(sourcePath,appDirZip);
//		FileUtil.deleteDirFiles(new File(sourcePath));
		
		try {
	//		FileZipUtil.zip(baseFileDir+File.separatorChar+packageWar, baseFileDir+File.separatorChar+appPackageInstall+".zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	FileUtil.copyFiles(baseFileDir, baseFileDir+"_bak");
		
	}

    
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new BuildPackage().run();
		
	}

}

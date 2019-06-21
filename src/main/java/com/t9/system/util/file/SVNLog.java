package com.t9.system.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.t9.system.entity.Node;
import com.t9.system.util.SAXHandler;

@SuppressWarnings("unchecked")
public class SVNLog {

	private Node rootNode;
	
	private String sourcePath;
	private String currentPath;
	private String copyVersion;
	public SVNLog(String fileName){
		SAXHandler saxHandler=new SAXHandler();
		try {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			SAXParser parser = spfactory.newSAXParser();
			File file=new File(fileName);
		//	System.out.println(file.getAbsolutePath());
			InputStream inputStream=new FileInputStream(file);
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8"); 
			InputSource inputSource = new InputSource(inputStreamReader); 
			parser.parse(inputSource, saxHandler);
			rootNode=saxHandler.getRootNode();
	//		System.out.println(rootNode.toXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap getSvnFileMap(){
		HashMap resultMap =new HashMap();
		List<Node> nodeList=rootNode.childNode("logentry");
		for (int i = 0; i < nodeList.size(); i++) {
			Node node=nodeList.get(i);
		//	String revisionStr=(String)node.getAttrMap().get("revision");
			String authorMsgdate=node.childNodeValue("author")+" "+node.childNodeValue("msg")+" "+node.childNodeValue("date");
			List<Node> pathNodeList=node.findNodeList("path");
			for (Iterator iterator = pathNodeList.iterator(); iterator.hasNext();) {
				Node pathNode = (Node) iterator.next();
				HashMap attrMap =pathNode.getAttrMap();
				String kind=(String)attrMap.get("kind");
				if(!"file".equals(kind)){
					if(sourcePath!=null){
						System.out.println(pathNode);
						throw new RuntimeException();
					}
					sourcePath=(String)attrMap.get("copyfrom-path");
					currentPath=pathNode.getNodeValue();
					copyVersion=(String)attrMap.get("copyfrom-rev");
				}
				if("file".equals(kind)){
					String fileStr=pathNode.getNodeValue();
					String projFile=fileStr.substring(currentPath.length());
					if(resultMap.get(projFile)==null){
						ArrayList revList=new ArrayList();
						revList.add(authorMsgdate);
						resultMap.put(projFile, revList);
					}else{
						ArrayList revList=(ArrayList)resultMap.get(projFile);
						revList.add(authorMsgdate);
					}
				}
			}
		}
		return resultMap;
	}
	public static void getSvnLog(String url){
		try {
			Runtime.getRuntime().exec("svn log "+url+" -r 1:HEAD --stop-on-copy -v --xml >svnLog.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public String getCopyVersion() {
		return copyVersion;
	}
}

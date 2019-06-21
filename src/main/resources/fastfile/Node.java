package com.arp.system.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node implements Serializable {
	private String nodeValue;
	private String nodeTag;
	private String attrStr;
	private Node parentNode;
	private List<Node> nodeList=new ArrayList<Node>();
	public String getNodeValue() {
		if(nodeList.size()>0)
			nodeValue=null;
		return nodeValue;
	}
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	public Node[] getNodeList() {
		Node[] listNode=new Node[nodeList.size()];
		for (int i = 0; i < nodeList.size(); i++) {
			listNode[i]=nodeList.get(i);
			
		}
		return listNode;
	}
	public Node getFirstNode() {
		return (Node)nodeList.get(0);
	}
	public void addChildNode(Node node){
		nodeList.add(node);
	}
	public String getNodeTag() {
		return nodeTag;
	}
	public void setNodeTag(String nodeTag) {
		this.nodeTag = nodeTag;
	}
	@Override
	public String toString() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("<").append(nodeTag);
		if(attrStr.length()!=0){
			stringBuffer.append(attrStr);
		}
		stringBuffer.append(">");
		if(this.getNodeValue()!=null){
			stringBuffer.append(getNodeValue());
		}else{
			for (int i = 0; i < nodeList.size(); i++) {
				stringBuffer.append(nodeList.get(i));
			}
		}
		return stringBuffer.append("</").append(nodeTag).append(">").toString();
	}

	//ѡ��idΪvalueֵ(#value),�ڵ�ֵΪ$value$,tagNameΪvalue����ݣ��м�ո��ʾ��������ڵ�
	public List<Node> select(String value){
		if(value.contains(" ")){
			List<Node> resultList = new ArrayList<Node>();
			String[] values = value.split(" ");
			List<Node> nodeList = findNodeList(this, values[0]);
			for (int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.get(i);
				resultList.addAll(findNodeList(node, values[1]));
			}
			return resultList;
		}
		return findNodeList(this,value);
	}
//	
//	public List<Node> findNodeList(List<Node> nlist,String value){
//		List<Node> resultList=new ArrayList<Node>();
//		if(value.length()==0)
//			return nlist;
//		String[] values=value.split(" ");
//		for (int k = 0; k < nlist.size(); k++) {
//			List<Node> nodeList=findNodeList(nlist.get(k),values[0]);
//			if(k==values.length-1)
//				resultList.addAll(nodeList);
//			else{
//				for (int i = 0; i < nodeList.size(); i++) {
//					Node node=nodeList.get(i);
//				}
//			}
//			
//		}
//		return resultList;
//		
//	}
	

	public List<Node> childNode(String tagName){
		List<Node> nodeList=new ArrayList<Node>();
		Node[] nodes=getNodeList();
		for (int i = 0; i < nodes.length; i++) {
			if(nodes[i].getNodeTag().equals(tagName));
				nodeList.add(nodes[i]);
		}
		return nodeList;
	}
	

	public String childNodeValue(String tagName){
		String result="";
		List<Node> nodeList=new ArrayList<Node>();
		Node[] nodes=getNodeList();
		for (int i = 0; i < nodes.length; i++) {
			if(nodes[i].getNodeTag().equals(tagName))
				nodeList.add(nodes[i]);
		}
		if(nodeList.size()==1)
			return nodeList.get(0).getNodeValue();
		return result;
	}
	public List<Node> findNodeList(String tagName){
		return findNodeList(this,tagName);
	}
	
	private List<Node> findNodeList(Node node,String value){
		List<Node> nodeList=new ArrayList<Node>();
		if(value.matches("^\\$.*\\$$")&&value.replaceAll("\\$", "").equals(node.getNodeValue())){//$value$��ʽƥ��
			nodeList.add(node);
			return nodeList;
		}else if(value.startsWith("#")&&node.getAttrStr().toLowerCase().contains("id="+value.replaceFirst("#", ""))){
			nodeList.add(node);
			return nodeList;
		}else if(value.equals(node.getNodeTag())){
			nodeList.add(node);
			return nodeList;
		}
		Node[] nodes=node.getNodeList();
		for (int i = 0; i < nodes.length; i++) {
			List<Node> rList=findNodeList(nodes[i],value);
			nodeList.addAll(rList);
		}
		return nodeList;
	}
	

	private String getXml(String t){
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(t).append("<").append(nodeTag).append(attrStr).append(">");
		if(this.getNodeValue()!=null){
			return stringBuffer.append(getNodeValue()).append("</").append(nodeTag).append(">\r\n").toString();
		}
		stringBuffer.append("\r\n");
		for (int i = 0; i < nodeList.size(); i++) {
			stringBuffer.append(nodeList.get(i).getXml(t+"\t"));
		}
		return stringBuffer.append(t).append("</").append(nodeTag).append(">\r\n").toString();
	}
	public String toXml() {
		return getXml("");
	}
	public String getAttrStr() {
		return attrStr;
	}
	public HashMap getAttrMap() {
		HashMap map=new HashMap();
		if(attrStr==null){
			return map;
		}
		String[] keyValus= attrStr.split(" ");
		for (int i = 0; i < keyValus.length; i++) {
			if(keyValus[i].length()==0){
				continue;
			}
			String[] vs=keyValus[i].split("=");
//			if(vs.length!=2){
//				System.out.println(keyValus[i]);
//			}
			map.put(vs[0], vs[1]);
		}
		return map;
	}
	
	public void setAttrStr(String attrStr) {
		this.attrStr = attrStr;
	}
}
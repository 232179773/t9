package com.t9.system.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.t9.system.entity.Node;

public class SAXHandler extends DefaultHandler {
	
	private Node rootNode;
	private Node currNode;
	//文档内容中有'号解析有问题
	public void characters(char[] ch, int start, int length)
			throws SAXException {	
		currNode.setNodeValue(new String(ch,start,length));
//		System.out.println(currNode.getNodeTag()+":"+currNode.getNodeValue());
//		if("PROVINCE".equals(currNode.getNodeValue())){
//			System.out.println("PROVINCE");
//		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attr) throws SAXException {
		String attrValue="";
		for(int i=0;i<attr.getLength();i++){
			attrValue+=" "+attr.getQName(i)+"="+attr.getValue(i);
		}
		Node node =new Node();
		node.setParentNode(currNode);
		node.setNodeTag(qName);
		node.setAttrStr(attrValue);
		currNode.addChildNode(node);
		currNode=node;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currNode=currNode.getParentNode();
		super.endElement(uri, localName, qName);
	}
	@Override
	public void startDocument() throws SAXException {
		rootNode=new Node();
		currNode=rootNode;
		super.startDocument();
	}

	public Node getRootNode(){
		return rootNode.getFirstNode();
	}
}
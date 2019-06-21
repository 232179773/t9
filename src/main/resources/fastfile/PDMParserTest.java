package com.arp.system.common;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PDMParserTest {


	@Test
	public void testFindPackage() {
		PDMParser pdmParser=PDMParser.getInstance();
		String[] ps=pdmParser.findPackage();
		Assert.assertTrue(ps.length> 0);
	}

	@Test
	public void testFindPackageTables() {
		PDMParser pdmParser=PDMParser.getInstance("");
		String[] ps=pdmParser.findPackage();
		List<Node> list=pdmParser.findPackageTables(ps[0]);
		Assert.assertTrue(list.size()> 0);
		Node node=list.get(0);
		Assert.assertTrue(node.toString().length()> 0);
		Assert.assertTrue(node.childNode("a:Code")!=null);
		Assert.assertTrue(node.getAttrMap()!=null);
		pdmParser.findTableInfo(ps[0], node.childNodeValue("a:Code"));
	}
	
	@Test
	public void testFindPackageTablesList() {
		PDMParser pdmParser=PDMParser.getInstance("");
		List list=pdmParser.findPackageTableList();
		Assert.assertTrue(list.size()> 0);
	}

	@Test
	public void testFindTableList() {
		PDMParser pdmParser=PDMParser.getInstance(PDMParser.fileName);
		List list=pdmParser.findTableList();
		Assert.assertTrue(list.size()> 0);
	}

}

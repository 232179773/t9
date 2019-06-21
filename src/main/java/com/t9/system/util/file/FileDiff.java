package com.t9.system.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.t9.system.util.FreeMarkerUtil;


@SuppressWarnings("unchecked")
public class FileDiff
{
	
	public void blockDiff(String[] ablocks,String[] bblocks){
		String[] result=this.getBlockString(ablocks, bblocks);
		System.out.println(result[0]);
		System.out.println("----------------------");
		System.out.println(result[1]);
	}
	
	public static void getDiffResultFile(String fromFile, String toFile) throws Exception{

        String[] aLines = FileUtil.getFileContent(fromFile);
        String[] bLines = FileUtil.getFileContent(toFile);
        List     diffs  = (new Diff(aLines, bLines)).diff();
        
		Iterator it     = diffs.iterator();
        HashMap diffMap=new HashMap();
        while (it.hasNext()) {
            Difference diff     = (Difference)it.next();
            int        delStart = diff.getDeletedStart();
            diffMap.put(delStart,diff);
        }


		HashMap dataMap=new HashMap();
		ArrayList dataList=new ArrayList();
		
        StringBuilder stringBuilder=new StringBuilder();        
        stringBuilder.append("<TR>\r\n");
        stringBuilder.append("<TH></TH>\r\n");
        stringBuilder.append("<TH>1.txt</TH>\r\n");
        stringBuilder.append("<TH></TH>\r\n");
        stringBuilder.append("<TH>2.txt</TH></TR>\r\n");
        int left=0,right=0;
        while (left<=aLines.length) {
        	Difference diff =(Difference)diffMap.get(left);
        	diffMap.remove(left);
        	
        	String leftLine=(left+1)+"";
        	String rightLine=(right+1)+"";
        	String leftStr="";
        	String rightStr="";

        	HashMap tMap=new HashMap();
        	if(diff!=null){
        		int leftL=diff.getDeletedEnd()-diff.getDeletedStart()+1;
        		int rightL=diff.getAddedEnd()-diff.getAddedStart()+1;
                leftLine=leftL<0?"":(leftL>1?((left+1)+"~"+(left+leftL)):""+(left+1))+"";
                rightLine=rightL<0?"":(rightL>1?((right+1)+"~"+(right+rightL)):""+(right+1))+"";
                int liftInt=leftL<0?0:(leftL>1?(leftL-1):1);
                int rightInt=rightL<0?0:(rightL>1?(rightL-1):1);
                
            	
                if(leftL>0&&rightL>0){
                	String[] leftArray=Arrays.copyOfRange(aLines, left,left+leftL);
                	String[] rightArray=Arrays.copyOfRange(bLines, right,right+rightL);
                	String[] result=getBlockString(leftArray,rightArray);
                	leftStr=result[0];
                	left=left+leftL;
                	rightStr=result[1];
                	right=right+rightL;
            //        System.out.println(leftLine+":"+leftStr);
           //         System.out.println(rightLine+":"+rightStr);
                	
                	
                }else if(leftL>0){
                	String[] leftArray=Arrays.copyOfRange(aLines, left,left+leftL);
                	leftStr="<font color='#FF0000'>"+toHtmlString(leftArray)+"</font>";
                	left=left+leftL;
                }else if(rightL>0){
                	String[] rightArray=Arrays.copyOfRange(bLines, right,right+rightL);
                	rightStr="<font color='#FF0000'>"+toHtmlString(rightArray)+"</font>";
                	right=right+rightL;
                }        		
        	}else{
        		if(left==aLines.length){
        			break;
        		}
            	leftStr=aLines[left++];
            	rightStr=bLines[right++];
        	}
        	leftStr=leftStr.replaceAll("<", "&lt");
        	leftStr=leftStr.replaceAll(">", "&gt");
        	rightStr=rightStr.replaceAll("<", "&lt");
        	rightStr=rightStr.replaceAll(">", "&gt");
        	tMap.put("leftLine", leftLine);
        	tMap.put("leftCode", leftStr);
        	tMap.put("rightLine", rightLine);
        	tMap.put("rightCode", rightStr);
        	dataList.add(tMap);
		}
        dataMap.put("dataList", dataList);

        String fileName=new File(fromFile).getName();
        dataMap.put("templateFtl", "fileDiff.html.ftl");
        dataMap.put("fileName", fileName+".html");
        FreeMarkerUtil.generateFile(dataMap);
    	HtmlFileReport.printResultHtml(dataMap);
	}

	
	private static String[] getBlockString(String[] ablocks,String[] bblocks){
		String ablockLines="";
		for (int i = 0; i < ablocks.length; i++) {
			ablockLines+=ablocks[i]+"\r\n\r\n\r\n";
		}
		String bblockLines="";
		for (int i = 0; i < bblocks.length; i++) {
			bblockLines+=bblocks[i]+"\r\n\r\n\r\n";
		}
		String[] aLines=ablockLines.split("");
		String[] bLines=bblockLines.split("");
		StringBuilder leftBuild=new StringBuilder();
		StringBuilder rightBuild=new StringBuilder();
		List   diffs  = (new Diff(aLines, bLines)).diff();
		Iterator it = diffs.iterator();
		HashMap diffMap = new HashMap();
		while (it.hasNext()) {
			Difference diff = (Difference) it.next();
			int delStart = diff.getDeletedStart();
			diffMap.put(delStart, diff);
		}
		int left=0,right=0;
        while (left<aLines.length&&right<bLines.length) {
        	Difference diff =(Difference)diffMap.get(left);
        	diffMap.remove(left);
        	if(diff!=null){
        		int leftL=diff.getDeletedEnd()-diff.getDeletedStart()+1;
        		int rightL=diff.getAddedEnd()-diff.getAddedStart()+1;
                if(leftL>0&&rightL>0){
                	String[] leftArray=Arrays.copyOfRange(aLines, left,left+leftL);
                	String[] rightArray=Arrays.copyOfRange(bLines, right,right+rightL);
               // 	left=left+leftL;
               // 	right=right+rightL;
              //  	System.out.println("kkkkkkkkkkk:"+leftL+Arrays.toString(leftArray));
              //  	System.out.println("ggggggggggg:"+rightL+Arrays.toString(rightArray));

                	leftBuild.append("<font color='#FF0000'>");
            		for (int i = left; i < left+leftL; i++) {
            			leftBuild.append(aLines[i]);
					}
                	leftBuild.append("</font>");
                	left=left+leftL;
                	

            		rightBuild.append("<font color='#FF0000'>");
            		for (int i = right; i < right+rightL; i++) {
                		rightBuild.append(bLines[i]);
					}
            		rightBuild.append("</font>");
                	right=right+rightL;
                	
                }else if(leftL>0){
                	leftBuild.append("<font color='#FF0000'>");
            		for (int i = left; i < left+leftL; i++) {
            			leftBuild.append(aLines[i]);
					}
                	leftBuild.append("</font>");
                	left=left+leftL;
                }else if(rightL>0){
            		rightBuild.append("<font color='#FF0000'>");
            		for (int i = right; i < right+rightL; i++) {
                		rightBuild.append(bLines[i]);
					}
            		rightBuild.append("</font>");
                	right=right+rightL;
                }        		
        	}else{
        		leftBuild.append(aLines[left++]);
        		rightBuild.append(bLines[right++]);
        	}
		}
		return new String[]{leftBuild.toString().replaceAll("\r\n\r\n\r\n", "<br/>"),rightBuild.toString().replaceAll("\r\n\r\n\r\n", "<br/>")};
	}
	private static String toHtmlString(String[] args){
		String result="";
		if(args.length==0)
			return result;
		for (int i = 0; i < args.length-1; i++) {
			result+=args[i]+"<br/>";
		}
		result+=args[args.length-1];
		return result;
	}

}

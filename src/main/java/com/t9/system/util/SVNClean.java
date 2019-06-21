package com.t9.system.util;

import java.io.File;


public class SVNClean {
	public static void main(String[] args) {
		SVNClean svn = new SVNClean();
		File f = new File("D:\\work\\code");
		svn.setF(f);
		try {
			svn.clean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("删除完毕,共删除.svn文件夹" + svn.cnt + "个");
	}

	private File f;

	private int cnt;

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public void clean() throws Exception {
		if (f.isDirectory() && f.getName().equals(".svn")) {
			deleteFile(f);
			cnt++;
		} else if (f.isDirectory() && !f.getName().equals(".svn")) {
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				f = fs[i];
				clean();
			}
		}

	}

	private static void deleteFile(File f) {
		if (f.isDirectory()) {
			for (File temp : f.listFiles())
				deleteFile(temp);
		}
		f.delete();
	}
	
	
	private static int clean(File f){
		int ct=0;
		if (f.isDirectory() && f.getName().equals(".svn")) {
			deleteFile(f);
			ct++;
		} else if (f.isDirectory() && !f.getName().equals(".svn")) {
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				f = fs[i];
				ct+=clean(f);
			}
		}
		return ct;
	}
	
	public static int clean(String filePath){
		File f = new File(filePath);
		return clean(f);
	}
}

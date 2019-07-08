package com.t9.system.util;

public class Sort {

	public static void maopao(int[] a){
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length-i; j++) {
				if(a[j]<a[j-1]){
					int temp=a[j];
					a[j]=a[j-1];
					a[j-1]=temp;
					
				}
			}
		}
	}
	public static void charu(int[] a){
		for (int i = 1; i < a.length; i++) {
			int temp=a[i];			
			for (int j = 0; j < i; j++) {
				if(a[j]>temp){
					for (int k = i; k>j; k--) {
						a[k]=a[k-1];
					}
					a[j]=temp;
					break;
				}
			}
		}
	}

	public static void quickSork(int[] a,int p,int q){
		if(p>=q){
			return;
		}
		int i=p;
		int j=q;
		int temp=a[i];
		while(i<j){
			while(i<j&&a[j]>temp){
				j--;
			}
			if(i<j){
				a[i]=a[j];
				i++;
			}
			while(i<j&&a[i]<temp){
				i++;
			}
			if(i<j){
				a[j]=a[i];
				j--;
			}
		}
		a[i]=temp;
		quickSork(a,p,i-1);
		quickSork(a,i+1,q);
		
	}
	public static void main(String[] args){
		int a[]=new int[]{12,45,65,32,82,14,8};
		quickSork(a,0,6);
		String ss="";
		for (int i = 0; i < a.length; i++) {
			ss=ss+a[i]+",";
		}
		System.out.println(ss);
	}
}

package com.t9.system.util;

import static org.junit.Assert.assertEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.t9.system.entity.RenRenLoan;

@SuppressWarnings("unchecked")
public class BeanUtilTest {

	@Test
	public void testCopyMapToBean() {
		Map map=new HashMap();
		RenRenLoan loan=new RenRenLoan();
		map.put("loanId", "12344");
		map.put("title", "我们");
		map.put("amount", "41500.0");
		map.put("openTime", "Mar 12, 2014 5:48:11 PM");
		map.put("passTime", "2014-03-14T17:46:04");
		BeanUtil.copyMapToBean(map, loan);
		assertEquals(loan.getTITLE(), "我们");
		assertEquals(loan.getLOAN_ID(), new Long(12344));
		assertEquals(loan.getAMOUNT(), new Double(41500));
	//	assertEquals(loan.getOPEN_TIME(), new Double(5000));
		System.out.println(loan.getOPEN_TIME());
	}
	
	@Test
	public void testPattern() {
		
		String[] arr = new String[] { "evertalk", "1397612346", "1658220462" };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
				byte[] digest = md.digest(content.toString().getBytes());
				tmpStr = byteToStr(digest);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		assertEquals(tmpStr, "1867172efc6545416d1543f5d783c9176507f5d8".toUpperCase());
	}
	
	// 将字节数组转换为十六进制字符串
	private static String byteToStr(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexStr(bytearray[i]);
		}
		return strDigest;
	}
	

	// 将字节转换为十六进制字符串
	private static String byteToHexStr(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];

		String s = new String(ob);
		return s;
	}
	

}

package com.t9.system.util;

import java.text.DecimalFormat;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


public class DataUtil {
	
    public static String encodePassword(String password, String salt) {
        PasswordEncoder passwordEncoder = new ShaPasswordEncoder();
        return passwordEncoder.encodePassword(password, salt);

    }
    
    private static DecimalFormat df = new DecimalFormat("#.00");
    public static String douleToMoneyString(double money){
    	return df.format(money);
    }
    
	public static Double getDoubleFromStr(String str){
		if(str==null)
			return null;
		Double result=new Double(str);
		
		return result;
	}

	public static double getdoubleFromStr(String str){
		if(str==null)
			return 0;
		double result=Double.parseDouble(str);		
		return result;
	}
    
}

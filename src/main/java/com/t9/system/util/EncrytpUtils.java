package com.t9.system.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Cipher;

import com.t9.system.util.file.FileUtil;
  

@SuppressWarnings("restriction")
public class EncrytpUtils {  
  
	private static Random random=new Random();
    /**
     * Encode a string using Base64 encoding. Used when storing passwords as
     * cookies.
     *
     * This is weak encoding in that anyone can use the decodeString routine to
     * reverse the encoding.
     *
     * @param str
     * @return String
     * @throws IOException
     */
	public static String encodeString(String str)
    {
//        BASE64Encoder encoder = new BASE64Encoder();
//        String encodedStr = new String(encoder.encodeBuffer(str.getBytes()));
		
		String encodedStr=bcd2Str(str.getBytes()).trim();
		String[] ss=encodedStr.split("");
		StringBuilder stringBuilder=new StringBuilder();
		int veri=0,verify=0;
		for (int i = 1; i < ss.length; i++) {
			stringBuilder.append(ss[i]);
			veri=veri+ss[i].charAt(0)^17;
			if(i%17==0){
				char c=(char)('0'+random.nextInt(9));
				veri=veri+c;
				verify+=veri;
				stringBuilder.append(c).append(veri%10);
				veri=0;
			}
		}
		stringBuilder.append(verify%10);
        return stringBuilder.toString();
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     * @throws IOException
     */
    public static String decodeString(String str)
    {
    //    sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
    //    String value = new String(dec.decodeBuffer(bstr));
    	
		String[] ss=str.split("");
		StringBuilder stringBuilder=new StringBuilder();
		int k=0;
		int veri=0,verify=0;
		for (int i = 1; i < ss.length-1; i++) {
			stringBuilder.append(ss[i]);
			veri=veri+ss[i].charAt(0)^17;
			if((i-k)%17==0){
				veri=veri+ss[i+1].charAt(0);
				verify+=veri;
				if(!ss[i+2].equals(""+veri%10)){
					throw new RuntimeException("decode error!");
				}
				veri=0;
				i=i+2;
				k=k+2;
			}
		}
		if(!ss[ss.length-1].equals(""+verify%10)){
			throw new RuntimeException("decode error!");
		}
        byte[] bbyte=stringBuilder.toString().getBytes();
        bbyte=ASCII_To_BCD(bbyte,bbyte.length);
        String bstr=new String(bbyte);
		
        return bstr;
    }
    
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	} 
    
    /** 
     * 生成公钥和私钥 
     * @throws NoSuchAlgorithmException  
     * 
     */  
    public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException{  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        keyPairGen.initialize(512);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        map.put("public", publicKey);  
        map.put("private", privateKey);  
        return map;  
    }  
    /** 
     * 使用模和指数生成RSA公钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */  
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {  
        try {  
            BigInteger b1 = new BigInteger(modulus);  
            BigInteger b2 = new BigInteger(exponent);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 使用模和指数生成RSA私钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */  
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {  
        try {  
            BigInteger b1 = new BigInteger(modulus);  
            BigInteger b2 = new BigInteger(exponent);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 公钥加密 
     *  
     * @param data 
     * @param publicKey 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(byte[] data, RSAPublicKey publicKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        // 模长  
        int key_len = publicKey.getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        int dataEl=(int)Math.ceil((float)data.length/(key_len-11));
        String mi = "";  
        for (int i = 0; i <dataEl; i++) {
        	int to=(i+1)*(key_len-11)>data.length?data.length:(i+1)*(key_len-11);
        	byte[] tbyte=Arrays.copyOfRange(data, i*(key_len-11),to);
        	mi += bcd2Str(cipher.doFinal(tbyte)); 
		}
        return mi;  
    } 
    /** 
     * 私钥解密 
     *  
     * @param data 
     * @param privateKey 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey)  
            throws Exception {  
    	byte[] resultBytes=new byte[data.length];
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        //模长  
        int key_len = privateKey.getModulus().bitLength() / 8;  
        byte[] bcd = ASCII_To_BCD(data, data.length);  
   //     System.out.println(bcd.length);  
        //如果密文长度大于模长则要分组解密  
        byte[][] arrays = splitArray(bcd, key_len);  
        int begin=0;
        for(byte[] arr : arrays){  
        	byte[] ming=cipher.doFinal(arr); 
        	for (int i = 0; i < ming.length; i++) {
        		resultBytes[begin+i]=ming[i];
			}
        	begin=ming.length;
        }  
        return Arrays.copyOf(resultBytes, resultBytes.length);  
    }  
    /** 
     * 公钥加密 
     *  
     * @param data 
     * @param publicKey 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        // 模长  
        int key_len = publicKey.getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cipher.doFinal(s.getBytes()));  
        }  
        return mi;  
    }  
  
    /** 
     * 私钥解密 
     *  
     * @param data 
     * @param privateKey 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        //模长  
        int key_len = privateKey.getModulus().bitLength() / 8;  
        byte[] bytes = data.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
   //     System.out.println(bcd.length);  
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        byte[][] arrays = splitArray(bcd, key_len);  
        for(byte[] arr : arrays){  
            ming += new String(cipher.doFinal(arr));  
        }  
        return ming;  
    }  
    /** 
     * ASCII码转BCD码 
     *  
     */  
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        byte[] bcd = new byte[asc_len / 2];  
        int j = 0;  
        for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }  
    public static byte asc_to_bcd(byte asc) {  
        byte bcd;  
  
        if ((asc >= '0') && (asc <= '9'))  
            bcd = (byte) (asc - '0');  
        else if ((asc >= 'A') && (asc <= 'F'))  
            bcd = (byte) (asc - 'A' + 10);  
        else if ((asc >= 'a') && (asc <= 'f'))  
            bcd = (byte) (asc - 'a' + 10);  
        else  
            bcd = (byte) (asc - 48);  
        return bcd;  
    }  
    /** 
     * BCD转字符串 
     */  
    public static String bcd2Str(byte[] bytes) {  
        char temp[] = new char[bytes.length * 2], val;  
  
        for (int i = 0; i < bytes.length; i++) {  
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
  
            val = (char) (bytes[i] & 0x0f);  
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
        }  
        return new String(temp);  
    }  
    /** 
     * 拆分字符串 
     */  
    public static String[] splitString(String string, int len) {  
        int x = string.length() / len;  
        int y = string.length() % len;  
        int z = 0;  
        if (y != 0) {  
            z = 1;  
        }  
        String[] strings = new String[x + z];  
        String str = "";  
        for (int i=0; i<x+z; i++) {  
            if (i==x+z-1 && y!=0) {  
                str = string.substring(i*len, i*len+y);  
            }else{  
                str = string.substring(i*len, i*len+len);  
            }  
            strings[i] = str;  
        }  
        return strings;  
    }  
    /** 
     *拆分数组  
     */  
    public static byte[][] splitArray(byte[] data,int len){  
        int x = data.length / len;  
        int y = data.length % len;  
        int z = 0;  
        if(y!=0){  
            z = 1;  
        }  
        byte[][] arrays = new byte[x+z][];  
        byte[] arr;  
        for(int i=0; i<x+z; i++){  
            arr = new byte[len];  
            if(i==x+z-1 && y!=0){  
                System.arraycopy(data, i*len, arr, 0, y);  
            }else{  
                System.arraycopy(data, i*len, arr, 0, len);  
            }  
            arrays[i] = arr;  
        }  
        return arrays;  
    }  
    
    static String public_exponent="65537";
    static String private_exponent="2073050993205177214443801130886082902889458530291193742631395204814211391527664165048616855266957859746006868593733516534708258717481007088221854774443249";
    static String modulus="9444011048358661136035270034400195690717812018607949694900163182115179477724963718783167541601703464458902486319514203976106329644583500692165662608462831";
    
	public void sign() throws Exception{
		long beginTime=System.currentTimeMillis();
		 // TODO Auto-generated method stub  
//        HashMap<String, Object> map = RSAUtils.getKeys();  
//        //生成公钥和私钥  
//        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
//        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
//          
//        //模  
//        String modulus = publicKey.getModulus().toString();  
//        //公钥指数  
//        String public_exponent = publicKey.getPublicExponent().toString();  
//        //私钥指数  
//        String private_exponent = privateKey.getPrivateExponent().toString();  

        System.out.println("public:"+public_exponent);
        System.out.println("private:"+private_exponent);
        System.out.println("modulus:"+modulus);
        RSAPublicKey pubKey = EncrytpUtils.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = EncrytpUtils.getPrivateKey(modulus, private_exponent);  
        
        byte[] ming=FileUtil.getFileBytes("E:\\hh\\qq.txt");
        System.out.println("ming:"+bcd2Str(ming));  
            //加密后的密文  
            String mi = EncrytpUtils.encryptByPublicKey(ming, pubKey);  
            System.out.println("mi:"+mi);  
            //解密后的明文  
           // mi="1DBEE7042B319EAD0E956F85D7C4D7E4449BE02ED7FB57B26E257D012DBD25A6EDBDF93A7354BAB0C1E14608D6637104964318C4FDCE5DAE88CAF495AE1B47281CF87ACBDD0E6541CEFBBEA24D30FA46CF8FA31D19F626A28A69ADD1E9A2F0B393DC9A58570162D8C58A1C3F370D43B54DD0AC0CD43DE86F4669410A139F95B9";
            ming = EncrytpUtils.decryptByPrivateKey(mi.getBytes(), priKey);  
            System.out.println("ming:"+bcd2Str(ming));  
            FileUtil.writeToFile("E:\\hh\\acct2.xlsx", ming);
		long endTime=System.currentTimeMillis();
		System.out.println((endTime-beginTime)+"\t");
	}
	public void run(){

		
       Calendar calendar=Calendar.getInstance();
       calendar.add(Calendar.DAY_OF_YEAR, 1);
		String ming="simpleActivity.do?wxCode=guozhanglong&simpleActivityId=100009&srcChannel=WS&timestamp="+calendar.getTimeInMillis();//
		try{
			ming=EncrytpUtils.encodeString(ming);
			System.out.println(ming);
		//	ming="77656958696E4269630E642E646F3F777843266F64653D6F48417245646A6C316F4A455F61186769757A7549785743216F5A38434B3026738772634368616E6E65633C3D57582674696D65307374616D703D3133375938343233363633373035357";
			ming=EncrytpUtils.decodeString(ming);
			System.out.println(ming);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	//	new EncrytpUtils().sign();
		new EncrytpUtils().run();
	}
}  
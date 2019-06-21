package com.t9.system.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.t9.system.common.QRCodeUtil;

public class QRcodeTest {

	@Test
	public void testQRcode() {
		String imgPath = "showqrcode.jpg";
        String content = "http://192.168.23.10:8800/mobilebank/simpleActivity.do?wxCode=guozhanglong&simpleActivityId=201401&srcChannel=WX"; 
        QRCodeUtil.encoder(content,imgPath); 
		String decoderContent = QRCodeUtil.decoder(imgPath);

		System.out.println("解析结果如下："+decoderContent);
		assertEquals(content,decoderContent);
	}

	
}

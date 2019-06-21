package com.t9.system.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.config.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.t9.system.web.Logger;
import com.t9.system.web.T9Result;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;

@SuppressWarnings({ "unchecked", "serial" })
@Component
@Scope("prototype")
@ParentPackage("t9package")
public class RandomValidateCodeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1956280766385555459L;
	//public  static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
	private Random random = new Random();
	private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 随机产生的字符串

	private int width = 80;// 图片宽
	private int height = 26;// 图片高
	private int lineSize = 40;// 干扰线数量
	private int stringNum = 4;// 随机产生字符数量

	/**
	 * 
	 * @return 随机数的字体
	 */
	private Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	/**
	 * 字体颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	public String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}

	public String drowString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
				.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString
				.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.drawLine(x, y, x + xl, y + yl);
	}

	public void getRandcode() {
		HttpServletRequest request=this.getRequest();
		HttpServletResponse response=this.getResponse();
		HttpSession session = request.getSession();
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String randomString = "";
		for (int i = 1; i <= stringNum; i++) {
			randomString = drowString(g, randomString, i);
		}
		session.removeAttribute(ServletUtils.RANDOMCODEKEY);
		session.setAttribute(ServletUtils.RANDOMCODEKEY, randomString);
		System.out.println("验证码："+randomString);
		g.dispose();
	//	super.renderToOutput(image);
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String checkCode(){
		Map map = ServletUtils.getMapByRequest(this.getRequest());
		String name=(String)map.get("name");
		ServletUtils.checkRandomValidateCode(this.getRequest(), name);
		T9Result result=new T9Result();
		super.renderToOutput(result);
		return null;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}

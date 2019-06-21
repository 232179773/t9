package com.t9.system.common;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 邮件发送组件
 * 
 * @author HUSQ
 * @since 1.0
 * create on: 8/13/2013 
 *
 */
public class MailSender {

	public boolean sendout() {
		Properties props = System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", "smtp.163.com"); // 设置SMTP主机
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		MimeMessage mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
		Multipart mp = new MimeMultipart(); // mp 一个multipart对象
		
		try {
			mimeMsg.setSubject("发送邮件测试");
			mimeMsg.setFrom(new InternetAddress("t9team@163.com")); // 设置发信人
			mimeMsg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("suever@163.com"));
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse("232179773@qq.com"));
			BodyPart bp = new MimeBodyPart();
			// 转换成中文格式
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
							+ "发送邮件测试 <font color=red>欢迎你,java</font>", "text/html;charset=GB2312");
			mp.addBodyPart(bp);

//			BodyPart bp = new MimeBodyPart();
//			FileDataSource fileds = new FileDataSource(filename);
//			bp.setDataHandler(new DataHandler(fileds));
//			bp.setFileName(fileds.getName());
//			mp.addBodyPart(bp);
			
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在发送邮件....");
			Transport transport = session.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), "t9team@163.com",
					"t9t9t9");
			transport.sendMessage(mimeMsg,
					mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.send(mimeMsg);
			System.out.println("发送邮件成功！");
			transport.close();
			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}
}

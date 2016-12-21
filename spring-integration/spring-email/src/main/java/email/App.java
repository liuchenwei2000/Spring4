package email;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/21.
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(MailConfig.class);

		String to = "liuchenwei2000@163.com";
		String message = "This is your pics.";

		MailService mailService = (MailService) context.getBean("mailService");

		// 发送简单文本邮件
//		mailService.sendMail(to, message);

		// 发送附件邮件
//		try {
//			mailService.sendMailWithAttachment(to, message, "C:/Users/lcw/Pictures/WA2.jpg");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

		// 发送富文本邮件
//		try {
//			mailService.sendMailRichText(to, message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

		// 发送 Velocity 模板生成的邮件
//		try {
//			mailService.sendMailByVelocityTemplate(to, message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

		// 发送 FreeMarker 模板生成的邮件
//		try {
//			mailService.sendMailByFreeMarkerTemplate(to, message);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}

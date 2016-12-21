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

		MailService mailService = (MailService) context.getBean("mailService");

		mailService.sendMail("liuchenwei2000@163.com", "This is your pics.");
	}
}

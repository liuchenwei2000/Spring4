package email;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.velocity.VelocityEngineFactory;

import javax.mail.Session;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置 Spring 发送邮件
 * <p>
 *     Spring Email 抽象的核心是 MailSender 接口，
 *     它能够通过连接 Email 服务器实现邮件发送功能。
 *     Spring 自带了一个 MailSender 实现是 JavaMailSenderImpl，
 *     它会使用 JavaMail API 来发送 Email。
 * <p>
 * Created by liuchenwei on 2016/12/21.
 */
@Configuration
@ComponentScan
@PropertySource("classpath:config/mail.properties")// 保存邮件服务器信息的外部属性文件
public class MailConfig {

    /**
     * 基于配置文件配置邮件发送器
     * <p>
     *     按照这里的配置，会从注入的 Environment 中获取值，
     *     这样就能在 Spring 之外管理邮件服务器的配置（比如在属性文件中）。
     */
    @Bean
    public JavaMailSender mailSender(Environment env){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 设置邮件服务器主机名
        mailSender.setHost(env.getProperty("mail.host"));
        // 设置邮件服务器端口号，默认是标准的 SMTP 端口 25
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        // 设置邮件服务器认证相关的 u/p
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        return mailSender;
    }

    /**
     * 基于 JNDI 配置邮件发送器
     * <p>
     *     在这种情景下，就没必要为 JavaMailSenderImpl 配置详细的服务器信息了。
     *     直接使用 JNDI 中已经配置好的 Session 即可。
     */
    // @Bean
    public MailSender mailSenderJndi(Session session){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setSession(session);
        return mailSender;
    }

    // @Bean
    public Session session() throws NamingException {
        JndiTemplate template = new JndiTemplate();
        Session session = (Session) template.lookup("java:comp/mail/session");
        return session;
    }

    /**
     * 配置 VelocityEngine
     * <p>
     *     从 Spring4 开始，Velocity 不再被推荐为主要的模板技术。
     */
    @Bean
    public VelocityEngine velocityEngine() throws VelocityException, IOException {
        VelocityEngineFactory factory = new VelocityEngineFactory();

        // 配置为从类路径下加载 velocity 模板
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());

        factory.setVelocityProperties(props);
        return factory.createVelocityEngine();
    }

    /**
     * 配置 FreeMarker
     */
    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        return bean;
    }
}

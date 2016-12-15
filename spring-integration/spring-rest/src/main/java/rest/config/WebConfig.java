package rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("rest.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置 内容协商 视图解析器
     * <p>
     *     ContentNegotiatingViewResolver 是一个特殊的视图解析器，
     *     它考虑到了客户端所需要的内容类型，下面是对其进行简单的配置。
     *     这个 bean 声明背后会涉及很多事情，主要涉及内容协商的两个步骤：
     *     <li>1，确定请求的媒体类型（MIME）
     *     <li>2，找到适合请求媒体类型（MIME）的最佳视图
     * <p><p>
     *     1，确定请求的媒体类型（MIME）
     * <p>
     *     1.1<p>ContentNegotiatingViewResolver 将会考虑到 Accept 头部信息并使用它所请求的媒体类型，
     *     但是它会首先查看 URL 的文件扩展名，这是因为 Accept 头部信息并不总是可靠的。
     *     如果 URL 在结尾处有文件扩展名的话，ContentNegotiatingViewResolver 将会基于该扩展名确定所需的类型。
     *     如果扩展名是 .json 的话，那么所需的内容类型必须是 application/json;
     *     如果扩展名是 .xml 的话，那么所需的内容类型必须是 application/xml;
     *     如果扩展名是 .html 的话，那么客户端所需的资源表述为 HTML（text/html）。
     * <p>
     *     1.2<p>如果根据文件扩展名不能得到任何媒体类型的话，那就会考虑请求中的 Accept 头部信息。
     * <p>
     *     1.3<p>最后，如果没有 Accept 头部信息，并且扩展名也无法提供帮助的话，
     *     ContentNegotiatingViewResolver 将会使用 "/" 作为默认的内容类型，
     *     这就意味着客户端必须要接收服务器发送的任何形式的表述。
     * <p>
     *     一旦内容类型确定之后，ContentNegotiatingViewResolver 就该将逻辑视图名解析为渲染模型的视图。
     *     与 Spring 的其他视图解析器不同，它本身不会解析视图，而是委托给其他的视图解析器，让它们来解析视图。
     *     解析得到的每个视图都会放到一个列表中，ContentNegotiatingViewResolver 会遍历客户端请求的所有媒体类型，
     *     在候选的视图中查找能够产生内容类型的视图，第一个匹配的视图会用来渲染模型。
     */
    @Bean
    public ViewResolver viewResolver(){
        return new ContentNegotiatingViewResolver();
    }

    /**
     * 上述的选择过程阐述了确定所请求媒体类型的默认策略，但可以通过为其设置
     * ContentNegotiationManager 来改变 ContentNegotiatingViewResolver 的默认行为。
     * 比如：
     * <li>指定默认的内容类型，如果根据请求无法得到内容类型的话，将会使用默认值
     * <li>通过请求参数指定内容类型
     * <li>忽视请求的 Accept 头部信息
     * <li>将请求的扩展名映射为特定的媒体类型
     * <p>
     *     有三种配置 ContentNegotiationManager 的方法：
     *     <li>直接声明一个 ContentNegotiationManager 的 bean
     *     <li>通过 ContentNegotiationManagerFactoryBean 间接创建 bean
     *     <li>重载 WebMvcConfigurerAdapter 的 configureContentNegotiation 方法
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // ContentNegotiationConfigurer 中的一些方法对应于 ContentNegotiationManager 的 setter 方法
        // 可以通过它设置任意内容协商相关的属性，比如本例设置默认的内容类型为 json
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * 将 ContentNegotiationManager 注入到 ContentNegotiatingViewResolver 中，
     * 这样一来，ContentNegotiatingViewResolver 将会使用 ContentNegotiationManager 所定义的行为。
     */
    @Bean
    public ViewResolver cnViewResolver(ContentNegotiationManager cnm){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(cnm);
        return resolver;
    }
}

package myapp.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

/**
 * HomeController 测试类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
public class HomeControllerTest {

    /**
     * 将 HomeController 作为 POJO 进行测试，然而没有按控制器的方式进行测试。
     */
    @Test
    public void testHomePage(){
        HomeController homeController = new HomeController();
        assertEquals("home",homeController.home());
        System.out.println("testHomePage()");
    }

    /**
     * 从 Spring3.2 开始，可以按照控制器的方式来测试 SpringMVC 中的控制器，而不仅仅是作为 POJO 进行测试。
     * Spring 包含了一种 mock SpringMVC 并针对控制器执行 HTTP 请求的机制。
     * 这样的话，在测试控制器的时候，就没有必要再启动 Web 服务器和浏览器了。
     */
    @Test
    public void testHomePage2() throws Exception {
        HomeController homeController = new HomeController();
        // 搭建 MOckMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        ViewResultMatchers view = MockMvcResultMatchers.view();
        // 对 / 执行 GET 请求，并预期得到 home 视图
        mockMvc.perform(request).andExpect(view.name("home"));
        System.out.println("testHomePage2()");
    }
}

package myapp;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义 Filter 实现
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter doFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy...");
    }
}

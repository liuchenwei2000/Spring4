package myapp;

import javax.servlet.*;
import java.io.IOException;

/**
 * DispatcherServlet 专用 Filter 1
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class DispatcherServletFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DispatcherServletFilter1 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DispatcherServletFilter1 doFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("DispatcherServletFilter1 destroy...");
    }
}

package myapp;

import javax.servlet.*;
import java.io.IOException;

/**
 * DispatcherServlet 专用 Filter 2
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class DispatcherServletFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DispatcherServletFilter2 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DispatcherServletFilter2 doFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("DispatcherServletFilter2 destroy...");
    }
}

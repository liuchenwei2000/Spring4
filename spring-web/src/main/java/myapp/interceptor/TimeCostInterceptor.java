/**
 * 
 */
package myapp.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 计时拦截器
 * <p>
 *     为每一个请求添加计时功能，统计请求响应耗时。
 * <p>
 * Created by liuchenwei on 2016/11/24.
 */
public class TimeCostInterceptor extends HandlerInterceptorAdapter {

	private static final String START_TIME = "$$startTime$$";

	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute(START_TIME, System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	/**
	 * 后处理
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		long end = System.currentTimeMillis();
		long start = (long) request.getAttribute(START_TIME);
		long count = end - start;
		System.out.printf("【Request URL】 %s [%s] 【Total time】 %d ms", request.getRequestURI(), request.getMethod(), count);
	}
}

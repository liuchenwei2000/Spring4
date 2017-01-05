/**
 * 
 */
package myapp.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseUrl 拦截器
 * <p>
 *     为每一个请求添加 baseUrl 属性，以便 JSP 页面直接使用。
 *     如 http://10.24.11.232:8080/app/
 * <p>
 * Created by liuchenwei on 2016/11/24.
 */
public class BaseUrlInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getContextPath();
		// http://10.24.11.232:8080/app/
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
				request.getServerPort() + path + "/";
		request.setAttribute("baseUrl", basePath);
		return super.preHandle(request, response, handler);
	}
}

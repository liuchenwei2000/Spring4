/**
 * 
 */
package myapp;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 自定义 Listener 实现
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/7.
 */
public class MyRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("request initialized...");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("request destroyed...");
	}
}

package edu.itu.course.dropwizard.websoket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author  作者 E-mail:
 * @date 创建时间：Sep 16, 2015 8:35:51 PM
 * @version 1.0
 * @parameter 
 * @since
 * @return
 */
public class NotificationServelet extends WebSocketServlet implements WebSocketCreator {
	    /** 
	* @Fields serialVersionUID : TODO(describe the functions of this varaible) 
	*/ 
	private static final long serialVersionUID = 1L;
		@Override
	    public void configure(WebSocketServletFactory factory) {
	        factory.register(MyWebSocketEndpoint.class);
	        factory.setCreator(this);
	    }
		@Override
		public Object createWebSocket(ServletUpgradeRequest arg0,
				ServletUpgradeResponse arg1) {
			// TODO Auto-generated method stub
			
			System.out.println("public Object createWebSocket(ServletUpgradeRequest arg0,");
			System.out.println(arg0.getHeader("deviceId"));
			MyWebSocketEndpoint ep = new MyWebSocketEndpoint(arg0.getHeaderInt("deviceId"));
			return ep;
		}
		
	}
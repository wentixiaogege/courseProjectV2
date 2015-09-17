package edu.itu.course.dropwizard.websoket;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  作者 E-mail:
 * @date 创建时间：Sep 16, 2015 8:38:17 PM
 * @version 1.0
 * @parameter 
 * @since
 * @return
 */
@WebSocket
public class MyWebSocketEndpoint extends WebSocketAdapter {

	
	 	private static final Logger log = LoggerFactory.getLogger(BroadcastSocket.class);

//	    private static Set<Session> sessions = new CopyOnWriteArraySet<>();
	    
	    private static HashMap<Integer, Session> map = new HashMap<>();
	
	    private int deviceId;
        public MyWebSocketEndpoint(int i) {
        	this.deviceId =i;
        }
        @Override
        public void onWebSocketConnect(Session session) {
           super.onWebSocketConnect(session);
//           sessions.add(session);
           map.put(deviceId, session);
           log.info("Socket Connected: {}", Integer.toHexString(session.hashCode()));
        }

        @Override
        public void onWebSocketClose(int statusCode, String reason) {
//           sessions.remove(getSession());
           
           map.values().remove(getSession());
           
           super.onWebSocketClose(statusCode, reason);
           log.info("Socket Closed: [{}] {}", statusCode, reason);
        }

        @Override
        public void onWebSocketError(Throwable cause) {
           super.onWebSocketError(cause);
           log.error("Websocket error", cause);
        }

        @Override
        public void onWebSocketText(String message) {
           log.info("Got text {} from {}", message, Integer.toHexString(getSession().hashCode()));
        }
        public static void broadcast(String msg) {
        	
        	System.out.println("broadcasting message is:"+msg);
        	
        	map.values().forEach(session -> {
                try {
                  	System.out.println("message to send:"+msg);
                      session.getRemote().sendString(msg);
                   } catch (IOException e) {
                      log.error("Problem broadcasting message", e);
                   }
                });
        	
         }
		public static boolean notify(String message, int id) {
			// TODO Auto-generated method stub
			System.out.println("notify message to "+id+"is"+message);
			try {
				if (map.containsKey(id)) {
					map.get(id).getRemote().sendString(message);
					return true;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
}

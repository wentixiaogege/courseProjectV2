package edu.itu.course.dropwizard.sse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.itu.course.dropwizard.api.beans.Manipulation;
import edu.itu.course.dropwizard.api.beans.SseEventSource;

/**
 * @author 作者 E-mail:
 * @date 创建时间：Sep 14, 2015 11:22:29 PM
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class NotificationPublisher {

	private static final Logger LOG = LoggerFactory
			.getLogger(NotificationPublisher.class);
	private static List<SseEventSource> listeners = Collections
			.synchronizedList(new ArrayList<SseEventSource>());

	public static void broadcast(Manipulation msg) {
		  
	        synchronized(listeners) {
	            Iterator<SseEventSource> iterator = listeners.iterator();
	            while(iterator.hasNext()) {
	                SseEventSource sseEventSource = (SseEventSource)iterator.next();
	                try {
	                	LOG.info("broadcasting: " + msg +"listeners.size()"+listeners.size());
	                    sseEventSource.emitEvent(msg);
	                }
	                catch(IOException e) {
	                    LOG.error(e.getMessage());
	                }
	            }
	        }
	}
	public static void addListener(SseEventSource l) {
		listeners.add(l);
	}

	public static void removeListener(SseEventSource l) {
		listeners.remove(l);
	}

	public static void notify(String msg) {
		// TODO Auto-generated method stub
		System.out.println("need to implement here!for notify one specific client");
	}
}

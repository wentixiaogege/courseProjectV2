package edu.itu.course.dropwizard.sse;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlets.EventSource;
import org.eclipse.jetty.servlets.EventSourceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.itu.course.dropwizard.api.beans.SseEventSource;

@SuppressWarnings("serial")
public class SseEventSourceServlet extends EventSourceServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SseEventSourceServlet.class);

    @Override
    protected EventSource newEventSource(HttpServletRequest request) {
        LOG.info("SseEventSourceServlet--begin");
        SseEventSource l = new SseEventSource();
        NotificationPublisher.addListener(l);
        LOG.info("SseEventSourceServlet");
        return l;
    }
}

package edu.itu.course.dropwizard.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.itu.course.dropwizard.api.beans.Manipulation;
import edu.itu.course.dropwizard.api.beans.SseEventSource;
import edu.itu.course.dropwizard.sse.NotificationPublisher;
import edu.itu.course.dropwizard.websoket.BroadcastSocket;
import edu.itu.course.dropwizard.websoket.MyWebSocketEndpoint;

/**
 * @author 作者 E-mail:
 * @date 创建时间：Sep 14, 2015 11:15:18 PM
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Path("/notification")
public class Notification {

	private final ObjectMapper objectMapper;

	public Notification(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	@Path("/broadcast")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response broadcast(Manipulation msg) {

		// NotificationPublisher.broadcast(msg);
		System.out.println(msg
				+ "=============================================");
		try {
//			BroadcastSocket.broadcast(objectMapper.writeValueAsString(msg));
			MyWebSocketEndpoint.broadcast(objectMapper.writeValueAsString(msg));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("success").build();
	}
	@POST
	@Path("/{deviceId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response notify(@PathParam("deviceId") final int deviceId, Manipulation message) {

		try {
			if(MyWebSocketEndpoint.notify(objectMapper.writeValueAsString(message), deviceId)){
				return Response.ok("success").build();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok("failed").build();
	}

}

package edu.itu.course.dropwizard.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

/**
 * @author  作者 E-mail:
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
	@Path("/{id}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response notify(String message) {
		
		NotificationPublisher.notify(message);
        return Response.ok().build();
    }
	@Path("/broadcast")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response broadcast(Manipulation msg) {
		
//		NotificationPublisher.broadcast(msg);
		System.out.println(msg+"=============================================");
		try {
			BroadcastSocket.broadcast(objectMapper.writeValueAsString(msg));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok("success").build();
    }
	@GET
	public String hello(){
        return "Hello World";
	}
	
}

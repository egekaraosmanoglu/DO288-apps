package com.redhat.training.openshift.hello;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.metrics.*;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("/")
public class HelloResource {


	@Inject
	@Metric(name = "requestCount", description = "All JAX-RS request made to the SessionResource",displayName = "SessionResource#requestCount")
	private Counter requestCount;


    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
		
		
		requestCount.inc();
		
        String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");
	      String message = System.getenv().getOrDefault("APP_MSG", null);
	      String response = "";

      	if (message == null) {	
      	  response = "Hello world from host "+hostname+"\n";
      	} else {
      	  response = "Hello world from host ["+hostname+"].\n";
      	  response += "Message received = "+message+"\n";
        }
        return response;
    }
}

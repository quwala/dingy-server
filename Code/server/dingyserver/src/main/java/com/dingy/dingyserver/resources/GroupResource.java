package com.dingy.dingyserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/groups")
public class GroupResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGroups(){
		return "Hellow babies of nice!";
	}

}

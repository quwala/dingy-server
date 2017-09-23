package com.dingy.dingyserver.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dingy.dingyserver.datamodels.DingyUser;


@Path("/groups")
public class GroupResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DingyUser getGroups(){	
		return new DingyUser("matok");
		
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DingyUser createGroup(DingyUser user){
		/*for(String name : userNames){
			System.out.println(name);
		}*/
		return user;
	}
	
}

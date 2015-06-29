package com.spanbee.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author sucheth.s
 *
 */


@Path("login")
public class AuthenticationController {
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/authenticate")
  public String authenticate(String request){
    
    
    System.out.println("*********Inside the authenticate method*********");
    return "{\"name\":\"value\"}";
  }

}


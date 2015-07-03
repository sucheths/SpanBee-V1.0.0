package com.spanbee.controller;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spanbee.service.EmailValidationService;
import com.spanbee.utils.PropertyReader;


/**
 * @author sucheth.s
 *
 */

@Path("email")
public class EmailValidationController {
  private static final Logger LOGGER = Logger.getLogger(EmailValidationController.class);
  
  @Autowired
  private EmailValidationService emailValidationService;
  
  public void setEmailValidationService(EmailValidationService emailValidationService) {
    this.emailValidationService = emailValidationService;
  }

  @GET
  @Path("/validate")
  @Produces(MediaType.TEXT_HTML)
  public String validate(@QueryParam("unniqueid") String unniqueid,@Context UriInfo uriInfo,String request){
    MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters(); 
    String uid= queryParams.getFirst("uniqueid");
    LOGGER.info("*********Inside the EmailValidationController::: validate method ********");
    LOGGER.info("unniqueid:::"+unniqueid +"::uid::"+uid +"::uriInfo::"+uriInfo +"::request::" +request);
    
    if(uid!=null){
      emailValidationService.validateEmail(uid);
    }
    
    String response=null;
    try {
      response = PropertyReader.resourceBundlesManager.getValueFromResourceBundle("en", "VERIFICATION_EMAIL_TEMPLATE");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return response;
  }
  
}


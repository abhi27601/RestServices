package org.rahul.webdev.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	@GET
	@Path("/annotations")
	public String getParams(@MatrixParam("matrixParam") String matrixParam,
							@HeaderParam("headerParam") String headerParam,
							@CookieParam("cookieParam") String cookieParam) {
		return "Matrix Param: "+matrixParam+" Header Param: "+headerParam+" Cookie Param: "+cookieParam;
	}
	
	@GET
	@Path("/context")
	public String getContext(@Context UriInfo uriInfo) {
		String path = uriInfo.getAbsolutePath().toString();
		return path;
	}
}

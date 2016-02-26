package br.com.semeru.rest.interfaces;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/report")
@RequestScoped
public interface IReportRESTService {
	
	@GET
    @Produces("application/pdf")
    public Response listAllMembers() throws Exception;
}
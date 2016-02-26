package br.com.semeru.rest.implementations;

import java.io.File;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.semeru.rest.interfaces.IReportRESTService;
import br.com.semeru.service.ReportService;

public class ReportRESTService implements IReportRESTService{
    
	@Inject
    ReportService report;

	@Override
    public Response listAllMembers() throws Exception {
    	File file = report.generatePDF();
    	ResponseBuilder response = Response.ok((Object) file);
    	response.header("Content-Disposition", "attachment; filename=output.pdf");
    	return response.build();
    }
}

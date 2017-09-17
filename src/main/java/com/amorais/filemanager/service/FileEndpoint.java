package com.amorais.filemanager.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.amorais.filemanager.business.FileBusiness;
import com.amorais.filemanager.domain.File;
import com.amorais.filemanager.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/file")
@Api(value = "file")
public class FileEndpoint {
	
	@Autowired
	private FileBusiness fileBusiness; 
	
	@GET
	@ApiOperation(value = "Realiza a listagem dos arquivos carregados")
	@Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response get() {
		List<File> list = fileBusiness.findAll();
		
		String json = JsonUtil.serialize(list);
		return Response.status(Status.OK.getStatusCode()).entity(json).build();
	}

}

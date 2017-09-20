package com.amorais.filemanager.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.amorais.filemanager.business.FileBusiness;
import com.amorais.filemanager.domain.File;
import com.amorais.filemanager.repository.FileRepository;
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
	
	@Autowired
	private FileRepository fileRepository; 
	
	@GET
	@ApiOperation(value = "Realiza a listagem dos arquivos carregados")
	@Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response get() {
		Iterable<File> list = fileRepository.findAll();
		
		String json = JsonUtil.serialize(list);
		return Response.status(Status.OK.getStatusCode()).entity(json).build();
	}
	
	@GET 
	@Path("/upload")
	@ApiOperation(value = "Realizar o upload de arquivos")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public void upload() {

		fileBusiness.upload("C:\\Users\\Aline\\Desktop\\teste_arquivo.txt", "user_teste");
		
	}
	
	@GET
	@Path("/download")
	@ApiOperation(value = "Realizar o download de arquivos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public void download() {
		
		//fileBusiness.download("C:\\Users\\Aline\\Desktop\\teste_arquivo_copy.txt");
		fileBusiness.download(new Long(2));
		
	}

}

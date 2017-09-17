package com.amorais.filemanager.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.filemanager.domain.File;
import com.amorais.filemanager.repository.FileRepository;

@Component
public class FileBusiness {

	@Autowired
	private FileRepository fileBusiness; 
	
	public List<File> findAll() {
		List<File> files = new ArrayList<File>();
		for(File file : fileBusiness.findAll()){
			files.add(file);
		}
		return files;
	}

}
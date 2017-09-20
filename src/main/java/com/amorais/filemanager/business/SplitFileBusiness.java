package com.amorais.filemanager.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.filemanager.domain.SplitFile;
import com.amorais.filemanager.repository.SplitFileRepository;

@Component
public class SplitFileBusiness {

	@Autowired
	private SplitFileRepository splitFileRepository;
	
	
	public void save(Integer chunk, byte[] bytes, Long fileId) {
		
		SplitFile splitFile = new SplitFile();
        //TODO: splitFile.setName(filename + "." + subfile);
		splitFile.setChunkNumber(chunk);
		splitFile.setSplitFile(bytes);
		splitFile.setFileId(fileId);
		
		splitFileRepository.save(splitFile);
	}

}
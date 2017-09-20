package com.amorais.filemanager.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.filemanager.domain.SplitFile;
import com.amorais.filemanager.repository.SplitFileRepository;

@Component
public class SplitFileBusiness {

	@Autowired
	private SplitFileRepository splitFileRepository;
	
	
	public void save(Integer chunkNumber, byte[] splitFile, long fileId) {
		
		SplitFile sf = new SplitFile();
        //TODO: splitFile.setName(filename + "." + subfile);
		sf.setChunkNumber(chunkNumber);
		sf.setSplitFile(splitFile);
		sf.setFileId(fileId);
		
		splitFileRepository.save(sf);
	}

}
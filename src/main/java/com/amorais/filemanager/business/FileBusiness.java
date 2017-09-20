package com.amorais.filemanager.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amorais.filemanager.domain.File;
import com.amorais.filemanager.domain.SplitFile;
import com.amorais.filemanager.repository.FileRepository;
import com.amorais.filemanager.repository.SplitFileRepository;
import com.amorais.filemanager.util.FileManagerConstants;

@Component
public class FileBusiness {

	@Autowired
	private SplitFileBusiness splitFileBusiness; 
	
	@Autowired
	private FileRepository fileRepository; 

	@Autowired
	private SplitFileRepository splitFileRepository;
	
	
	public void upload(String filename, String user) {

		File file = null;
		try{
			// open the file
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
			
			java.io.File f = new java.io.File(filename);
			long fileSize = f.length();
					
			Long chunkCount = Math.round(new Double(fileSize) / new Double(FileManagerConstants.CHUNK_SIZE));
 
			file = save(filename, 
						chunkCount.intValue(), 
						FileManagerConstants.STATUS_UPLOAD.PROGRESS.toString(), 
						user);
			
			
			int chunkNumber;
			for(chunkNumber = 0; chunkNumber < fileSize / FileManagerConstants.CHUNK_SIZE; chunkNumber++) {
	
				byte[] splitFile = new byte[FileManagerConstants.CHUNK_SIZE];
				
				// loads byte to byte
				for (int currentByte = 0; currentByte < FileManagerConstants.CHUNK_SIZE; currentByte++){
					splitFile[currentByte] = (byte) in.read();
				}
				
				splitFileBusiness.save(chunkNumber, splitFile, file.getId());
			}

			if (fileSize != FileManagerConstants.CHUNK_SIZE * (chunkNumber - 1)){
				
				byte[] splitFile = new byte[FileManagerConstants.CHUNK_SIZE];
				
				// loads byte to byte
				for(int currentByte = 0; currentByte < FileManagerConstants.CHUNK_SIZE; currentByte++){
					splitFile[currentByte] = (byte) in.read();
				}
				
				splitFileBusiness.save(chunkNumber, splitFile, file.getId());
				
				// TODO: 
				//byte[] bytes2 = new byte[FileManagerConstants.CHUNK_SIZE];
				
				/*int b;
				int currentByte = 0;
				while ((b = in.read()) != -1) {
					//out.write(b);
					bytes2[currentByte] = (byte) b;
					currentByte++;
				}*/
				
/*				// Escreve o resto do arquivo
				int b;
				while ((b = in.read()) != -1)
					out.write(b);*/
			}
			
			// close the file
			in.close();
						
			statusUpdate(file.getId(), FileManagerConstants.STATUS_UPLOAD.COMPLETED.toString());

		} catch(FileNotFoundException e){
			statusUpdate(file.getId(), FileManagerConstants.STATUS_UPLOAD.FAILURE.toString());
			
		} catch (IOException e) {
			statusUpdate(file.getId(), FileManagerConstants.STATUS_UPLOAD.FAILURE.toString());
		}
	}
	
	public void download(long fileId) {
		
		try {
			List<SplitFile> files = splitFileRepository.findByFileId(fileId);
			
			byte[] file = null;
			for(SplitFile f : files) {
				file = f.getSplitFile();
			}

			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Aline\\Desktop\\teste_arquivo_copy.txt"));
			out.write(file);
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void statusUpdate(long fileId, String status) {

		File file = fileRepository.findOne(fileId);
		file.setStatus(status);
		fileRepository.save(file);
	}
			 
	public File save(String filename, Integer chunkCount, String status, String user) {
		
		File file = new File();
		file.setDateUpload(new Date());
		file.setName(filename);
		file.setChunkCount(chunkCount);
		file.setStatus(status);
		file.setUsername(user);
		fileRepository.save(file);
		
		return file;
	}
	
}
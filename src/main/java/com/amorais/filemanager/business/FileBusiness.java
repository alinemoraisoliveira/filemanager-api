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
			long sizeFile = f.length();
			
			Long chunksNumber = sizeFile / FileManagerConstants.CHUNK_SIZE;
 
			file = save(filename, 
						chunksNumber.intValue(), 
						FileManagerConstants.STATUS_UPLOAD.PROGRESS.toString(), 
						user);
			
			
			int chunk;
			for(chunk = 0; chunk < chunksNumber.intValue(); chunk++) {
	
				byte[] bytes = new byte[FileManagerConstants.CHUNK_SIZE];
				
				// TODO: Escreve a quantidade certa de bytes
				for (int currentByte = 0; currentByte < FileManagerConstants.CHUNK_SIZE; currentByte++){
					// TODO: Carrega um byte do arquivo de entrada e escrevê-lo para o arquivo de saída
					bytes[currentByte] = (byte) in.read();
				}
				
				splitFileBusiness.save(chunk, bytes, file.getId());
			}

			if (sizeFile != FileManagerConstants.CHUNK_SIZE * (chunk - 1)){
				
				byte[] bytes = new byte[FileManagerConstants.CHUNK_SIZE];
				
				// TODO: Escreve a quantidade certa de bytes
				for (int currentByte = 0; currentByte < FileManagerConstants.CHUNK_SIZE; currentByte++){
					// TODO: Carrega um byte do arquivo de entrada e escrevê-lo para o arquivo de saída
					bytes[currentByte] = (byte) in.read();
				}
				
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

				splitFileBusiness.save(chunk, bytes, file.getId());
			}
			
			// close the file
			in.close();
			
			file = fileRepository.findOne(file.getId());
			file.setStatus(FileManagerConstants.STATUS_UPLOAD.COMPLETED.toString());
			fileRepository.save(file);

		} catch(FileNotFoundException e){
			statusUpdate(file.getId(), FileManagerConstants.STATUS_UPLOAD.FAILURE.toString());
			
		} catch (IOException e) {
			statusUpdate(file.getId(), FileManagerConstants.STATUS_UPLOAD.FAILURE.toString());
		}
	}
	
	public void download(Long fileId) {
		
		try {
			List<SplitFile> files = splitFileRepository.findByFileId(fileId);
			
			byte [] bytes = null;
			for(SplitFile file : files) {
				bytes = file.getSplitFile();
			}

			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Aline\\Desktop\\teste_arquivo_copy.txt"));
			out.write(bytes);
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void statusUpdate(Long fileId, String status) {

		File file = fileRepository.findOne(fileId);
		file.setStatus(status);
		fileRepository.save(file);
	}
			 
	public File save(String filename, Integer chunksNumber, String status, String user) {
		
		File file = new File();
		file.setDateUpload(new Date());
		file.setName(filename);
		file.setChunksNumber(chunksNumber);
		file.setStatus(status);
		file.setUsername(user);
		fileRepository.save(file);
		
		return file;
	}
	
}
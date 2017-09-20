package com.amorais.filemanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "SPLIT_FILE")
public class SplitFile implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CHUNK_NUMBER", nullable = true)
	private int chunkNumber;

	@Column(name = "ARQUIVO", nullable = true)
	@Lob
	private byte[] splitFile;
	

/*	@Column( name = "FILEIMAGE" )
	@Lob(type = LobType.BLOB)
	private byte[] fileimage;*/

	@Column(name = "FILE_ID", nullable = true)
	private Long fileId;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="FILE_ID", referencedColumnName="ID", nullable = false, updatable = false, insertable = false)
	private File file;
	
	public SplitFile() {
		super();
	}

	public SplitFile(Long id, int chunkNumber, byte[] splitFile, Long fileId, File file) {
		super();
		this.id = id;
		this.chunkNumber = chunkNumber;
		this.splitFile = splitFile;
		this.fileId = fileId;
		this.file = file;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getChunkNumber() {
		return chunkNumber;
	}
	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}
	public byte[] getSplitFile() {
		return splitFile;
	}
	public void setSplitFile(byte[] splitFile) {
		this.splitFile = splitFile;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}

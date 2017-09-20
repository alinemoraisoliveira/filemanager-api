package com.amorais.filemanager.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.amorais.filemanager.util.FileManagerConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "FILE")
public class File implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME", nullable = true)
	private String name;
	
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	@Column(name = "STATUS", nullable = true)
	private String status;

	@JsonFormat(pattern = FileManagerConstants.DATETIME_PATTERN, locale = FileManagerConstants.JSON_DEFAULT_LOCALE, timezone = FileManagerConstants.JSON_DEFAULT_TIMEZONE)
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_UPLOAD", nullable = true)
	private Date dateUpload;
	
	@Column(name = "CHUNK_COUNT", nullable = true)
	private Integer chunkCount;
	
	
	public File() {
		super();
	}

	public File(long id, String name, String username, String status, Date dateUpload, Integer chunkCount) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.status = status;
		this.dateUpload = dateUpload;
		this.chunkCount = chunkCount;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	public Integer getChunkCount() {
		return chunkCount;
	}
	public void setChunkCount(Integer chunkCount) {
		this.chunkCount = chunkCount;
	}
	
}

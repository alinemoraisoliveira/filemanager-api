package com.amorais.filemanager.domain;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILE")
public class File implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = true)
	private String name;
	
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	@Column(name = "STATUS", nullable = true)
	private String status;
	
	@Column(name = "SEND_TIME", nullable = true)
	private Long sendTime;
	
	@Column(name = "CHUNKS_NUMBER", nullable = true)
	private long chunksNumber;
	
	@Column(name = "DATE_UPLOAD", nullable = true)
	private GregorianCalendar dateUpload;

	
	public File() {
		super();
	}
	
	public File(Long id, String name, String username, String status, Long sendTime, long chunksNumber,
			GregorianCalendar dateUpload) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.status = status;
		this.sendTime = sendTime;
		this.chunksNumber = chunksNumber;
		this.dateUpload = dateUpload;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Long getSendTime() {
		return sendTime;
	}
	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}
	public long getChunksNumber() {
		return chunksNumber;
	}
	public void setChunksNumber(long chunksNumber) {
		this.chunksNumber = chunksNumber;
	}
	public GregorianCalendar getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(GregorianCalendar dateUpload) {
		this.dateUpload = dateUpload;
	}

}

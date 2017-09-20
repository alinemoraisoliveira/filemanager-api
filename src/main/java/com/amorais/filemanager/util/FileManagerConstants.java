package com.amorais.filemanager.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileManagerConstants {

	public static Integer CHUNK_SIZE = 1048576;
	
	public static final String JSON_DEFAULT_LOCALE = "pt-BR";
	public static final String JSON_DEFAULT_TIMEZONE = "Brazil/East";
	
	public static final String DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	
	//public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat(DATETIME_PATTERN);
	
	public enum STATUS_UPLOAD {
		PROGRESS,
		FAILURE,
		COMPLETED
	}
	
}

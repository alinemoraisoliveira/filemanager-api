package com.amorais.filemanager.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonFormat;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class JsonUtil {

	//private static final Logger LOGGER = Logger.getLogger(JsonUtil.class.getName());
	
	public static String serialize(Object obj) {
		return serialize(obj, null);
	}

	public static String serialize(Object obj, String[] includes) {
		
		JSONSerializer serializer = new JSONSerializer();
		Class<?> classRef = obj != null ? getClassRef(obj) : null;
		
		if(classRef != null) {
			Field[] fieldsToInclude = getFieldsToInclude(classRef);
			
			includeFields(fieldsToInclude, null, serializer);
			
			if(includes != null) {
				for(String include : includes) {
					try {
						Class<?> classRefInclude = getClassRef(classRef, include);
						Field[] newFieldsToInclude = getFieldsToInclude(classRefInclude);
						includeFields(newFieldsToInclude, include, serializer);
					} catch (Exception ex) {
						//LOGGER.info("Field to include not exists in the object", ex);
					}
				}
			}

			serializer.exclude("*");
		}

		return serializer.serialize(obj);
	}
	/*
	public static String serializeAll(Object obj) {
		
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		
		return serializer.serialize(obj);
	}*/

	private static Class<?> getClassRef(Object obj) {
		
		Object objRef = null;
		if (obj instanceof Collection) {
			Collection<?> list = (Collection<?>) obj;
			if (!list.isEmpty()) {
				objRef = list.iterator().next();
			}
		} else {
			objRef = obj;
		}
		
		return objRef != null ? objRef.getClass() : null;
	}

	@SuppressWarnings("rawtypes")
	private static Class<?> getClassRef(Class<?> clazz, String include) throws SecurityException, NoSuchFieldException {
		
		Class<?> classRef = null;
		
		String fieldNames[] = include.split("\\.");
		for(String fieldName : fieldNames) {
			Field field = classRef != null ? classRef.getDeclaredField(fieldName) : clazz.getDeclaredField(fieldName);
			
			if(field.getGenericType() instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
				classRef = (Class) parameterizedType.getActualTypeArguments()[0];
			} else {
				classRef = field.getType();
			}
		}
		
		return classRef;
	}
	
	private static Field[] getFieldsToInclude(Class<?> classRef) {
		
		Field[] fieldsToInclude = classRef.getDeclaredFields();
		
		return fieldsToInclude;
	}
	
	private static void includeFields(Field[] fieldsToInclude, String prefix, JSONSerializer serializer) {
		if (fieldsToInclude != null) {
			for (Field field : fieldsToInclude) {
				if (field.getType().isPrimitive()
					|| (field.getType().getPackage() != null
						&& (field.getType().getPackage().getName().startsWith("java.lang")
								|| field.getType().getPackage().getName().startsWith("java.math")
								|| field.getType().getPackage().getName().startsWith("java.sql")
								|| field.getType().getName().equals("java.util.Date")
							)
						)
				) {
	
					String fieldName = (prefix != null ? prefix + "." : "") + field.getName();
					
					serializer.include(fieldName);
					
					if(field.getType().getName().equals("java.util.Date")) {
						JsonFormat jsonFormat = field.getAnnotation(JsonFormat.class);
						if(jsonFormat != null && jsonFormat.pattern() != null) {
							DateTransformer dateTransformer = new DateTransformer(jsonFormat.pattern());
							serializer.transform(dateTransformer, fieldName);
						}
					}
				}
			}
		}
	}
}

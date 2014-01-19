package org.azizkhani.common.utility.dozer;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


public class CustomMappingJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	public CustomMappingJacksonHttpMessageConverter(){
		super();
		System.out.println("canWrite");
	}
	
	@Override
	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		System.out.println(contextClass.getName());
		return super.canRead(type, contextClass, mediaType);
	}
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		System.out.println(clazz.getName());
		return super.canWrite(clazz, mediaType);
	}
	@Override
	protected boolean canRead(MediaType mediaType) {
		System.out.println("canRead");
		return super.canRead(mediaType);
	}
	@Override
	protected boolean canWrite(MediaType mediaType) {
		System.out.println("canWrite");
		return super.canWrite(mediaType);
	}
	@Override
	public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		System.out.println(contextClass.getName());
		return super.read(type, contextClass, inputMessage);
	}
	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		System.out.println(clazz.getName());
		return super.readInternal(clazz, inputMessage);
	}
}

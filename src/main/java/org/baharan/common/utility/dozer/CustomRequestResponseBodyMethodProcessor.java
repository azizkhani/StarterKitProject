package org.baharan.common.utility.dozer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.baharan.common.utility.dozer.annotation.CustomMapping;
import org.baharan.common.utility.dozer.annotation.CustomRequestBody;
import org.baharan.common.utility.dozer.annotation.CustomResponseBody;
import org.baharan.common.viewmodel.BaseInformationViewModel;
import org.baharan.model.baseInfo.BaseInformation;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

public class CustomRequestResponseBodyMethodProcessor extends AbstractMessageConverterMethodProcessor {

	public CustomRequestResponseBodyMethodProcessor(){
		super(null);
	}
	
	public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}

	public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters,ContentNegotiationManager contentNegotiationManager) {

		super(messageConverters, contentNegotiationManager);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CustomRequestBody.class);
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return ((AnnotationUtils.findAnnotation(returnType.getContainingClass(), CustomResponseBody.class) != null) ||
				(returnType.getMethodAnnotation(CustomResponseBody.class) != null));
	}

	/**
	 * {@inheritDoc}
	 * @throws MethodArgumentNotValidException if validation fails
	 * @throws HttpMessageNotReadableException if {@link RequestBody#required()}
	 * is {@code true} and there is no body content or if there is no suitable
	 * converter to read the content with.
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		Object argument = readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());

		String name = Conventions.getVariableNameForParameter(parameter);
		WebDataBinder binder = binderFactory.createBinder(webRequest, argument, name);

		if (argument != null) {
			validate(binder, parameter);
		}

		mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());

		return argument;
	}

	private void validate(WebDataBinder binder, MethodParameter parameter) throws Exception, MethodArgumentNotValidException {

		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation annot : annotations) {
			if (annot.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = AnnotationUtils.getValue(annot);
				binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
				BindingResult bindingResult = binder.getBindingResult();
				if (bindingResult.hasErrors()) {
					if (isBindExceptionRequired(binder, parameter)) {
						throw new MethodArgumentNotValidException(parameter, bindingResult);
					}
				}
				break;
			}
		}
	}

	/**
	 * Whether to raise a {@link MethodArgumentNotValidException} on validation errors.
	 * @param binder the data binder used to perform data binding
	 * @param parameter the method argument
	 * @return {@code true} if the next method argument is not of type {@link Errors}.
	 */
	private boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
		int i = parameter.getParameterIndex();
		Class<?>[] paramTypes = parameter.getMethod().getParameterTypes();
		boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));

		return !hasBindingResult;
	}

	@Override
	protected <T> Object readWithMessageConverters(NativeWebRequest webRequest,
			MethodParameter methodParam,  Type paramType) throws IOException, HttpMediaTypeNotSupportedException {

		final HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpInputMessage inputMessage = new ServletServerHttpRequest(servletRequest);

		CustomRequestBody annot = methodParam.getParameterAnnotation(CustomRequestBody.class);
		if (!annot.required()) {
			InputStream inputStream = inputMessage.getBody();
			if (inputStream == null) {
				return null;
			}
			else if (inputStream.markSupported()) {
				inputStream.mark(1);
				if (inputStream.read() == -1) {
					return null;
				}
				inputStream.reset();
			}
			else {
				final PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
				int b = pushbackInputStream.read();
				if (b == -1) {
					return null;
				}
				else {
					pushbackInputStream.unread(b);
				}
				inputMessage = new ServletServerHttpRequest(servletRequest) {
					@Override
					public InputStream getBody() throws IOException {
						// Form POST should not get here
						return pushbackInputStream;
					}
				};
			}
		}
		

		CustomMapping map = methodParam.getParameterAnnotation(CustomMapping.class);
		if (map!=null && map.source()!=null && map.destination()!=null) {
			Object readWithMessageConverters =readWithMessageConverters = super.readWithMessageConverters(inputMessage, methodParam, getType(map.source(),0));
			BaseInformationViewModel bv=(BaseInformationViewModel)readWithMessageConverters;
			BaseInformation b=new BaseInformation();
			b.setId(bv.getId());
			b.setCode(bv.getCode());
			b.setParent(new BaseInformation(bv.getParentId()));
			b.setTopic(bv.getTopic());
			return bv;
			
		}

		return super.readWithMessageConverters(inputMessage, methodParam, paramType);
	}
	static public Class<?> getType(final Class<?> klass, final int pos) {
	    // obtain anonymous, if any, class for 'this' instance
	    final Type superclass = klass.getGenericSuperclass();

	    // test if an anonymous class was employed during the call
	    if ( !(superclass instanceof ParameterizedType) ) {
	            throw new RuntimeException("This instance should belong to an anonymous class");
	    }

	    // obtain RTTI of all generic parameters
	    final Type[] types = ((ParameterizedType) superclass).getActualTypeArguments();

	    // test if enough generic parameters were passed
	    if ( pos >= types.length ) {
	            throw new RuntimeException(String.format("Could not find generic parameter #%d because only %d parameters were passed", pos, types.length));
	    }

	    if (!(types[pos] instanceof Class<?>)) {
	            throw new RuntimeException("Generic type is not a class but declaration definition(all you get is \"[T]\") " + types[pos]);
	    }
	    // return the type descriptor of the requested generic parameter
	    return (Class<?>) types[pos];
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
			throws IOException, HttpMediaTypeNotAcceptableException {

		mavContainer.setRequestHandled(true);
		if (returnValue != null) {
			writeWithMessageConverters(returnValue, returnType, webRequest);
		}
	}

}

package org.azizkhani.common.utility.dozer;

import java.util.List;

import org.azizkhani.common.utility.dozer.annotation.RequestView;
import org.azizkhani.common.viewmodel.BaseEntityViewModel;
import org.azizkhani.model.BaseEntity;
import org.dozer.Mapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class CustomRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

	private final Mapper mapper;

	public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters,
			Mapper mapper) {
		super(messageConverters);
		this.mapper = mapper;
		System.out.println("const");
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		System.out.println("res");
		Class<? extends BaseEntityViewModel> viewClass = getDeclaredViewClass(parameter);

		Object retValue = null;

		if (viewClass != null) {
			retValue = readWithMessageConverters(webRequest, parameter, viewClass);
			return wrapResult(retValue, (Class<? extends BaseEntity>) parameter.getParameterType());
		} else {
			return super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		}
	}

	private Class<? extends BaseEntityViewModel> getDeclaredViewClass(MethodParameter returnType) {
		RequestView annotation = returnType.getMethodAnnotation(RequestView.class);
		if (annotation != null) {
			return annotation.value();
		} else {
			return null;
		}
	}

	private Object wrapResult(Object result, Class<? extends BaseEntity> domainClass) {
		return mapper.map(result, domainClass);
	}

}
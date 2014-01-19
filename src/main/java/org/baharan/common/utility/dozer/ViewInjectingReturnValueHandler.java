package org.baharan.common.utility.dozer;

import org.baharan.common.utility.dozer.annotation.ResponseView;
import org.baharan.common.viewmodel.BaseEntityViewModel;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Decorator that detects a declared {@link ResponseView}, and injects support
 * if required
 * 
 * @author martypitt
 * 
 */
public class ViewInjectingReturnValueHandler implements HandlerMethodReturnValueHandler {

	private final Mapper mapper;

	private final HandlerMethodReturnValueHandler delegate;

	public ViewInjectingReturnValueHandler(HandlerMethodReturnValueHandler delegate,Mapper mapper) {
		this.delegate = delegate;
		this.mapper=mapper;
	}

	public boolean supportsReturnType(MethodParameter returnType) {
		return delegate.supportsReturnType(returnType);
	}

	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
			throws Exception {

		Class<? extends BaseEntityViewModel> viewClass = getDeclaredViewClass(returnType);
		if (viewClass != null) {
			returnValue = wrapResult(returnValue, viewClass);
		}

		delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
	}

	/**
	 * Returns the view class declared on the method, if it exists. Otherwise,
	 * returns null.
	 * 
	 * @param returnType
	 * @return
	 */
	private Class<? extends BaseEntityViewModel> getDeclaredViewClass(MethodParameter returnType) {
		ResponseView annotation = returnType.getMethodAnnotation(ResponseView.class);
		if (annotation != null) {
			return annotation.value();
		} else {
			return null;
		}
	}

	private Object wrapResult(Object result, Class<? extends BaseEntityViewModel> viewClass) {
		//Mapper mapper = new DozerBeanMapper();
		return  mapper.map(result, viewClass);
	}
}
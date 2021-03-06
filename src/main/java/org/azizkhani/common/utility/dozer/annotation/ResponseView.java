package org.azizkhani.common.utility.dozer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.azizkhani.common.viewmodel.BaseEntityViewModel;

@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseView {
	public Class<? extends BaseEntityViewModel> value();
}

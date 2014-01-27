package org.azizkhani.common.utility.dozer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.azizkhani.common.viewmodel.BaseEntityViewModel;
import org.azizkhani.model.BaseEntity;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestView {
	public Class<? extends BaseEntityViewModel> value();
}

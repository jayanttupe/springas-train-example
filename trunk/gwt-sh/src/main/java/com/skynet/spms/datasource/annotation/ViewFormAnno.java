package com.skynet.spms.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewFormAnno {
   String value();
   
   Class<? extends BaseEntity> entityCls() default BaseEntity.class; 
}

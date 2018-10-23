package org.eop.sb.operlog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eop.sb.operlog.enums.Entity;
import org.eop.sb.operlog.enums.Operate;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

	Entity entity();
	
	Operate operate();
	
	Class<?> serviceClass() default Object.class;
	
	String methodName() default "";
	
	String idField();
	
	String[] titleFields();
}

package com.cif.accumulate.config;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author: liuxincai
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String msg() default "this is myAnnotation"; //default 默认值
}

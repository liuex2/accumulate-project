package com.cif.accumulate.config.annotation;

/**
 * 自定义注解使用接口案例
 *
 * @author: liuxincai
 */
@MyAnnotation
public interface Message {

    @MyAnnotation
    void msg();
}

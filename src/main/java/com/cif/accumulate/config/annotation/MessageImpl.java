package com.cif.accumulate.config.annotation;

/**
 * @Author: liuxincai
 * @Description: 自定义注解实现类
 * @Date: 2019/9/11 15:13
 */
@MyAnnotation
public class MessageImpl implements Message {

    @Override
    @MyAnnotation(msg = "这是我的自定义注解！")
    public void msg() {

    }
}

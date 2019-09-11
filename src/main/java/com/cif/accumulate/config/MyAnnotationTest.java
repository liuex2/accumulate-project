package com.cif.accumulate.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author: liuxincai
 * @Description: 自定义注解测试案例
 * @Date: 2019/9/11 15:16
 */
public class MyAnnotationTest {

    Annotation[] annotations = null;

    public static void main(String[] args) throws ClassNotFoundException {
        new MyAnnotationTest().getAnnotation();
    }

    private void getAnnotation() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.cif.accumulate.config.MessageImpl");
        //判断aClass是否使用了MyAnnotaion注解
        boolean presentAnnotation = aClass.isAnnotationPresent(com.cif.accumulate.config.MyAnnotation.class);
        if(presentAnnotation){
            annotations = aClass.getAnnotations();
            out(annotations,null);
        }

        //查看使用注解的方法
        Method[] methods = aClass.getMethods();
        System.out.println("method");
        for(Method m : methods){
            //判断aClass是否使用了MyAnnotaion注解
            boolean presentAnnotation2 = aClass.isAnnotationPresent(com.cif.accumulate.config.MyAnnotation.class);
            if(presentAnnotation2){
                annotations = m.getAnnotations();
                out(annotations,m);
            }
        }
    }

    /**
     * 输出
     * @param annotations
     * @param method
     */
    private void out(Annotation[] annotations, Method method){
        for(Annotation a : annotations){
            MyAnnotation myAnnotation = (MyAnnotation) a;
            if(method==null){
                System.out.println(a+"--"+myAnnotation.msg());
            }else {
                System.out.println(method+"--"+myAnnotation.msg());
            }
        }
    }
}

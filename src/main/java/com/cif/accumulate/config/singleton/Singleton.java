package com.cif.accumulate.config.singleton;

/**
 * @Author: liuxincai
 * @Description: 单例模式下的线程案例
 * @Date: 2019/9/11 16:55
 */
public class Singleton {

    /**
     * 私有构造方法防止被实例化
     */
    private Singleton(){}

    /**
     * 使用一个内部类来维护单例
     */
    private static class SingletonFactory{
        private static Singleton instance = new Singleton();
    }

    /**
     * 获取实例
     * @return
     */
    private static Singleton getSingleton(){
        return SingletonFactory.instance;
    }

    /**
     * 如果该对象被用于实例化，可以保证对象在序列化后保持一致
     * @return
     */
    public Object readResolve(){
        return getSingleton();
    }
}

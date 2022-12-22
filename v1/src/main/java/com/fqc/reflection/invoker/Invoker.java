package com.fqc.reflection.invoker;

/**
 * 反射调用者接口
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}

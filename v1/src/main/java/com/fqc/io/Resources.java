package com.fqc.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 资源的一些工具类
 */
public class Resources {
    //读取资源作为输入流并使用装饰者包装为输入流阅读器
    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    //加载一个资源文件作为输入流
    private static InputStream getResourceAsStream(String resource) throws IOException {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (null != inputStream) {
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    //获得两个类加载器
    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()};
    }

    /*
     * Loads a class
     */
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}

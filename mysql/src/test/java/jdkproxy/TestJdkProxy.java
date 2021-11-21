package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestJdkProxy {
    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        CaculatorImpl cl = new CaculatorImpl();

        Caculator caculator = (Caculator) Proxy.newProxyInstance(cl.getClass().getClassLoader(), cl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String name = method.getName();
                System.out.println("method name = " + name+" args =" + Arrays.asList(args));
                System.out.println("method init");
                Object result = method.invoke(cl, args);
                System.out.println("method end");
                return result;
            }
        });
        int add = caculator.add(1, 4);
        System.out.println("add = " + add);
    }
}

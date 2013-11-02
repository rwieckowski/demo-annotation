package pl.rawie.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxy implements InvocationHandler {
    private Object service;

    @SuppressWarnings("unchecked")
    public static <T> T proxy(T service) {
        return (T) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new ServiceProxy(service));
    }

    public ServiceProxy(Object service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method annotated = service.getClass().getMethod(method.getName(), method.getParameterTypes());
        Annotation[][] paramsAnnotations = annotated.getParameterAnnotations();
        for (int i = 0; i < paramsAnnotations.length; i++) {
            Annotation[] paramAnnotations = paramsAnnotations[i];
            //System.out.printf("%d annotatations", paramAnnotations.length);
            for (Annotation annotation : paramAnnotations)
                if (annotation instanceof Validate) {
                    //System.out.println("@Validate with " + ((Validate) annotation).value());
                    Validator validator = ((Validate) annotation).value().newInstance();
                    if (!validator.validate(args[i]))
                        throw new ValidationException("Illegal value for argument: " + i);
                }
            System.out.println();
        }


        return method.invoke(service, args);
    }
}

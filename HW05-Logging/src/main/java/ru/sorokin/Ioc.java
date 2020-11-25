package ru.sorokin;

import ru.sorokin.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() throws ClassNotFoundException {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        Map<String, Method> mapAllMethod;

        DemoInvocationHandler(MyClassInterface myClass) throws ClassNotFoundException {
            this.myClass = myClass;
            mapAllMethod = reflection(myClass);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method saveMethodName = mapAllMethod.get(method.getName());
            System.out.println("___________________");
            if (saveMethodName != null) {
                printParametrs(saveMethodName, args);
            }
            return method.invoke(myClass, args);
        }

        //filling MAP with methods through reflection
        private Map<String, Method> reflection(MyClassInterface myClass) throws ClassNotFoundException {
            Class<?> clazz1 = Class.forName(myClass.getClass().getName());
            Method[] methodsAll = clazz1.getDeclaredMethods();
            Map<String, Method> mapAllMethod = new HashMap<>();
            for (Method all : methodsAll) {
                if (all.isAnnotationPresent(Log.class)) {
                    mapAllMethod.put(all.getName(), all);
                }
            }
            return mapAllMethod;
        }
    }

    //print parametrs for selected metod
    static private void printParametrs(Method selectedMethod, Object[] args) {
        System.out.println("Name of the logged method is: " + selectedMethod.getName());

        List<Object> parameters = new ArrayList<>();
        Collections.addAll(parameters, selectedMethod.getParameterTypes());

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(parameters.get(i) + ": value is: " + args[i] + ", ");
            }
        } else {
            System.out.println("Parameters are empty");
        }
    }
}



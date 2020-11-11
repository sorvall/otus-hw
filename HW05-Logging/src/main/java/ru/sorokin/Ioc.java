package ru.sorokin;

import ru.sorokin.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ioc {


    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new MyClassImpl());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> clazz = Class.forName(this.myClass.getClass().getName());
            Method selectedMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
            System.out.println("___________________");
            printParametrs(selectedMethod, args);
            return method.invoke(myClass, args);
        }

    }

    //print parametrs for selected metod
    static private void printParametrs(Method selectedMethod, Object[] args) {
        if (selectedMethod.isAnnotationPresent(Log.class)) {
            System.out.println("Executed method name is: " + selectedMethod.getName());

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
}


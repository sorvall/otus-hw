package ru.sorokin.MyFramework;

import java.lang.reflect.Method;
import java.util.List;

class StartTest {
    public static int testFailed;
    public static int testCompleted;
    public static int allTest;

    public static void StartBeforeOrAfterTest(Object object, Class clazz, List<Method> list) {
        try {
            for (Method i : list) {
                Method method = clazz.getDeclaredMethod(i.getName());
                method.setAccessible(true);
                try {
                    method.invoke(object);
                    System.out.println("Выполнен успешно, " + "всего успешно " + ++testCompleted);
                } catch (Exception e) {
                    testFailed++;
                    System.out.println("Метод вызвал исключение!!! " + "Всего неуспешно " + testFailed);
                }
            }
            allTest = testFailed + testCompleted;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void StartCurrentTest(Object object, Class clazz, Method runMethod) {
        try {
            Method method;
            method = clazz.getDeclaredMethod(runMethod.getName());
            method.setAccessible(true);
            try {
                method.invoke(object);
                System.out.println("Выполнен успешно, " + "всего успешно " + ++testCompleted);
            } catch (Exception e) {
                testFailed++;
                System.out.println("Метод вызвал исключение!!! " + "Всего неуспешно " + testFailed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        allTest = testFailed + testCompleted;
    }
}


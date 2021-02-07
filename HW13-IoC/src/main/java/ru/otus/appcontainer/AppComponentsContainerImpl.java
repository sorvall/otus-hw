package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<String, String> convertedNameForMethod = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        checkConfigClass(configClass);
        Constructor<?> constructor = configClass.getDeclaredConstructor();
        Object configInstance = constructor.newInstance();
        Method[] methodsAll = configClass.getDeclaredMethods();
        Map<Integer, List<Method>> mapAllMethod = new HashMap<>();

        //сортировка методов по ордеру в Map
        for (Method currentMethod : methodsAll) {
            if (currentMethod.isAnnotationPresent(AppComponent.class)) {
                AppComponent appComponent = currentMethod.getAnnotation(AppComponent.class);
                if (mapAllMethod.containsKey(appComponent.order())) {
                    List<Method> method;
                    method = mapAllMethod.get(appComponent.order());
                    method.add(currentMethod);
                    mapAllMethod.put(appComponent.order(), method);
                } else {
                    List<Method> method = new ArrayList<>();
                    method.add(currentMethod);
                    mapAllMethod.put(appComponent.order(), method);
                }
            }

        }
        //бежим по Map берем методы и подготоваливаем для них парамерты если нужны
        for (Map.Entry<Integer, List<Method>> entry : mapAllMethod.entrySet()) {
            List<Method> currentListMethod = entry.getValue();
            for (Method currentMethod : currentListMethod) {
                convertedNameForMethod.put(currentMethod.getReturnType().getSimpleName(), currentMethod.getName());
                Class<?>[] parameters = currentMethod.getParameterTypes();
                Object[] parametersForInstance = new Object[parameters.length];
                if (parameters.length > 0) {
                    for (int i = 0; i < parameters.length; i++) {
                        String nameForMethod = parameters[i].getSimpleName();
                        parametersForInstance[i] = appComponentsByName.get(convertedNameForMethod.get(nameForMethod));
                    }

                }

                //создание экземпляра объекта, проверка есть ли параметры
                Object objInstance;
                if (parametersForInstance.length > 0) {
                    objInstance = currentMethod.invoke(configInstance, parametersForInstance);
                } else {
                    objInstance = currentMethod.invoke(configInstance);
                }
                appComponents.add(objInstance);
                appComponentsByName.put(currentMethod.getAnnotation(AppComponent.class).name(), objInstance);
            }

        }
    }


    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (Object obj : appComponents) {
            if (componentClass == obj.getClass()) {
                return (C) obj;
            }
        }
        return (C) appComponentsByName.get(convertedNameForMethod.get(componentClass.getSimpleName()));
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}

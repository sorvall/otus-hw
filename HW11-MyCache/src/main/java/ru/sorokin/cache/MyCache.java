package ru.sorokin.cache;


import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    WeakHashMap<K, V> weakHashMap = new WeakHashMap<>();
    List<HwListener<K, V>>listeners =new ArrayList<>();

    @Override
    public void put(K key, V value) {
        listenerNotify(key, value, "Put");
        weakHashMap.put(key, value);


    }

    @Override
    public void remove(K key) {
        listenerNotify(key, weakHashMap.get(key), "Remove");
        weakHashMap.remove(key);

    }

    @Override
    public V get(K key) {
        listenerNotify(key, weakHashMap.get(key), "Get");
        return weakHashMap.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);

    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);

    }

    @Override
    public void clearCache() {
        weakHashMap.clear();
    }

    private void listenerNotify(K key, V value, String action) {
        listeners.forEach(listener -> listener.notify(key, value, action));
    }
}


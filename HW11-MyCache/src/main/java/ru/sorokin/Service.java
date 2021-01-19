package ru.sorokin;

import ru.sorokin.api.mapper.JdbcMapper;
import ru.sorokin.cache.HwCache;
import ru.sorokin.cache.HwListener;
import ru.sorokin.cache.MyCache;

public class Service<K, T> {
    private final JdbcMapper<T> customORMJdbcMapper;
    private final HwCache<K, T> myCache = new MyCache<>();

    public Service(JdbcMapper<T> customORMJdbcMapper) {
        this.customORMJdbcMapper = customORMJdbcMapper;
    }

    public void save(T objectData) {
        customORMJdbcMapper.insertOrUpdate(objectData);
    }

    public T findById(K id, Class<T> clazz) {
        T returnObject = myCache.get(id);
        if (returnObject == null) {
            T objectData = customORMJdbcMapper.findById(id, clazz);
            myCache.put(id, objectData);
            return objectData;
        } else {
            return returnObject;
        }
    }

    public void clearCache() {
        myCache.clearCache();
    }

   public void addListener(HwListener<K, T> listener) {
        myCache.addListener(listener);
   }

   public void removeListener(HwListener<K, T> listener){
       myCache.removeListener(listener);
   }
}

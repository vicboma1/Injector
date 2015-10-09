package core.mapping;

import core.mapping.maps.Instance;
import core.provider.ProviderImpl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by vicboma on 28/09/14.
 */
public class StoreMappingImpl implements StoreMapping{

    private Map<Class, Instance> map ;

    public static StoreMapping create(){
        return new StoreMappingImpl(ProviderImpl.newInstance(ConcurrentHashMap.class));
    }

    StoreMappingImpl(Map<Class, Instance> map) {
       this.map = map;
    }

    public Set<Map.Entry<Class,Instance>> entrySet(){
        return this.map.entrySet();
    }

    public Instance remove(Class clazz){
        return this.map.remove(clazz);
    }

    public Boolean containsKey(Class clazz){
        return this.map.containsKey(clazz);
    }

    public Instance get(Class clazz){
        return this.map.get(clazz);
    }

    public Instance put(Class clazz, Instance instance){
        return this.map.put(clazz, instance);
    }

    public void clear(){
        this.map.clear();
    }

    public Integer size(){
        return this.map.size();
    }
}
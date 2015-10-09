package core.mapping;

import core.mapping.maps.Prototype;
import core.mapping.maps.Singleton;
import core.provider.Provider;
import core.provider.ProviderImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by vicboma on 26/09/14.
 */
public class ProviderMappingImpl implements ProviderMapping {

    private Map<Class, Provider> map;

    public static ProviderMappingImpl create() {
        return new ProviderMappingImpl(ProviderImpl.newInstance(ConcurrentHashMap.class));
    }

    ProviderMappingImpl(Map<Class, Provider> map) {
        this.map = map;
    }

    public <T> Provider get(Class<? extends T> clazz) {
        return this.map.get(clazz);
    }

    public void put(Provider singleton, Provider prototype ) {
        this.set(Singleton.class, singleton);
        this.set(Prototype.class, prototype);
    }

    private <T> void set(Class<? extends T> clazz, Provider provider){
        this.map.put(clazz, provider);
    }
}
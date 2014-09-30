package core.mapping;

import core.Injector;
import core.mapping.maps.Prototype;
import core.mapping.maps.Singleton;
import core.mapping.maps.Value;

/**
 * Created by vicboma on 26/09/14.
 */
public class InjectionMappingImpl implements InjectionMapping {

    private StoreMapping storeMapping;
    private Injector injector;

    public static InjectionMapping create(StoreMapping storeMapping){
        return new InjectionMappingImpl(storeMapping);
    }

    InjectionMappingImpl(StoreMapping storeMapping) {
        this.storeMapping = storeMapping;
    }

    @Override
    public <T> void toValue(Class<T> modelClass, T instanceClass) {
        this.storeMapping.put(modelClass, new Value(instanceClass));
    }

    @Override
    public <T> void toSingleton(Class<T> ImodelClass, Class<? extends T> modelClass) {
        isInterface(modelClass,"Can't map toSingleton with an ");
        this.storeMapping.put(ImodelClass, new Singleton(modelClass, this.injector));
    }

    @Override
    public <T> void toPrototype(Class<T> ImodelClass, Class<? extends T> modelClass) {
        isInterface(modelClass,"Can't map toPrototype with an ");
        this.storeMapping.put(ImodelClass, new Prototype(modelClass, this.injector));
    }

    @Override
    public <T> void asSingleton(Class<? extends T> modelClass) {
        isInterface(modelClass,"Can't map asSingleton with an ");
        this.storeMapping.put(modelClass, new Singleton(modelClass, this.injector));
    }

    private <T> void isInterface(Class<? extends T> modelClass, String message) {
        if(modelClass.isInterface())
            throw new RuntimeException(message + modelClass);
    }

    public InjectionMapping setInject(Injector inject) {
        this.injector = inject;
        return this;
    }
}

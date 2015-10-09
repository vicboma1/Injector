package core;

import annotation.*;
import core.mapping.maps.Instance;
import core.mapping.*;
import core.provider.Provider;
import core.provider.ProviderImpl;
import core.verify.Loader;
import utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class InjectorImpl implements Injector {

    private InjectorImpl parent;
    private StoreMapping storeMapping;
    private InjectionMapping injectionMapping;
    private ProviderMapping providerMapping;
    private Provider provider;

    public static Injector create() throws Exception {
        Loader.verify();
        final StoreMapping storeMapping = StoreMappingImpl.create();
        final InjectionMapping injectionMapping = InjectionMappingImpl.create(storeMapping);
        final ProviderMapping providerMapping = ProviderMappingImpl.create();
        final ProviderImpl providerImpl = ProviderImpl.create();
        return new InjectorImpl(storeMapping, injectionMapping, providerMapping, providerImpl);
    }

    public InjectorImpl() {

    }

    private InjectorImpl(StoreMapping storeMapping, InjectionMapping injectionMapping, ProviderMapping providerMapping, Provider provider) throws Exception {
        this.storeMapping = storeMapping;
        this.injectionMapping = injectionMapping;
        this.providerMapping = providerMapping;
        this.provider = provider;
        this.providerMapping.put(this.provider, this.provider);
    }

    @Override
    public InjectionMapping map() {
        return ((InjectionMappingImpl) this.injectionMapping).setInject(this);
    }

    @Override
    public <T> T unmap(Class<T> modelClass) throws Exception {
        final Instance remove = this.storeMapping.remove(modelClass);
        final Provider provider = this.providerMapping.get(remove.getClass());
        return (T) remove.get(provider);
    }

    @Override
    public void injectInto(Object instance) throws Exception {
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (method.getAnnotation(PostConstruct.class) != null) {
                Utils.invokeMethod(method, instance);
            }
        }

        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Inject.class) != null) {
                Object value = getInstance(field.getType());
                Utils.setField(field, instance, value);
            }
        }
    }

    @Override
    public void dispose() throws Exception {
        for (Map.Entry<Class, Instance> entry : this.storeMapping.entrySet()) {
            Class key = entry.getKey();
            Instance value = this.storeMapping.remove(key);
            final Provider provider = this.providerMapping.get(key);
            for (Method method : key.getDeclaredMethods()) {
                if (method.getAnnotation(PreDispose.class) != null) {
                    Utils.invokeMethod(method, value.get(provider));
                }
            }
        }
        this.storeMapping.clear();
        this.providerMapping = null;
        this.injectionMapping = null;
        this.provider = null;
        this.storeMapping = null;
    }

    @Override
    public Injector createChild() throws Exception {
        final Injector _injector = InjectorImpl.create();
        _injector.parent(this);
        return _injector;
    }

    @Override
    public <T> T getInstance(Class<? extends T> clazz) throws Exception {
        return getInstance(this, clazz);
    }

    private <T> T getInstance(InjectorImpl requestInjectorImpl, Class<? extends T> clazz) throws Exception {
        if (!this.storeMapping.containsKey(clazz)) {
            if (clazz.isAnnotationPresent(AsSingleton.class))
                map().asSingleton(clazz);
            else if (clazz.isAnnotationPresent(ToSingleton.class)) {
                final Annotation[] annotations = clazz.getAnnotations();
                final ToSingleton toSingleton = (ToSingleton) annotations[0];
                String nameClass = toSingleton.toClass();
                final Object _class = ProviderImpl.newInstance(Class.forName(nameClass));
                this.map().toSingleton((Class<T>) clazz, (Class<? extends T>) _class.getClass());
            } else if (this.parent != null) {
                return RunException(clazz);
            } else {
                return this.parent.getInstance(requestInjectorImpl, clazz);
            }
        }

        this.providerMapping.put(this.provider, requestInjectorImpl.provider);
        final Instance instance = this.storeMapping.get(clazz);
        final Provider provider = this.providerMapping.get(instance.getClass());

        return (T) instance.get(provider);
    }

    public Injector parent() {
        return parent;
    }

    public void parent(Injector parent) {
        this.parent = (InjectorImpl)parent;
    }

    public <T> T RunException(Class<? extends T> clazz) {
        throw (new RuntimeException("Can't find map " + clazz));
    }

    @Override
    public <T> Boolean hasMapping(Class<? extends T> modelClass) {
        return this.storeMapping.containsKey(modelClass);
    }
}






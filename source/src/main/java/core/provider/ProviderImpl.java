package core.provider;

import core.Injector;

/**
 * Created by vicboma on 27/09/14.
 */
public class ProviderImpl implements Provider {

    public static ProviderImpl create(){
        return new ProviderImpl();
    }

    ProviderImpl() {
    }

    @Override
    public Object createInstance(Class clazz, Injector injector) throws Exception {
        final Object instance = ProviderImpl.newInstance(clazz);
        injector.injectInto(instance);
        return instance;
    }

    public static <T> T newInstance(Class<T> destinationClass) {
        T newClass = null;
        try {
            newClass = destinationClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newClass;
    }
}


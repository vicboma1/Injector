package core.mapping.maps;

import core.Injector;
import core.provider.Provider;

/**
 * Created by vicboma on 25/09/14.
 */
public class Singleton implements Instance {
    private Class clazz;
    private Object singleton;
    private Injector injector;

    public Singleton(Class clazz, Injector injector) {
        this.clazz = clazz;
        this.injector = injector;
    }

    @Override
    public Object get(Provider provider) throws Exception {
        if (this.singleton == null)
            this.singleton = provider.createInstance(this.clazz,this.injector);
        return this.singleton;
    }
}
package core.mapping.maps;

import core.Injector;
import core.provider.Provider;

/**
 * Created by vicboma on 25/09/14.
 */
public class Prototype implements Instance {
    private Class clazz;
    private Injector injector;


    public Prototype(Class clazz, Injector injector) {
        this.clazz = clazz;
        this.injector = injector;
    }

    @Override
    public Object get(Provider provider) throws Exception {
        return provider.createInstance(this.clazz, this.injector);
    }
}
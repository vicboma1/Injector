package core.provider;

import core.Injector;

/**
 * Created by vicboma on 28/09/14.
 */
public interface Provider {
    Object createInstance(Class _class, Injector injector) throws Exception;
}
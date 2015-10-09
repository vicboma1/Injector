package core.mapping;

import core.provider.Provider;

/**
 * Created by vicboma on 27/09/14.
 */
public interface ProviderMapping {
    <T> Provider get(Class<? extends T> clazz);
    void put(Provider singleton, Provider prototype) ;
}

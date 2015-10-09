package core.mapping.maps;

import core.provider.Provider;

/**
 * Created by vicboma on 25/09/14.
 */
public interface Instance {
    Object get(Provider provider) throws Exception;
}

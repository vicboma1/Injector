package core.mapping.maps;

import core.provider.Provider;

/**
 * Created by vicboma on 25/09/14.
 */
public class Value implements Instance {
    private Object value;

    public Value(Object value) {
        this.value = value;
    }

    @Override
    public Object get(Provider provider) {
        return value;
    }

}

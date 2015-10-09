package core.mapping;

import core.mapping.maps.Instance;

import java.util.Map;
import java.util.Set;

/**
 * Created by vicboma on 27/09/14.
 */
public interface StoreMapping {
    Set<Map.Entry<Class, Instance>> entrySet();

    Instance remove(Class clazz);

    Boolean containsKey(Class clazz);

    Instance get(Class clazz);

    Instance put(Class clazz, Instance instance);

    void clear();

    Integer size();
}

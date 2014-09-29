package core.mapping;

/**
 * Created by vicboma on 27/09/14.
 */
public interface InjectionMapping {
    <T> void toValue(Class<T> modelClass, T instanceClass);
    <T> void toSingleton(Class<T> ImodelClass, Class<? extends T> modelClass);
    <T> void asSingleton(Class<? extends T> modelClass);
    <T> void toPrototype(Class<T> ImodelClass, Class<? extends T> modelClass);
}

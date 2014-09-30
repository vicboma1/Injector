package core;

import core.mapping.InjectionMapping;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 14/02/14
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public interface Injector
{
    void injectInto(Object instance) throws Exception ;

    <T> T getInstance(Class<? extends T> modelClass) throws Exception ;

    <T> Boolean hasMapping(Class<? extends T> modelClass);

    Injector parent();

    void parent(Injector parent);

    void dispose() throws Exception;

    <T> T unmap(Class<T> modelClass) throws Exception;

    Injector createChild() throws Exception;

    InjectionMapping map();

}

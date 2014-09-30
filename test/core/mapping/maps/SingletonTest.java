package core.mapping.maps;

import core.Injector;
import core.provider.Provider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingletonTest {

    private Injector injector;
    private Provider provider;
    private Singleton singleton;

    @Before
    public void setUp() throws Exception {
        injector = mock(Injector.class);
        provider = mock(Provider.class);
        final Class<Mock> clazz = Mock.class;
        singleton = new Singleton(clazz, injector);
    }

    @Test
    public void testGet() throws Exception {
        final Mock expected = new Mock();
        when(singleton.get(provider)).thenReturn(expected);
        final Object clazz = singleton.get(provider);
        Assert.assertEquals("Not singleton", expected, clazz);
    }
}
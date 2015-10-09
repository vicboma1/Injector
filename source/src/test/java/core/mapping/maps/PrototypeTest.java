package core.mapping.maps;

import core.Injector;
import core.provider.Provider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrototypeTest {

    private Injector injector;
    private Provider provider;
    private Prototype prototype;

    @Before
    public void setUp() throws Exception {
        injector = mock(Injector.class);
        provider =  mock(Provider.class);
        final Class<Mock> clazz = Mock.class;
        prototype = new Prototype(clazz,injector);
    }

    @After
    public void tearDown() throws Exception {
        injector = null;
        provider =  null;
        prototype = null;
    }

    @Test
    public void testGet() throws Exception {
        final Mock expected = new Mock();
        when(prototype.get(provider)).thenReturn(expected);
        final Object clazz = prototype.get(provider);
        Assert.assertEquals("Not prototype",expected,clazz);
    }
}

class Mock {

    Mock() {
    }
}
package core.mapping.maps;

import core.provider.Provider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ValueTest {

    private Provider provider;
    private Value value;
    private String expected = "";

    @Before
    public void setUp() throws Exception {
        provider = mock(Provider.class);
        value = new Value(expected);
    }

    @After
    public void tearDown() throws Exception {
        provider =  null;
        value = null;
    }

    @Test
    public void testGet() throws Exception {
        final String expected = String.valueOf("");
        final Object clazz = value.get(provider);
        Assert.assertEquals("Not value", expected, clazz);
    }
}
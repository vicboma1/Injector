package core.mapping.maps;

import core.provider.Provider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValueTest {

    private Provider provider;
    private Value value;
    private String expected = "";

    @Before
    public void setUp() throws Exception {
        provider = mock(Provider.class);
        value = new Value(expected);
    }

    @Test
    public void testGet() throws Exception {
        final String expected = String.valueOf("");
        when(value.get(provider)).thenReturn(expected);
        final Object clazz = value.get(provider);
        Assert.assertEquals("Not value", expected, clazz);
    }
}
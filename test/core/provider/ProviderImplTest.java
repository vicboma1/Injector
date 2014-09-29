package core.provider;

import core.Injector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProviderImplTest {

    private Provider provider;

    @Before
    public void setUp() throws Exception {
        provider = mock(Provider.class);
    }

    @Test
    public void testCreateInstance() throws Exception {
        final String expected = "";
        final Injector injector = mock(Injector.class);
        final Class<String> stringClass = String.class;
        when(provider.createInstance(stringClass, injector)).thenReturn(expected);
        final Object result = provider.createInstance(stringClass, injector);
        Assert.assertEquals("Not create instance",expected,result);
    }
}
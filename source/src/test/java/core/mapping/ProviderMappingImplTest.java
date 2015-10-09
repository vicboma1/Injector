package core.mapping;

import core.mapping.maps.Prototype;
import core.mapping.maps.Singleton;
import core.provider.Provider;
import core.provider.ProviderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ProviderMappingImplTest {

    private ProviderMapping providerMapping;

    @Before
    public void setUp() throws Exception {
        providerMapping = ProviderMappingImpl.create();
    }

    @After
    public void tearDown() throws Exception {
        this.providerMapping = null;
    }

    @Test
    public void testGet() throws Exception {
        final Class<String> clazz = String.class;
        final Provider provider = this.providerMapping.get(clazz);
        assertNull("Get", provider);
        this.providerMapping.put(ProviderImpl.create(), ProviderImpl.create());
        final Provider singleton = this.providerMapping.get(Singleton.class);
        final Provider prototype = this.providerMapping.get(Prototype.class);
        assertEquals("Fail get", ProviderImpl.class, singleton.getClass());
        assertEquals("Fail get", ProviderImpl.class, prototype.getClass());
    }

    @Test
    public void testPut() throws Exception {
        ProviderMapping providerMappingSpy = spy(providerMapping);
        final Provider singleton = ProviderImpl.create();
        final Provider prototype = ProviderImpl.create();
        providerMappingSpy.put(singleton, prototype);
        verify(providerMappingSpy).put(singleton, prototype);
    }
}
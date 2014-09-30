package core.mapping;

import core.mapping.maps.Instance;
import core.mapping.maps.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class StoreMappingImplTest {

    private StoreMapping storeMapping;
    private Expected expected;

    @Before
    public void setUp() throws Exception {
        this.storeMapping = StoreMappingImpl.create();
    }

    @After
    public void tearDown() throws Exception {
        this.storeMapping = null;
    }

    @Test
    public void testEntrySet() throws Exception {
        this.expected = new Expected(storeMapping);
        for (Map.Entry<Class, Instance> entry : this.storeMapping.entrySet()) {
            assertEquals("Not key",expected.getClazz() , entry.getKey());
            assertEquals("Not value", expected.getTest(), entry.getValue());
        }
    }

    @Test
    public void testRemove() throws Exception {
        this.expected = new Expected(storeMapping);
        final Instance removeMap = this.storeMapping.remove(expected.getClazz());
        assertEquals("Not remove", expected.getTest(), removeMap);
    }

    @Test
    public void testContainsKey() throws Exception {
        this.expected = new Expected(storeMapping);
        assertTrue("Not contains", this.storeMapping.containsKey(expected.getClazz()));
    }

    @Test
    public void testGet() throws Exception {
        final Value test = new Value(String.valueOf("test"));
        final Class<String> clazz = String.class;
        final Instance instanceGetNull = this.storeMapping.get(clazz);
        assertNull("Get", instanceGetNull);
        this.storeMapping.put(clazz, test);
        final Instance instanceGetTrue = this.storeMapping.get(clazz);
        assertEquals("Not get", test, instanceGetTrue);
    }

    @Test
    public void testPut() throws Exception {
        final Value test = new Value(String.valueOf("test"));
        final Class<String> clazz = String.class;
        Instance putInstace = this.storeMapping.put(clazz, test);
        assertNull("Not put",putInstace);
        putInstace = this.storeMapping.put(clazz, test);
        assertEquals("Not put",test,putInstace);
    }

    @Test
    public void testClear() throws Exception {
        final StoreMapping storeMappingSpy = spy(this.storeMapping);
        storeMappingSpy.clear();
        verify(storeMappingSpy).clear();
    }

    @Test
    public void testSize() throws Exception {
        final Value test = new Value(String.valueOf("test"));
        final Class<String> clazz = String.class;
        assertTrue("Not size", this.storeMapping.size() == 0);
        this.storeMapping.put(clazz, test);
        assertTrue("Not size", this.storeMapping.size() == 1);
    }

    private class Expected {
        private Value test;
        private Class<String> clazz;

        private Expected(StoreMapping storeMapping) {
            test = new Value(String.valueOf("test"));
            clazz = String.class;
            storeMapping.put(clazz, test);
        }

        public Value getTest() {
            return test;
        }

        public Class<String> getClazz() {
            return clazz;
        }

    }
}
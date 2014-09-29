package annotation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ToSingletonTest {

    private MockToSingleton mockAsSingleton;

    @Before
    public void setUp() throws Exception {
        mockAsSingleton = new MockAsSingletonImpl();
    }

    @Test
    public void annotation() throws Exception {
        assertTrue(MockToSingleton.class.isAnnotationPresent(ToSingleton.class));
    }
}

@ToSingleton(toClass = "annotation.MockAsSingletonImpl" )
interface MockToSingleton {
}

class MockAsSingletonImpl implements MockToSingleton{

}
package annotation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ToSingletonTest {

    private MockToSingleton mockAsSingleton;

    @Before
    public void setUp() throws Exception {
        this.mockAsSingleton = new MockAsSingletonImpl();
    }

    @After
    public void tearDown() throws Exception {
        this.mockAsSingleton = null;
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
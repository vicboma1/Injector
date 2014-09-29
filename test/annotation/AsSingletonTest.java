package annotation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AsSingletonTest {

    private MockAsSingleton mockAsSingleton;

    @Before
    public void setUp() throws Exception {
        mockAsSingleton = new MockAsSingleton();
    }

    @Test
    public void annotation() throws Exception {
        assertTrue(mockAsSingleton.getClass().isAnnotationPresent(AsSingleton.class));
    }
}

@AsSingleton
class MockAsSingleton {
}
package annotation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AsSingletonTest {

    private MockAsSingleton mockAsSingleton;

    @Before
    public void setUp() throws Exception {
        this.mockAsSingleton = new MockAsSingleton();
    }

    @After
    public void tearDown() throws Exception {
        this.mockAsSingleton = null;
    }

    @Test
    public void annotation() throws Exception {
        assertTrue(this.mockAsSingleton.getClass().isAnnotationPresent(AsSingleton.class));
    }
}

@AsSingleton
class MockAsSingleton {
}
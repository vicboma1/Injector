package core.verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by vicboma on 09/10/15.
 */
public class VerifyTest {

    private Verify verify;
    private Verify spyVerify;

    @Before
    public void setUp() throws Exception {
        verify = new Verify();
        spyVerify = spy(verify);
    }

    @After
    public void tearDown() throws Exception {
        verify = null;
    }

    @Test
    public void testCreate() throws Exception {
        Verify verify = new Verify();
        Assert.assertNotNull("Is null", verify);
    }

    @Test
    public void testInitialize() throws Exception {
        spyVerify.initialize();
        verify(spyVerify).initialize();
    }

    @Test
    public void testDispose() throws Exception {
        spyVerify.dispose();
        verify(spyVerify).dispose();
    }

    @Test
    public void testToString() throws Exception {
        final String toString = "POJO{core.verify='null'}";
        Assert.assertEquals(this.verify.toString(),toString);
    }
}
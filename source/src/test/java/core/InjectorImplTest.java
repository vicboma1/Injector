package core;

import core.mapping.InjectionMapping;
import core.verify.IVerify;
import core.verify.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class InjectorImplTest {

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        this.injector = InjectorImpl.create();
    }

    @After
    public void tearDown() throws Exception {
        this.injector = null;
    }

    @Test
    public void testCreate() throws Exception {
        Injector injector = new InjectorImpl();
        Assert.assertNotNull("Is null", injector);
    }

    @Test
    public void testMap() throws Exception {
        final InjectionMapping injectionMapping = this.injector.map();
        assertNotNull("Fail injectionMapping", injectionMapping);
    }

    @Test
    public void testUnmap() throws Exception {
        final String expected = "";
        final Class<String> modelClass = String.class;
        this.injector.map().asSingleton(modelClass);
        final String unmap = this.injector.unmap(modelClass);
        assertEquals("Fail unmap", expected, unmap);
    }

    @Test
    public void testInjectInto() throws Exception {
        final String expected = "Test";
        Verify pojoAsSingleton = new Verify();
        injector.map().toValue(String.class, expected);
        injector.injectInto(pojoAsSingleton);
        injector.map().asSingleton(Verify.class);
        Verify verifyAsSingleton = injector.getInstance(Verify.class);
        assertEquals("Fail InjectInto", expected, verifyAsSingleton.verify);
    }

    @Test
    public void testDispose() throws Exception {
        final String expected = "Test";
        injector.map().toValue(String.class, expected);
        injector.map().asSingleton(Verify.class);
        Verify verifyAsSingleton = injector.getInstance(Verify.class);
        junit.framework.Assert.assertNotNull(verifyAsSingleton);
        this.injector.dispose();
        try {
            injector.getInstance(Verify.class);
        }catch (Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }

    @Test
    public void testCreateChild() throws Exception {
        final Injector child = this.injector.createChild();
        assertNotEquals("Fail child",this.injector,child);
        assertNull("Fail child", this.injector.parent());
        assertEquals("Fail child", this.injector, child.parent());
    }

    @Test
    public void testGetInstance() throws Exception {
        final Class<String> modelClass = String.class;
        final String expected = "Test";
        this.injector.map().toValue(modelClass, expected);
        final String result = this.injector.getInstance(String.class);
        assertEquals("Fail Get Instance",expected,result);
    }

    @Test
    public void testGetInstanceNullParent() throws Exception {
        injector.parent(null);
        try {
             this.injector.getInstance(String.class);
        }
        catch (Exception e){
            Assert.assertTrue(true);
            return;
        }

        Assert.assertTrue(!true);
    }

    @Test
    public void testRunException() throws Exception {
        InjectorImpl injector = new InjectorImpl();
        try {
            injector.RunException(String.class);
        }
        catch (Exception e){
            Assert.assertTrue(true);
            return;
        }

        Assert.assertTrue(false);

    }


    @Test
    public void testHasMapping() throws Exception {
        final Class<String> modelClass = String.class;
        this.injector.map().asSingleton(modelClass);
        final Boolean result = this.injector.hasMapping(String.class);
        assertTrue("Fail HasMapping", result);
    }


    @Test
    public void testAutoToSingleton() throws Exception {
        IVerify pojoAutoAsSingleton = new Verify();
        injector.map().toValue(String.class, "Test");
        injector.injectInto(pojoAutoAsSingleton);
        injector.getInstance(IVerify.class);
        final Boolean result = this.injector.hasMapping(IVerify.class);
        assertTrue("Fail HasMapping",result);
    }

    @Test
    public void testParentGet() throws Exception {
        final Injector parent = this.injector.parent();
        assertNull("Fail parent", parent);
    }

    @Test
    public void testParentSet() throws Exception {
        final Injector injectorSpy = spy(this.injector);
        injectorSpy.parent(this.injector);
        verify(injectorSpy).parent(this.injector);
    }
}
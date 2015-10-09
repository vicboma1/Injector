package core.mapping;

import core.Injector;
import core.InjectorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class InjectionMappingImplTest {

    private InjectionMapping injectionMapping;
    private StoreMapping storeMapping;
    private InjectionMapping injectionMappingSpy;

    @Before
    public void setUp() throws Exception {
        this.storeMapping = StoreMappingImpl.create();
        this.injectionMapping = InjectionMappingImpl.create(this.storeMapping);
        this.injectionMappingSpy = spy(this.injectionMapping);
    }

    @After
    public void tearDown() throws Exception {
        this.storeMapping = null;
        this.injectionMapping = null;
        this.injectionMappingSpy = null;
    }

    @Test
    public void testToValue() throws Exception {
        final Class<String> modelClass = String.class;
        final String instanceClass = String.valueOf("");
        this.injectionMappingSpy.toValue(modelClass, instanceClass);
        verify(this.injectionMappingSpy).toValue(modelClass, instanceClass);
    }

    @Test
    public void testToSingleton() throws Exception {
        final Class<Number> imodelClass = Number.class;
        final Class<Byte> modelClass = Byte.class;
        this.injectionMappingSpy.toSingleton(imodelClass, modelClass);
        verify(this.injectionMappingSpy).toSingleton(imodelClass, modelClass);
    }

    @Test
    public void testToPrototype() throws Exception {
        final Class<Number> imodelClass = Number.class;
        final Class<Double> modelClass = Double.class;
        this.injectionMappingSpy.toPrototype(imodelClass, modelClass);
        verify(this.injectionMappingSpy).toPrototype(imodelClass, modelClass);
    }

    @Test
    public void testAsSingleton() throws Exception {
        final Class<String> modelClass = String.class;
        this.injectionMappingSpy.asSingleton(modelClass);
        verify(this.injectionMappingSpy).asSingleton(modelClass);
    }

    @Test
    public void testInterface() throws Exception {
        final String message = "Can't map asSingleton with an ";
        final Class<InjectorImpl> modelClass = InjectorImpl.class;
        injectionMappingSpy.isInterface(modelClass, message);
        verify(injectionMappingSpy).isInterface(modelClass, message);
    }

    @Test
    public void testInterfaceException() throws Exception {
        final String message = "Can't map asSingleton with an ";
        final Class<Injector> modelClass = Injector.class;
        try {
            injectionMappingSpy.isInterface(modelClass, message);
        } catch (RuntimeException e){
            verify(injectionMappingSpy).isInterface(modelClass,message);
        }
    }

    @Test
    public void testSetInjector() throws Exception {
        Injector injector = mock(Injector.class);
        final InjectionMapping _injectionMapping = ((InjectionMappingImpl) this.injectionMapping).setInject(injector);
        assertEquals("Not set inject", this.injectionMapping, _injectionMapping);
    }

    @Test
    public void testCreate() throws Exception {
        this.storeMapping = StoreMappingImpl.create();
        this.injectionMapping = new InjectionMappingImpl(this.storeMapping);
        assertNotNull("Is null",injectionMapping);
    }
}
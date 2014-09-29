package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    private Mock mock;

    @org.junit.Before
    public void setUp() throws Exception {
        mock = new Mock();
    }

    @org.junit.Test
    public void testInvokeMethod() throws Exception {
        final String expected = "constructor";
        final String toStringMethod = "toString";
        assertEquals(expected,mock.mockTest());
        for (Method method : mock.getClass().getDeclaredMethods()) {
            if (method.getName().equals(toStringMethod)) {
                Utils.invokeMethod(method, mock);
                assertEquals("Not invoke method",toStringMethod,mock.mockTest());
            }
        }
    }

    @org.junit.Test
    public void testSetField() throws Exception {
        final String expected = "setterField";
        for (Field field : mock.getClass().getDeclaredFields()) {
            if (field.getName().equals("mockTest")) {
                Utils.setField(field, mock, expected);
                assertEquals("Not set field", expected,mock.mockTest());
            }
        }
    }
}


class Mock {

    private String mockTest;

    public Mock() {
        mockTest = "constructor";
    }

    public String mockTest(){
        return mockTest;
    }

    public String toString(){
        mockTest = "toString";
        return mockTest;
    }
}
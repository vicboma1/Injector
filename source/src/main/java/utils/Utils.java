package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Utils {

    public static void invokeMethod(final Method method, final Object instance, final Object... args) throws Exception {
        final boolean isAcceptable = method.isAccessible();
        if (!isAcceptable)
            method.setAccessible(true);

        method.invoke(instance, args);

        if (!isAcceptable)
            method.setAccessible(false);
    }

    public static void setField(final Field field, final Object instance, final Object value) throws Exception {
        final boolean isAcceptable = field.isAccessible();
        if (!isAcceptable)
            field.setAccessible(true);

        field.set(instance, value);

        if (!isAcceptable)
            field.setAccessible(false);
    }
}
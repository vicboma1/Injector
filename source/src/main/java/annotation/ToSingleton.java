package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by vicboma on 27/09/14.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface ToSingleton {
    String toClass();
}


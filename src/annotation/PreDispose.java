package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by vicboma on 26/09/14.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface PreDispose {
}

package core.verify;

import annotation.AsSingleton;
import annotation.Inject;
import annotation.PostConstruct;
import annotation.PreDispose;

import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicboma on 27/09/14.
 */
public class Loader {

    private static Boolean verify = false;
    private static Class<Verify> classVerify = Verify.class;
    private static List<String> verifyMetadata = new ArrayList();

    public static Boolean verify() throws Exception {
        if (!verify) {
            verify = true;
            hasSingleton();
            hasInject();
            hasPostContruct();
            hasPreDestroy();
            check();
        }

        return verify;
    }

    private static void check() throws Exception {
        if (verifyMetadata.size() > 0)
            throw (new Exception("Missing annotation: " + verifyMetadata.toString()));
    }

    private static void hasSingleton() {
        if (!classVerify.isAnnotationPresent(AsSingleton.class))
            verifyMetadata.add("Singleton");
        final AnnotatedType[] annotatedInterfaces = classVerify.getAnnotatedInterfaces();
        if(annotatedInterfaces.length > 0) {
            final AnnotatedType annotatedInterface = annotatedInterfaces[0];
            if (!annotatedInterface.getType().getClass().isInstance(IVerify.class))
                verifyMetadata.add("ISingleton");
        }
    }

    private static void hasPreDestroy() throws NoSuchMethodException {
        if (classVerify.getDeclaredMethod("dispose").getAnnotation(PreDispose.class) == null)
            verifyMetadata.add("PreDispose");
    }

    private static void hasPostContruct() throws NoSuchMethodException {
        if (classVerify.getDeclaredMethod("initialize").getAnnotation(PostConstruct.class) == null)
            verifyMetadata.add("PostConstruct");
    }

    private static void hasInject() throws NoSuchFieldException {
        if (classVerify.getDeclaredField("verify").getAnnotation(Inject.class) == null)
            verifyMetadata.add("Inject");
    }
}

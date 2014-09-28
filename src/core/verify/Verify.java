package core.verify;

import annotation.*;

/**
 * Created by vicboma on 27/09/14.
 */
@AsSingleton
public class Verify implements IVerify {

    @Inject
    public String verify;

    public Verify(){}

    @PostConstruct
    public void initialize() {
        this.verify = " PostConstruct initialize \n";
    }

    @PreDispose
    public void dispose() {
        this.verify += " PreDestroy destroy ";
    }

    @Override
    public String toString() {
        return "POJO{" +"core.verify='" + verify + '\'' +'}';
    }
}

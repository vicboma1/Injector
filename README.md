Injector
========
[![Build Status](https://travis-ci.org/vicboma1/Injector.svg?branch=master)](https://travis-ci.org/vicboma1/Injector)[![Coverage Status](https://coveralls.io/repos/vicboma1/Injector/badge.svg?branch=master&service=github)](https://coveralls.io/github/vicboma1/Injector?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/)
[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/vicboma1/injector/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
[![Analytics](https://ga-beacon.appspot.com/UA-68661131-1/injector/readme)](https://github.com/igrigorik/ga-beacon)


IoC Container - Dependency Injector for Java/Scala

##Main Test

###POJO's 
####Implementation
```java
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
```
   
####API  
```java 
@ToSingleton(toClass = "core.verify.Verify")
public interface IVerify {
    String toString();
}
```

## Statements

### As Singleton
```
Verify pojoAsSingleton = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoAsSingleton);
injector.map().asSingleton(Verify.class);
Verify verifyAsSingleton = injector.getInstance(Verify.class);
```

### To Singleton
```
IVerify pojoToSingleton = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoToSingleton);
injector.map().toSingleton(IVerify.class,Verify.class);
IVerify verifyToSingleton = injector.getInstance(IVerify.class);
```

### To Prototype
```
IVerify pojoToPrototype = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoToPrototype);
injector.map().toPrototype(IVerify.class,Verify.class);
IVerify verifyToPrototype = injector.getInstance(IVerify.class);
```

### ToValue
```
IVerify pojoToValue = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoToValue);
injector.map().toValue(IVerify.class,pojoToValue);
IVerify verifyToValue = injector.getInstance(IVerify.class);
```

### Automatic Injection with ToSingleton annotation in IVerify.class
```
IVerify pojoAutoToSingleton = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoAutoToSingleton);
IVerify verifyAutoToSingleton = injector.getInstance(IVerify.class);
```

### Automatic Injection with AsSingleton annotation in Verify.class
```
Verify pojoAutoAsSingleton = new Verify();
injector.map().toValue(String.class, "Test");
injector.injectInto(pojoAutoAsSingleton);
Verify verifyAutoAsSingleton = injector.getInstance(Verify.class);
```





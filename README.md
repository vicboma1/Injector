Injector
========
[![Coverage Status](https://coveralls.io/repos/vicboma1/Injector/badge.svg?branch=master&service=github)](https://coveralls.io/github/vicboma1/Injector?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/)
[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/vicboma1/injector/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

IoC Container - Dependency Injector for Java/Scala

##Main Test
       
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





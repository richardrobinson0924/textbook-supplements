# Classes

## Format
A typical non-static class in Java has the following syntax and structure for the class file:
```java
    public class ClassName extends SuperClass implements InterfaceName {
        // attributes section
        // constructors section
        // methods section
    }
```

#### Attributes
Attributes are global fields which describe a class' instance. Generally in non-static classes, all attributes should be `private`. As well, attributes which do not change should have the keyword `final`. For example, an attributes section could be:
```java
    private final int ANSWER = 42;
    private Object width, height;
```
The global attributes are typically called via `this.paramName` so as not to be differentiate between local parameters.

#### Constructors
The constructors section of a class generally has three or more constructors, such as:
```java
    public ClassName() {
        this(0, 0);
    }

    public ClassName(Object paramOne, Object paramTwo) {
        this.setParamOne(paramOne);
        this.setParamTwo(paramTwo);
    }

    public ClassName(ClassName other) {
        this(other.getParamOne, other.getParamTwo)
    }
```
The _default constructor_ refers to the one with no arguments (the first constructor in the example). In this, it calls the custom constructor and passes a default value(s) to it as parameter(s), typically 0.

The _custom constructor_ is the main one, to which other constructors call. It typically contains all the global attributes as its parameters. In the constructor body, it uses _setter methods_ to set all the `this.` attributes to the parameters.

Lastly, the _copy constructor_ uses _getter methods_ to get the attributes of another instance `other` of the class. It then passes these to the custom constructor as its parameters.


## Methods
Methods are the most important aspect of classes, as they control the functions of the class.

#### Accessors & Mutators
All classes should have both accessor (getters) and mutator (setters) methods. Typically, these are given by
```java
    public Object getParamOne() {
        return this.paramOne;
    }

    public void setParamOne(Object paramOne) {
        this.paramOne = paramOne;
    }
```
These methods get the specified attribute of the instance, and set the specified attribute to the given parameter. Note that these should be the only methods which directly access the `this.` attributes; all other methods should call these methods instead.

#### Obligatory Methods
All classes should override the standard `.equals()`, `.hashCode()`, and `.toString()` methods. However, this is not required by Java but may result in errors if not included.

A typical `equals` method tests for equality between two instances, and may be a function of the IDE to easily implement. A typical version looks like:
```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null && this.getClass() == obj.getClass()) {
            ClassName other = (ClassName) o;
            return (this.getParamOne() == other.getParamOne());
        } else return false;
    }
```

A typical `hashCode` method returns the integer given by `Objects.hash(params)` unless otherwise specified;
```java
    @Override
    public int hashCode() {
        return Objects.hash(this.getParamOne, this.getParamTwo);
    }
```
 Additionally, a typical `toString` methods returns the string that is printed when inputted into the `print` method in the main class;
 ```java
    @Override
    public String toString() {
        return "( " + this.getParamOne + ", " + this.getParamTwo + ")";
    }
 ```

#### Static Methods
Static methods of a class are defined by the keyword `static` and work differently than regular methods. For a non-static method, an instant of a class must first be created before calling the method;
```java
    ClassName instance = new ClassName();
    instance.methodName();
```
However, static methods may be called directly and are solely dependent on their parameters as arguments. They are called via `ClassName.methodName(params)` and are created via
```java
    public static Object methodName(Object params) {
        // do calculation using params
        return calculation;
    }
```

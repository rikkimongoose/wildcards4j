# wildcards4j
**wildcards4j** is a lightweight library for Java 8+ to compare string with wildcards patterns.

An wildcard pattern includes the characters ‘?’ or ‘*’
 * _?_ – matches any single character.
 * _*_ – Matches any sequence of characters (including an empty sequence).
 
`"b?t"` matches `"bat"`, `"bot"`, but not `"beet"`.

`"b*t"` matches `"bat"`, `"bot"`, `"beet"`, `"bt"`, `"best"`, `"be a carrot"`.

The same wildcards are used for file masks (like `*.txt`)

## Install
The **wildcards4j** package is currently avaible at Maven Central repository.
                    
1. Add to yours `pom.xml`:
```xml
<dependency>
  <groupId>io.github.rikkimongoose</groupId>
  <artifactId>wildcards4j</artifactId>
  <version>1.0.0</version>
</dependency>
```
2. Run via command line:
```bash
$ mvn install
```

## Usage

```java
WildcardsUtils.equalsByWildcards("myfile.txt", "*.txt");
```

or

```java
import static com.github.wildcards4j.WildcardsUtils.equalsByWildcards;
//...
equalsByWildcards("myfile.txt", "*.txt");
```

There're following implementations of compare algorithm:

* **DYNAMIC** – with linear complicity. 
* **RECURSIVE** – optimal if string or pattern are very short.

The **DYNAMIC** implementation is default.

Comparing using **RECURSIVE** implementation:

```java
WildcardsUtils.equalsByWildcards("myfile.txt", "*.txt", WildcardsSearchAlgorithm.RECURSIVE);
```

## Requirements
Java 1.8+

## License
This project available under Apache 2.0 license
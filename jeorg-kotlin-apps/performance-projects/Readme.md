# performance-projects

## Intro

This project is an experimental project made only for performance tests and to explore the developments made by Oracle.
The main goal is to understand new changes made to the Java platform.

Currently supporting Java 17.

#### Stable releases

-   [0.0.0](https://github.com/jesperancinha/performance-projects/tree/0.0.0) - [cb02dcc996c9a36f4fb404c7c2be390688252181](https://github.com/jesperancinha/performance-projects/tree/0.0.0) - JDK 17 / Kohsuke Args 4J / Java
-   [1.0.0](https://github.com/jesperancinha/performance-projects/tree/1.0.0) - [f277716aee0e663c5ce932c1f08b56e5596bc006](https://github.com/jesperancinha/performance-projects/tree/1.0.0) - JDK 17 / Picocli / Kotlin 1.8.0

## Upgrade

Find the latest tested `GRADLE_VERSION` in the [Makefile.mk](Makefile.mk) file.

```shell
gradle wrapper --gradle-version $GRADLE_VERSION
```

## Contents

* [performance-buffered-reader](performance-buffered-reader)
* [performance-input-test-generator](performance-input-test-generator)
* [performance-jump-search](performance-jump-search)

### Java Migration

##### Please Install SDK Man for a faster installation

https://sdkman.io/install

##### Install Java and Gradle on their latest versions

```bash

sdk install java
sdk install gradle

```

### Running these tests

* Make sure that Intellij or Eclipse have interpreted this code correctly
```bash

gradle idea 

gradle cleanIdea 

gradle test

./gradlew test

```

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)

# performance-projects

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Performance%20Objects%20&color=informational)](https://github.com/jesperancinha/performance-projects)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CircleCI](https://circleci.com/gh/jesperancinha/performance-projects.svg?style=svg)](https://circleci.com/gh/jesperancinha/performance-projects)
[![performance-projects](https://github.com/jesperancinha/performance-projects/actions/workflows/performance-projects.yml/badge.svg)](https://github.com/jesperancinha/performance-projects/actions/workflows/performance-projects.yml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/98bb1264a9ee4986bb6700b2b1bb9273)](https://www.codacy.com/app/jofisaes/performance-projects?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/performance-projects&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/777f3fdd-9d28-4edf-add3-7a3df5c573a5)](https://codebeat.co/projects/github-com-jesperancinha-performance-projects-master)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/98bb1264a9ee4986bb6700b2b1bb9273)](https://www.codacy.com/gh/jesperancinha/performance-projects/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/performance-projects&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/performance-projects/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/performance-projects?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/performance-projects/branch/master/graph/badge.svg?token=IyBAUw18Z1)](https://codecov.io/gh/jesperancinha/performance-projects)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/performance-projects.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/performance-projects.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/performance-projects.svg)](#)

---

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

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

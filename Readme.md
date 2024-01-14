# Coffee paradigms simulator

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/coffee-paradigms)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Coffee%20Paradigms&color=informational)](https://github.com/jesperancinha/coffee-paradigms)

[![CircleCI](https://circleci.com/gh/jesperancinha/coffee-paradigms.svg?style=svg)](https://circleci.com/gh/jesperancinha/coffee-paradigms)
[![coffee-paradigms](https://github.com/jesperancinha/coffee-paradigms/actions/workflows/coffee-paradigms.yml/badge.svg)](https://github.com/jesperancinha/coffee-paradigms/actions/workflows/coffee-paradigms.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/gh/jesperancinha/coffee-paradigms/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/coffee-paradigms&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/coffee-paradigms/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/coffee-paradigms?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/coffee-paradigms/branch/master/graph/badge.svg?token=DETmpoHIhj)](https://codecov.io/gh/jesperancinha/coffee-paradigms)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/gh/jesperancinha/coffee-paradigms/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/coffee-paradigms&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/0d45f066-b81a-4cb8-ae72-d3f6daf5b736)](https://codebeat.co/projects/github-com-jesperancinha-coffee-paradigms-master)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/coffee-paradigms.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/coffee-paradigms.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/coffee-paradigms.svg)](#)
---

## Description

The goal of this project is to simulate multiple people using coffee machines throughout a specific period of the day.

This is not a game, but only a configurable simulator which will allow you to calculate how long fellow employees and colleagues can take to have coffee.

You will be able to:

* Configure the type of coffee they are going to take.

* Follow realtime the usage of the different coffee machines

* Storyline and input variables:

> This is an office where many engineers, designers, product owners, managers, scrumm masters work.
>
> The following are the global events that will take place at the very first begining:
>
> * Find a cup
>
> * Put under outlet
>
> * Take cup and leave
>
> Every office has it's coffee usage. For this event a **specific time frame** is given for the simulation.
>
> Each person can choose a variety of **coffees and cups**.
>
> Each cafeteria is equipped with a specified **number** of coffee machines.
>
> Each coffee machine provides **different types** of coffees (examples):
>
> * Mocha
>
> * Latte
>
> * Expresso
>
> * Double Expresso
>
>> These types of coffee can be specified by task and by **concurrency**
>
>
> There are multiple **types of payment** machines (examples):
>
> * You pay before you get your coffee.
>
> * You pay whilst your coffee is being made
>
> * You don't pay anything at all
>
> * You pay after you get your coffee
>
>> You will be able to specify diferent types of payment. The solution provided is to be as generic as possible.

For this simulator, socializing times are not considered because they may vary a lot, from non-existent to very lasting.

Essentially you will get an average, mode and standard deviation for the amount you times you decide to run this specific simulation.

#### Stable releases

-   [0.0.0](https://github.com/jesperancinha/coffee-paradigms/tree/0.0.0) - [84ed2eeffb8c602bf6aef6eab50b86a6b950977a](https://github.com/jesperancinha/coffee-paradigms/tree/0.0.0) - Java / JDK11 / Kohsuke
-   [1.0.0](https://github.com/jesperancinha/coffee-paradigms/tree/1.0.0) - [26aa1d3882be25671d353507b374bfdf13a7cd06](https://github.com/jesperancinha/coffee-paradigms/tree/1.0.0) - Kotlin 1.8.0 / JDK19 / Picocli

## Usage

* Go to target in the [coffee-system](./coffee-system) folder and pick the jar generated file. Then try the following example (or with your own files):

```
$ java -jar coffee-system-1.0-SNAPSHOT.jar -it 1 -ud "test-classes/employees_example_test_1.xml" -md "test-classes/coffemachine_example_test_1.xml" -pre 2 -post 3
```

To make it easy I made a few [Makefile](./Makefile) scripts:

1.  Make the build and run: `make build-run-a`
2.  Just run: `make run-a`

## Input files format

* Employees:

[cs_employee.xsd](https://github.com/jesperancinha/coffee-paradigms/blob/master/coffee-system-api/src/main/resources/cs_employee.xsd)

* Coffee Machines:

[cs_employee.xsd](https://github.com/jesperancinha/coffee-paradigms/blob/master/coffee-system-api/src/main/resources/cs_employee.xsd)


## References

-   [Generating classes from XSD under Java 11 – the right way](https://artofcode.wordpress.com/2019/02/28/generating-classes-from-xsd-under-java-11-the-right-way/)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)

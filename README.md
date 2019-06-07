# Coffee paradigms simulator

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/app/jofisaes/coffee-paradigms?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/coffee-paradigms&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/0d45f066-b81a-4cb8-ae72-d3f6daf5b736)](https://codebeat.co/projects/github-com-jesperancinha-coffee-paradigms-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/coffee-paradigms.svg?style=svg)](https://circleci.com/gh/jesperancinha/coffee-paradigms)
[![Build Status](https://travis-ci.org/jesperancinha/coffee-paradigms.svg?branch=master)](https://travis-ci.org/jesperancinha/coffee-paradigms)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/coffee-paradigms?branch=master)](https://bettercodehub.com/)
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


## Usage

* Go to target in the coffe-system folder and pick the jar generated file. Then try the following example (or with your own files):

```
$ java -jar coffee-system-1.0-SNAPSHOT.jar -it 1 -ud "test-classes/employees_example_test_1.xml" -md "test-classes/coffemachine_example_test_1.xml" -pre 2 -post 3
```

## Input files format


* Employees:

[cs_employee.xsd](https://github.com/jesperancinha/coffee-paradigms/blob/master/coffee-system-api/src/main/resources/cs_employee.xsd)

* Coffee Machines:

[cs_employee.xsd](https://github.com/jesperancinha/coffee-paradigms/blob/master/coffee-system-api/src/main/resources/cs_employee.xsd)

## Status

*Under development*

## Change logs

201605<>:   
-   As of 9th of May of 2016, the first functional version became available.

> Example Output:
```text
2016-05-08 18:06:34.417 INFO  ClassPathXmlApplicationContext:578 - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@69222c14: startup date [Sun May 08 18:06:34 CEST 2016]; root of context hierarchy
2016-05-08 18:06:34.443 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from class path resource [META-INF/config.xml]
2016-05-08 18:06:34.808 INFO  PreActionCallableImpl:58 - EmployeeCallable Marco is waiting in line
2016-05-08 18:06:34.808 INFO  PreActionCallableImpl:58 - EmployeeCallable Joao is waiting in line
2016-05-08 18:06:34.809 INFO  PreActionCallableImpl:61 - Starting with choose a cup
2016-05-08 18:06:34.809 INFO  PreActionCallableImpl:61 - Starting with choose a cup
2016-05-08 18:06:34.819 INFO  PreActionCallableImpl:61 - Starting with put cup in outlet
2016-05-08 18:06:34.819 INFO  PreActionCallableImpl:61 - Starting with put cup in outlet
2016-05-08 18:06:34.848 INFO  CoffeeCallableImpl:34 - 0 - Starting task heating to make coffee
2016-05-08 18:06:34.848 INFO  CoffeeCallableImpl:34 - 0 - Starting task grinding coffee to make coffee
2016-05-08 18:06:34.848 INFO  CoffeeCallableImpl:34 - 0 - Starting task heating to make coffee
2016-05-08 18:06:34.853 INFO  CoffeeCallableImpl:34 - 0 - Starting task grinding coffee to make coffee
2016-05-08 18:06:34.868 INFO  CoffeeCallableImpl:34 - 1 - Starting task pouring milk to make coffee
2016-05-08 18:06:34.868 INFO  CoffeeCallableImpl:34 - 1 - Starting task pouring milk to make coffee
2016-05-08 18:06:34.889 INFO  CoffeeCallableImpl:34 - 2 - Starting task switch time to make coffee
2016-05-08 18:06:34.889 INFO  CoffeeCallableImpl:34 - 2 - Starting task switch time to make coffee
2016-05-08 18:06:34.920 INFO  CoffeeCallableImpl:34 - 3 - Starting task pouring coffee to make coffee
2016-05-08 18:06:34.921 INFO  CoffeeCallableImpl:34 - 3 - Starting task pouring coffee to make coffee
2016-05-08 18:06:34.962 INFO  PaymentCallableImpl:54 - PaymentCallable with noPayment
2016-05-08 18:06:34.962 INFO  PaymentCallableImpl:54 - PaymentCallable with noPayment
2016-05-08 18:06:34.964 INFO  PreActionCallableImpl:40 - Ending with take cup and leave
2016-05-08 18:06:34.964 INFO  PreActionCallableImpl:40 - Ending with take cup and leave
2016-05-08 18:06:34.969 INFO  PreActionCallableImpl:40 - Ending with dummy
2016-05-08 18:06:34.969 INFO  PreActionCallableImpl:40 - Ending with dummy

```

## About me

-   Twitter [@jofisaes](https://twitter.com/jofisaes)
-   GitHub [jesperancinha](https://github.com/jesperancinha)
-   Free Code Camp [jofisaes](https://www.freecodecamp.org/jofisaes)
-   Hackerrank [jofisaes](https://www.hackerrank.com/jofisaes)

## License

```
Copyright 2016-2019 João Esperancinha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

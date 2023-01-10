# Coffee paradigms simulator

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/coffee-paradigms)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Coffee%20Paradigms&color=informational)](https://github.com/jesperancinha/coffee-paradigms)

[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/coffee-paradigms/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/coffee-paradigms?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/coffee-paradigms/branch/master/graph/badge.svg?token=DETmpoHIhj)](https://codecov.io/gh/jesperancinha/coffee-paradigms)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/gh/jesperancinha/coffee-paradigms/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/coffee-paradigms&utm_campaign=Badge_Coverage)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/gh/jesperancinha/coffee-paradigms/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/coffee-paradigms&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/0d45f066-b81a-4cb8-ae72-d3f6daf5b736)](https://codebeat.co/projects/github-com-jesperancinha-coffee-paradigms-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/coffee-paradigms.svg?style=svg)](https://circleci.com/gh/jesperancinha/coffee-paradigms)

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

2019-06-10:

-   Modularization with Java 11

2019-06-09:

-   Project migrated to Java 11

2016-05-<>:

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

## References

-   [Generating classes from XSD under Java 11 – the right way](https://artofcode.wordpress.com/2019/02/28/generating-classes-from-xsd-under-java-11-the-right-way/)

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/mastodon-20.png "Mastodon")](https://masto.ai/@jesperancinha)
| [Sessionize](https://sessionize.com/joao-esperancinha/)
| [Spotify](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm?si=b54b89eae8894960)
| [Medium](https://medium.com/@jofisaes)
| [Buy me a coffee](https://www.buymeacoffee.com/jesperancinha)
| [Credly Badges](https://www.credly.com/users/joao-esperancinha)
| [Google Apps](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Docker Images](https://hub.docker.com/u/jesperancinha)
| [Stack Overflow Profile](https://stackoverflow.com/users/3702839/joao-esperancinha)
| [Reddit](https://www.reddit.com/user/jesperancinha/)
| [Dev.TO](https://dev.to/jofisaes)
| [Hackernoon](https://hackernoon.com/@jesperancinha)
| [Code Project](https://www.codeproject.com/Members/jesperancinha)
| [BitBucket](https://bitbucket.org/jesperancinha)
| [GitLab](https://gitlab.com/jesperancinha)
| [Coursera](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
| [FreeCodeCamp](https://www.freecodecamp.org/jofisaes)
| [HackerRank](https://www.hackerrank.com/jofisaes)
| [LeetCode](https://leetcode.com/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [CodeWars](https://www.codewars.com/users/jesperancinha)
| [Code Pen](https://codepen.io/jesperancinha)
| [Hacker Earth](https://www.hackerearth.com/@jofisaes)
| [Khan Academy](https://www.khanacademy.org/profile/jofisaes)
| [Hacker News](https://news.ycombinator.com/user?id=jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
| [LinkedIn](https://www.linkedin.com/in/joaoesperancinha/)
| [Xing](https://www.xing.com/profile/Joao_Esperancinha/cv)
| [Tumblr](https://jofisaes.tumblr.com/)
| [Pinterest](https://nl.pinterest.com/jesperancinha/)
| [Quora](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
| [VMware Spring Professional 2021](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
| [Oracle Certified Professional, Java SE 11 Programmer](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
| [Oracle Certified Professional, JEE7 Developer](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
| [IBM Cybersecurity Analyst Professional](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
| [Certified Advanced JavaScript Developer](https://cancanit.com/certified/1462/)
| [Certified Neo4j Professional](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
| [Deep Learning](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
| [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)

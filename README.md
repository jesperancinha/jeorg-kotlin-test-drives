# Coffee paradigms simulator
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Coffee%20Paradigms&color=informational)](https://github.com/jesperancinha/coffee-paradigms)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4619967a56c24086b00a7e0344aebaa8)](https://www.codacy.com/app/jofisaes/coffee-paradigms?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/coffee-paradigms&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/0d45f066-b81a-4cb8-ae72-d3f6daf5b736)](https://codebeat.co/projects/github-com-jesperancinha-coffee-paradigms-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/coffee-paradigms.svg?style=svg)](https://circleci.com/gh/jesperancinha/coffee-paradigms)
[![Build Status](https://travis-ci.org/jesperancinha/coffee-paradigms.svg?branch=master)](https://travis-ci.org/jesperancinha/coffee-paradigms)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/coffee-paradigms?branch=master)](https://bettercodehub.com/)

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

## About me 👨🏽‍💻🚀

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/acclaim-20.png "Acclaim")](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/instagram-20.png "Instagram")](https://www.instagram.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)

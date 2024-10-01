SHELL := /bin/sh
GRADLE_VERSION ?= 8.10.1

first:
	make buildw build-maven

b?: buildw build-maven

build-maven:
	if [ -f pom.xml  ]; then mvn clean install;fi

buildw:
	if [ -f "build.gradle.kts"  ] || [ -f build.gradle ]; then gradle build; fi

build:
	gradle build

wrapper:
	gradle wrapper

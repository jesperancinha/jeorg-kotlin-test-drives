SHELL := /bin/bash
GRADLE_VERSION ?= 8.10.1

first:
	make b

b?: buildw

buildw:
	./gradlew build

build:
	gradle build

wrapper:
	gradle wrapper
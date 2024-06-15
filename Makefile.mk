SHELL := /bin/bash
GRADLE_VERSION ?= 8.8

first:
	make b

b?: buildw

buildw:
	./gradlew build

build:
	gradle build

wrapper:
	gradle wrapper
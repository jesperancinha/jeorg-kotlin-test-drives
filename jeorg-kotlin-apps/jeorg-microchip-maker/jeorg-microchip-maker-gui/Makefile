include Makefile.mk
include ../../../Makefile.mk

clean:
	if [ -d ../../../kotlin-js-store ]; then rm -r .../../../kotlin-js-store; fi
b: clean build-gradle
build-gradle:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --stacktrace; \
	./gradlew build test
build-gradle-report:
	gradle wrapper
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper

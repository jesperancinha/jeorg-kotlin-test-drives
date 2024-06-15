include Makefile.mk

b: build-gradle
build-gradle:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	./gradlew build test
build-gradle-report:
	gradle wrapper
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper

include Makefile.mk

b: build-gradle-report
build-gradle:
	./gradlew build test
build-gradle-report:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	gradle clean build test jacocoTestReport -i
build-wrapper:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace

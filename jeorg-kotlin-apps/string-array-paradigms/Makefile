include Makefile.mk

first: build-gradle

publish:
	cd string-paradigm-api && gradle clean build && gradle assemble test publishToMavenLocal
	cd string-paradigm-expression-api && gradle clean build && gradle assemble test publishToMavenLocal
b: build-gradle-report
build-gradle: publish
	gradle build test
build-gradle-report:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace

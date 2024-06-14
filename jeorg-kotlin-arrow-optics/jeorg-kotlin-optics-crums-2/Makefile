b: build-gradle-report
build-gradle:
	./gradlew build test
build-gradle-report:
	gradle wrapper
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper

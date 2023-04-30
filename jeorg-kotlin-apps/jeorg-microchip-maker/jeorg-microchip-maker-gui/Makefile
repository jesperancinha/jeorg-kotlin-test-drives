b: build-gradle
build-gradle:
	gradle wrapper
	gradle kotlinUpgradeYarnLock
	./gradlew build test
build-gradle-report:
	gradle wrapper
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper

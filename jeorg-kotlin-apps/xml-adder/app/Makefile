b: buildw
wrapper:
	gradle wrapper
buildw:
	gradle --stop
	gradle clean build test publishToMavenLocal
test-gradle: buildw
build-report:
	gradle clean build test jacocoTestReport -i
send-report:
	./gradlew -i
coverage: build-report send-report
	gradle clean build test jacocoTestReport -i
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION);
upgrade-mac-os:
	brew upgrade gradle

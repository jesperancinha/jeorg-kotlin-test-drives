b: buildw
wrapper:
	gradle wrapper
buildw:
	./gradlew --stop
	./gradlew clean build test publishToMavenLocal
test-gradle: buildw
build-report:
	./gradlew clean build test jacocoTestReport -i
send-report:
	./gradlew -i
coverage: build-report send-report
	./gradlew clean build test jacocoTestReport -i
upgrade:
	gradle wrapper --gradle-version 7.6
upgrade-mac-os:
	brew upgrade gradle

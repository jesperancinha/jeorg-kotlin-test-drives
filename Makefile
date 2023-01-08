b: buildw
wrapper:
	gradle wrapper
buildw:
	./gradlew --stop
	gradle clean build test publishToMavenLocal
test-gradle: buildw
coverage:
	./gradlew clean build test jacocoTestReport -i
	gradle -i
upgrade:
	gradle wrapper --gradle-version 7.3.3
upgrade-mac-os:
	brew upgrade gradle

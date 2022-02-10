wrapper:
	gradle wrapper
buildw:
	gradle clean build test publishToMavenLocal
coverage:
	./gradlew clean build test jacocoTestReport -i
	gradle -i
upgrade:
	gradle wrapper --gradle-version 7.3.3
upgrade-mac-os:
	brew upgrade gradle

b: buildw
wrapper:
	gradle wrapper
buildw:
	cd string-paradigm-api && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd string-paradigm-expression-api && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd string-paradigm-expression-no-wrapper && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	gradle clean build test publishToMavenLocal
upgrade:
	gradle wrapper --gradle-version 7.6
upgrade-mac-os:
	brew upgrade gradle
wrapper:
	gradle wrapper
buildw:
	./gradlew clean build test jacocoTestReport -i && ./gradlew -i
upgrade:
	gradle wrapper --gradle-version 7.3.3
upgrade-mac-os:
	brew upgrade gradle
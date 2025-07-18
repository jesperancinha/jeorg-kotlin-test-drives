include ../Makefile.mk

b: buildw
clean:
	gradle --stop
	./gradlew clean
upgrade-build: clean upgrade b
wrapper:
	gradle wrapper
buildw:
	gradle clean build test jacocoTestReport -i && ./gradlew -i
debug:
	gradle clean build test jacocoTestReport -i --stacktrace --debug
	./gradlew --stacktrace --debug
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION)
upgrade-mac-os:
	brew upgrade gradle

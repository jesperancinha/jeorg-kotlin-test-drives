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
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-gradle-update deps-plugins-update deps-java-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash

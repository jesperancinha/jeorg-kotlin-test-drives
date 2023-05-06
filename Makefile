SHELL := /bin/bash
GRADLE_VERSION := 8.1.1

b: buildw
wrapper:
	gradle wrapper
buildw:
	cd string-paradigm-api && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd string-paradigm-expression-api && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	cd string-paradigm-expression-no-wrapper && gradle wrapper && ./gradlew clean build && gradle assemble test publishToMavenLocal
	gradle clean build test publishToMavenLocal
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd string-paradigm-api && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd string-paradigm-expression-api && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd string-paradigm-expression-no-wrapper && gradle wrapper --gradle-version $(GRADLE_VERSION)
upgrade-mac-os:
	brew upgrade gradle
upgrade-gradle:
	sudo apt upgrade
	sudo apt update
	export SDKMAN_DIR="$(HOME)/.sdkman"; \
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
	source "$(HOME)/.sdkman/bin/sdkman-init.sh"; \
	sdk update; \
	gradleOnlineVersion=$(shell curl -s https://services.gradle.org/versions/current | jq .version | xargs -I {} echo {}); \
	echo $$gradleOnlineVersion; \
	if [[ -z "$$gradleOnlineVersion" ]]; then \
		sdk install gradle $(GRADLE_VERSION); \
		sdk use gradle $(GRADLE_VERSION); \
	else \
		sdk install gradle $$gradleOnlineVersion; \
		sdk use gradle $$gradleOnlineVersion; \
	fi
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current

SHELL := /bin/bash
GRADLE_VERSION ?= 8.14
MODULE_LOCATIONS := string-paradigm-api \
					string-paradigm-expression-api \
					string-paradigm-expression-no-wrapper
b: buildw
wrapper:
	gradle wrapper
buildw:
	if [ -d build ]; then rm -r build; fi; \
	gradle wrapper; \
	./gradlew
	gradle clean build test publishToMavenLocal --stacktrace
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION)
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
	if [[ -z "$$gradleOnlineVersion" ]]; then \
		sdk install gradle $(GRADLE_VERSION); \
		sdk use gradle $(GRADLE_VERSION); \
	else \
		sdk install gradle $$gradleOnlineVersion; \
		sdk use gradle $$gradleOnlineVersion; \
		export GRADLE_VERSION=$$gradleOnlineVersion; \
	fi;
	make upgrade
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-plugins-update deps-java-update deps-gradle-update

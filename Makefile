SHELL := /bin/bash
GRADLE_VERSION := 8.0.2

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
	export SDKMAN_DIR="$(HOME)/.sdkman"
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk update
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk install gradle $(GRADLE_VERSION)
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk use gradle $(GRADLE_VERSION)

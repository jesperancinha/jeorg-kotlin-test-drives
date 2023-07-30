SHELL := /bin/bash
GRADLE_VERSION ?= 8.2.1
MODULE_LOCATIONS := jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-arrow-optics-gradle-1 \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-optics-crums-1

b: clean build
clean:
	if [[ -f jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store/yarn.lock ]]; then rm jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store/yarn.lock; fi
	if [[ -d jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build ]]; then rm -r jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build; fi
build: build-gradle build-maven
build-maven:
	mvn clean install
build-gradle:
	gradle wrapper
	./gradlew build test
	@for location in $(MODULE_LOCATIONS); do \
		export CURRENT=$(shell pwd); \
		echo "Building $$location..."; \
		cd $$location; \
		make b; \
		cd $$CURRENT; \
	done
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION)
	@for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Upgrading $$location..."; \
		cd $$location; \
		gradle wrapper --gradle-version $(GRADLE_VERSION); \
		cd $$CURRENT; \
	done
build-chip-maker:
	cd jeorg-kotlin-apps/jeorg-microchip-maker && mvn clean install
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui && gradle build test
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

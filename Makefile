include Makefile.mk

MODULE_LOCATIONS := jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-optics-crums-1 \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-optics-crums-2
b: clean build
clean:
	if [[ -f jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store/yarn.lock ]]; then rm jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store/yarn.lock; fi
	if [[ -d jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build ]]; then rm -r jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build; fi
build: build-gradle build-maven
build-maven:
	mvn clean install
build-gradle:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	./gradlew build test
	@for location in $(MODULE_LOCATIONS); do \
		export CURRENT=$(shell pwd); \
		echo "Building $$location..."; \
		cd $$location; \
		make; \
		cd $$CURRENT; \
	done
build-microchip-gradle:
	gradle wrapper
	./gradlew build test
	echo "Building Microchip Project..."; \
	export CURRENT=$(shell pwd); \
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui; \
	make b; \
	cd $$CURRENT;
upgrade:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Upgrading $$location..."; \
		cd $$location; \
		gradle wrapper --gradle-version $(GRADLE_VERSION); \
		cd $$CURRENT; \
	done
build-chip-maker:
	cd jeorg-kotlin-apps/jeorg-microchip-maker && mvn clean install
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui && gradle build test
upgrade-system:
	sudo apt upgrade
	sudo apt update
upgrade-sdk-man:
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
upgrade-gradle: upgrade-system upgrade
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
deps-quick-update: deps-gradle-update deps-plugins-update deps-java-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash

include Makefile.mk

MODULE_LOCATIONS := jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-optics-crums-1 \
					jeorg-kotlin-arrow-optics/jeorg-kotlin-optics-crums-2
first:
	make b

b: clean build

clean:
	if [ -d kotlin-js-store ]; then rm -r kotlin-js-store; fi
	if [ -d jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store ]; then rm -r jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/kotlin-js-store; fi
	if [ -d jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build ]; then rm -r jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui/build; fi
build: build-gradle build-maven
build-maven:
	mvn clean install
build-gradle: clean
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace; \
	./gradlew --stop
	./gradlew clean build test
build-microchip-gradle:
	gradle wrapper
	./gradlew build test
	echo "Building Microchip Project..."; \
	export CURRENT=$(shell pwd); \
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui; \
	make b; \
	cd $$CURRENT;
upgrade:
	for location in $(MODULE_LOCATIONS); do \
  		echo "Upgrading $$location..."; \
	done
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --stacktrace;
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
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash

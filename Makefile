b: build
build: build-gradle build-maven
build-maven:
	mvn clean install
build-gradle:
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui && make b
	cd jeorg-kotlin-arrow-optics/jeorg-kotlin-arrow-optics-gradle-1 && make b
build-chip-maker:
	cd jeorg-kotlin-apps/jeorg-microchip-maker && mvn clean install
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui && gradle build test
ksp-dyescape-health-test:
	cd jeorg-kotlin-arrow-optics/jeorg-ksp-plugin-test && mvn clean install
upgrade:
	cd jeorg-kotlin-apps/jeorg-microchip-maker/jeorg-microchip-maker-gui && gradle wrapper --gradle-version 8.0.1
	cd jeorg-kotlin-arrow-optics/jeorg-kotlin-arrow-optics-gradle-1 && gradle wrapper --gradle-version 8.0.1
upgrade-gradle:
	sudo apt upgrade gradle
	sdk install gradle 8.0.1
	sdk use gradle 8.0.1

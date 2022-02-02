wrapper:
	gradle wrapper
buildw:
	gradle clean build test publishToMavenLocal
upgrade:
	gradle wrapper --gradle-version 7.3.3
upgrade-mac-os:
	brew upgrade gradle
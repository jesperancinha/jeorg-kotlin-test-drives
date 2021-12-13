b: build-maven
build:
	mvn clean install
build-maven:
	mvn clean install -DskipTests
test:
	mvn test
test-maven:
	mvn test
local: no-test
	cp coffee-system/src/test/resources/*.xml /tmp
	mkdir -p bin
no-test:
	mvn clean install -DskipTests

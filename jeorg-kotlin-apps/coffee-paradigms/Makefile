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
run-a:
	java -jar coffee-system/target/coffee-system.jar -it 1 -ud "coffee-system/target/test-classes/employees_example_test_1.xml" -md "coffee-system/target/test-classes/coffemachine_example_test_1.xml" -pre 2 -post 3
build-run-a: b run-a

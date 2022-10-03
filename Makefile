SHELL=/bin/bash

b: build-maven
build:
	mvn clean install
build-maven:
	mvn clean install -DskipTests
build-npm:
	cd jofisaes-image-morpher-js && yarn
test: test-maven
test-maven:
	mvn test
test-npm:
	cd jofisaes-image-morpher-js && npm test
coverage-npm:
	cd jofisaes-image-morpher-js && npm run coverage
security-npm:
	cd jofisaes-image-morpher-js && snyk auth && npm run snyk
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker:
	docker-compose rm -svf
	docker-compose up -d --build --remove-orphans
prune-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker rm {}
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes

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
	cd jofisaes-image-morpher-js && npm run snyk
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
update:
	find . -name "package-lock.json" | xargs rm; \
	find . -name "yarn.lock" | xargs rm; \
	git pull; \
	curl --compressed -o- -L https://yarnpkg.com/install.sh | bash; \
	npm install caniuse-lite; \
	npm install -g npm-check-updates; \
	cd jofisaes-image-morpher-js; \
		yarn; \
		npx browserslist --update-db; \
		ncu -u; \
		yarn
#install-tools:
#	wget https://download.java.net/openjdk/jdk8u41/ri/openjdk-8u41-b04-linux-x64-14_jan_2020.tar.gz -P /tmp
#	tar xf /tmp/openjdk-8u41-b04-linux-x64-14_jan_2020.tar.gz -C
#	ls -d /opt/hostedtoolcache/Java_Adopt_jdk/17.0*/x64/../ | xargs -I {}  mkdir {}/lib
#	ls -d /opt/hostedtoolcache/Java_Adopt_jdk/17.0*/x64/../lib | sudo xargs cp ~/java-se-8u41-ri/lib/tools.jar

wrapper:
	gradle wrapper
buildw:
	./gradlew clean build test jacocoTestReport -i && ./gradlew -i
debug:
	./gradlew clean build test jacocoTestReport -i --stacktrace --debug
	./gradlew --stacktrace --debug
upgrade:
	gradle wrapper --gradle-version 7.3.3
upgrade-mac-os:
	brew upgrade gradle
install-jacococli:
	wget https://search.maven.org/remotecontent\?filepath\=org/jacoco/jacoco/0.8.7/jacoco-0.8.7.zip
	unzip remotecontent\?filepath=org%2Fjacoco%2Fjacoco%2F0.8.7%2Fjacoco-0.8.7.zip
unpack-reports:
	mkdir -p jacoco
	java -jar lib/jacococli.jar report performance-buffered-reader/build/jacoco/test.exec --classfiles performance-buffered-reader/build/libs/*.jar --xml jacoco/jacoco-pbr.xml
	java -jar lib/jacococli.jar report performance-input-test-generator/build/jacoco/test.exec --classfiles performance-input-test-generator/build/libs/*.jar --xml jacoco/jacoco-itg.xml
	java -jar lib/jacococli.jar report performance-jump-search/build/jacoco/test.exec --classfiles performance-jump-search/build/libs/*.jar --xml jacoco/jacoco-pjs.xml

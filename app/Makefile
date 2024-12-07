run-dist:
	@./build/install/app/bin/app -f="plain" src/main/resources/file3.json src/main/resources/file4.json

help:
	@./build/install/app/bin/app -h

clean:
	@./gradlew clean

install:
	@./gradlew clean installDist

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

.PHONY: build

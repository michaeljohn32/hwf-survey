# hwf-survey

## how to build for Docker
mvn clean package
docker build -t hwf-survey --rm --build-arg ARTIFACT_URL=ui/target/hwf-survey.war .
This will generate an image with a name such as adb5d07da2c3

## how to run under Docker
docker run -i -t --rm -p 8080:8080 adb5d07da2c3



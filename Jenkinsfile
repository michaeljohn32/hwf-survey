#!groovy
def buildVersion = null
node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Get some code from a GitHub repository
   git url: 'https://github.com/UM-RAD-hack-2016/hwf-survey.git'

   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.           
   def mvnHome = tool 'M3'

   stage 'Compile'
   // Run the maven compile
   sh "${mvnHome}/bin/mvn clean compile"

   stage 'Test'
   // Run the maven test
   sh "${mvnHome}/bin/mvn test"

   stage 'Package'
   // Run the maven test
   sh "${mvnHome}/bin/mvn package"

   //Build docker image for app and tests in parallel
   stage 'Build Docker Image'
   def mobileSurveyAppImage
   sh "/bin/ls"
   def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
   if (matcher) {
     buildVersion = matcher[0][1]
     echo "Release version: ${buildVersion}"
   }
   matcher = null
   dir('target') {
        sh "cp ../Dockerfile ."
        sh "cp ../ui/target/hwf-survey.war ."
        mobileSurveyAppImage = docker.build "michaeljohn32/hwf-survey:${buildVersion}"
//        if (mobileSurveyAppImage.indexOf("sha256"))
//        {
//          mobileSurveyAppImage = mobileSurveyAppImage.substring(6)
//        }
        container = mobileSurveyAppImage.run("--name hwf-survey-${buildVersion} -p 8081:8080")
    }

   stage 'Publish Docker Image'
        sh "docker -v"
        //use withDockerRegistry to make sure we are logged in to docker hub registry
        withDockerRegistry(registry: [credentialsId: 'docker-hub-michaeljohn32']) {
//            int length = mobileSurveyAppImage.length()
//            mobileSurveyAppImage = mobileSurveyAppImage.substring(6,length)
            mobileSurveyAppImage.push()
        }
   sh "/bin/ls"
   stage 'Build Functional test jar'
   def mobileSurveyFuncImage

        git url: 'https://github.com/UM-RAD-hack-2016/hwf-survey-functional-tests.git'
        sh "${mvnHome}/bin/mvn clean install"
 
//        mobileSurveyFuncImage = docker.build "michaeljohn32/hwf-survey-func:${buildVersion}"
//        withDockerRegistry(registry: [credentialsId: 'docker-hub-michaeljohn32']) {
//            mobileSurveyFuncImage.push()
//        }
    stage 'Run Functional Tests'
    sh " echo 'browser:\n\tvendor: htmlunit' > application.yml"
    sh "java -jar target/hwf-survey-functional-tests.jar"
    }


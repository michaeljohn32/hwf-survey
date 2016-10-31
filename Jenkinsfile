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

   stage 'Check for Security Vulnerabilities'
      sh "${mvnHome}/bin/mvn compile org.owasp:dependency-check-maven:check"

   stage 'Package'
      // Run the maven package step
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
//            if (mobileSurveyAppImage.indexOf("sha256"))
//            {
//              mobileSurveyAppImage = mobileSurveyAppImage.substring(6)
//            }
            container = mobileSurveyAppImage.run("--name hwf-survey-${buildVersion} -p 8080:8080 --link hwf-mysql-prod:mysqlprod")
       }


       sh "/bin/ls"

   stage 'Ensure MySQL Database is Available'
        sh "docker run -i --link hwf-mysql-prod:mysqlprod1 --rm mysql sh -c 'exec mysql -h $MYSQLPROD1_PORT_3306_TCP_ADDR -P3306 -uroot -e \"show databases\"' | grep hwfruns"

   stage 'Ensure Users table is populated'
        sh "docker run -i --link hwf-mysql-prod:mysqlprod1 --rm mysql sh -c 'exec mysql -h $MYSQLPROD1_PORT_3306_TCP_ADDR -P3306 -uroot -e \"use hwfruns; select * from Users;\"'"

   stage 'Build Functional test jar'
        def mobileSurveyFuncImage

        git url: 'https://github.com/michaeljohn32/hwf-survey-functional-tests.git'
        sh "${mvnHome}/bin/mvn clean install"

//        mobileSurveyFuncImage = docker.build "michaeljohn32/hwf-survey-func:${buildVersion}"
//        withDockerRegistry(registry: [credentialsId: 'docker-hub-michaeljohn32']) {
//            mobileSurveyFuncImage.push()
//        }
    stage 'Run Functional Tests'
        sh " echo 'browser:\n     vendor: htmlunit\n\nsurvey:\n     site:\n        url: http://localhost:8080/hwf-survey' > application.yml"
        sh "java -jar target/hwf-survey-functional-tests.jar"
    stage "Clean Docker Image"
        container.stop()
    stage "Publish and Deploy?"
        input 'Do you want to publish and deploy this image?'
    stage "Publish Docker Image"
        sh "docker -v"
        withDockerRegistry(registry: [credentialsId: 'docker-hub-michaeljohn32']) {
                mobileSurveyAppImage.push()
        }
    stage "Deploy the Image"
        sh "docker stop hwf-survey-prod || echo 'No Container deployed'"
        sh "docker rm hwf-survey-prod || echo 'No Container to delete'"
        sh "docker run -d -p 8090:8080 --name hwf-survey-prod 'michaeljohn32/hwf-survey:${buildVersion}'"
  //      sh "cd /etc/ansible/devansible/playbooks && ansible-playbook -i ../test deploy-docker.yml --extra-vars='application_name=hwf-survey docker_image=michaeljohn32/hwf-survey:${buildVersion}'"
}

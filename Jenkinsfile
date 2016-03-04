node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Get some code from a GitHub repository
   git url: 'https://github.com/UM-RAD-hack-2016/hwf-survey.git'

   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.           
   def mvnHome = tool 'M3'

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   sh "${mvnHome}/bin/mvn clean package"
   //stash target/hwf-survey.war

   //Build docker image for app and tests in parallel
   stage 'Build Docker Image'

   def mobileSurveyApp

    //unstash 'jar-dockerfile'
    dir('target') {
        sh "cp ../Dockerfile ."
        mobileSurveyAppImage = docker.build("--rm") "hwf-survey:v1"
        container = mobileSurveyAppImage.run("--name hwf-survey -p 8080:8080")
    }

}

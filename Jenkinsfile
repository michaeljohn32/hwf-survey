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

   stage 'Compile'
   // Run the maven test
   sh "${mvnHome}/bin/mvn test"

   stage 'Package'
   // Run the maven test
   sh "${mvnHome}/bin/mvn package"

   //Build docker image for app and tests in parallel
   stage 'Build Docker Image'

   def mobileSurveyAppImage

    //unstash 'jar-dockerfile'
    dir('target') {
        sh "cp ../Dockerfile ."
        mobileSurveyAppImage = docker.build("--rm") "hwf-survey"
        container = mobileSurveyAppImage.run("--name hwf-survey -p 8080:8080")
    }

}

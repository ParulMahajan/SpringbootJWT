pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "maven3.6.3"
   }

   stages {
       
       stage('Build Base Image') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/ParulMahajan/SpringbootJWT'

			
			// Create Base Imge
			sh 'docker build --tag="mahajan777/base_image:latest" /var/lib/jenkins/workspace/DockerPipeline/Docker/base_image'
         }
      }
       
       
       
      stage('Build SpringBoot Jar') {
         steps {
            
            // Run Maven on a Unix agent.
            sh "mvn clean package -Dmaven.test.skip=true"

            // Copy JAR to Docker artifact
            sh "cp /var/lib/jenkins/workspace/DockerPipeline/target/SpringbootJWT-latest.jar /var/lib/jenkins/workspace/DockerPipeline/Docker/SpringBootJWT_image/artifacts"
            
            // Create SpringBoot Docker Image
            sh 'docker build --tag="mahajan777/springboot_jwt:latest" /var/lib/jenkins/workspace/DockerPipeline/Docker/SpringBootJWT_image'
         }
      }
   }
}

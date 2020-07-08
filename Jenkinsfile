
def PipelineBasePath = "/var/lib/jenkins/workspace/DockerPipeline/Docker"


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
			sh 'docker build --tag="mahajan777/base_image:latest" ${PipelineBasePath}/base_image'
         }
      }
      
      stage('Build Kafka Base Image') {
         steps {
            
			// Create Kafka Base Imge
			sh 'docker build --tag="mahajan777/streaming-base:latest"  ${PipelineBasePath}/Kafka/stream-base'
         }
      }
       
      stage('Build SpringBoot Jar') {
         steps {
            
            // Run Maven on a Unix agent.
            sh "mvn clean package -Dmaven.test.skip=true"

            // Copy JAR to Docker artifact
            sh "cp /var/lib/jenkins/workspace/DockerPipeline/target/SpringbootJWT-latest.jar ${PipelineBasePath}/SpringBootJWT_image/artifacts"
            
            // Create SpringBoot Docker Image
            sh 'docker build --tag="mahajan777/springboot_jwt:latest" ${PipelineBasePath}/SpringBootJWT_image'
            
            // remove untagged none images
            sh 'docker rmi $(docker images -f dangling=true -q)'
         }
      }
   }
}

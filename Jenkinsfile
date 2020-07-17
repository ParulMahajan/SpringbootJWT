



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
			sh 'docker build --tag="mahajan777/base_image:latest"  /var/lib/jenkins/workspace/DockerPipeline/Docker/base_image'
         }
      }
      
      stage('Build Streaming Base Image') {
         steps {
            
			// Create Kafka Base Image
			sh 'docker build --tag="mahajan777/streaming-base:latest"   /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/stream-base'
         }
      }
      
      stage('Build Zookeper Image') {
         steps {
            
			// Create zookeeper Image
			sh 'docker build --tag="mahajan777/kafka-zookeeper:latest"  --no-cache="true" --rm=true /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/zookeper'
         }
      }
      
      stage('Build Kafka Image') {
         steps {
            
			// Create Kafka Node Image
			sh 'docker build --tag="mahajan777/kafka-node:latest"  --no-cache="true" --rm=true /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/kafkaNode'
         }
      }
       
      stage('Build SpringBoot Jar') {
         steps {
            
            // Run Maven on a Unix agent.
            sh "mvn clean package -Dmaven.test.skip=true"

            // Copy JAR to Docker artifact
            sh "cp /var/lib/jenkins/workspace/DockerPipeline/target/SpringbootJWT-latest.jar /var/lib/jenkins/workspace/DockerPipeline/Docker/SpringBootJWT_image/artifacts"
            
            // Create SpringBoot Docker Image
            sh 'docker build --tag="mahajan777/springboot_jwt:latest"  --no-cache="true" --rm=true /var/lib/jenkins/workspace/DockerPipeline/Docker/SpringBootJWT_image'
            
            // remove untagged none images
            sh 'docker rmi -f $(docker images -f dangling=true -q)'
            
           
            
            // Genarate Network
           # sh 'docker network create mynetwork'
         }
      }
   }
}

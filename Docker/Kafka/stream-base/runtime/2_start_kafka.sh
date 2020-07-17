#script to start kafka-node service
#Eg. docker stack deploy -c <path_to_compose_file> <service_name>
#sudo docker stack deploy -c docker-compose-kafka.yaml kafkaz

docker run -d -p 9092:9092 --name kafkaNode --network mynetwork  -v /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/kafkaNode/artifacts/server.properties:/apps/kafka/config/server.properties  mahajan777/kafka-node:latest

docker run -d -p 9093:9092 --name kafkaNode1 --network mynetwork  -v /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/kafkaNode/artifacts/server1.properties:/apps/kafka/config/server.properties  mahajan777/kafka-node:latest

docker run -d -p 9094:9092 --name kafkaNode2 --network mynetwork  -v /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/kafkaNode/artifacts/server2.properties:/apps/kafka/config/server.properties  mahajan777/kafka-node:latest
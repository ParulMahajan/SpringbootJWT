#script to start kafka-node service
#Eg. docker stack deploy -c <path_to_compose_file> <service_name>
#sudo docker stack deploy -c docker-compose-kafka.yaml kafkaz

docker run -d -p 9092:9092 --name kafkaNode --network mynetwork  -v /var/lib/jenkins/workspace/DockerPipeline/Docker/Kafka/kafkaNode/artifacts/server.properties:/apps/kafka/config/server.properties  mahajan777/kafka-node:latest

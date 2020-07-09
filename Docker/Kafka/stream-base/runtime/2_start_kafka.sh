#script to start kafka-node service
#Eg. docker stack deploy -c <path_to_compose_file> <service_name>
#sudo docker stack deploy -c docker-compose-kafka.yaml kafkaz

sudo docker run -d -p 9092:9092 mahajan777/kafka-node:latest

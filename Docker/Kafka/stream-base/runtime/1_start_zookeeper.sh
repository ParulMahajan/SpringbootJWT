#This script will start zookeeper service
#Eg. docker stack deploy -c <path_to_compose_file> <service_name>
#sudo docker stack deploy -c docker-compose-zookeeper.yaml kafkaz


docker run --name zookeper -d -p 2181:2181 --network mynetwork  mahajan777/kafka-zookeeper:latest
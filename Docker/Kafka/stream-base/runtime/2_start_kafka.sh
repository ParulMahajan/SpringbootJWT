#script to start kafka-node service
#Eg. docker stack deploy -c <path_to_compose_file> <service_name>
sudo docker stack deploy -c docker-compose-kafka.yaml kafkaz

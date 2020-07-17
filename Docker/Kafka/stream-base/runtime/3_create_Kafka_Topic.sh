/apps/kafka/bin/kafka-topics.sh --create --zookeeper zookeper:2181 --replication-factor 2   --partitions 2 --topic myKafka

# /apps/kafka/bin/kafka-topics.sh --list --zookeeper zookeper:2181

/apps/kafka/bin/kafka-topics.sh  --describe --zookeeper zookeper:2181 --topic myKafka

# /apps/kafka/bin/kafka-console-producer.sh --broker-list kafkaNode:9092 --topic myKafka

# /apps/kafka/bin/kafka-console-consumer.sh --bootstrap-server kafkaNode3:9092 --topic myKafka  --from-beginning

# /apps/kafka/bin/kafka-topics.sh --zookeeper zookeper:2181 --delete --topic myKafka

# /apps/kafka/bin/zookeeper-shell.sh zookeper:2181 ls /brokers/ids
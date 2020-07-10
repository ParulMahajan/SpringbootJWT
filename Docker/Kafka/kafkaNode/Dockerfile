FROM mahajan777/streaming-base:latest
MAINTAINER Parul  Mahajan


# Expose Kafka
EXPOSE 9092
USER root

COPY artifacts/server.properties /apps/kafka/config/zookeeper.properties

RUN chmod -R 755 /apps/kafka/

RUN ls -l /apps/kafka/bin
RUN ls -l /apps/kafka/config/
RUN cat /apps/kafka/config/zookeeper.properties

RUN pwd

ENTRYPOINT ["/bin/sh", "/apps/kafka/bin/kafka-server-start.sh",  "/apps/kafka/config/server.properties"]
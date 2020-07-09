FROM mahajan777/streaming-base:latest
MAINTAINER Parul  Mahajan


# Expose ZooKeeper
EXPOSE 2181
USER root

RUN ls -l /apps/kafka/bin
RUN ls -l /apps/kafka/config/

ENTRYPOINT ["/apps/kafka/bin/zookeeper-server-start.sh /apps/kafka/config/zookeeper.properties"]
#RUN /apps/kafka/bin/zookeeper-server-start.sh

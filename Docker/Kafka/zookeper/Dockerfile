FROM mahajan777/streaming-base:latest
MAINTAINER Parul  Mahajan


# Expose ZooKeeper
EXPOSE 2181
USER root

COPY artifacts/zookeeper.properties /apps/kafka/config/zookeeper.properties

RUN chmod -R 755 /apps/kafka/

RUN ls -l /apps/kafka/bin
RUN ls -l /apps/kafka/config/
RUN cat /apps/kafka/config/zookeeper.properties

ENTRYPOINT ["/apps/kafka/bin/zookeeper-server-start.sh -daemon /apps/kafka/config/zookeeper.properties"]
#ENTRYPOINT ["/apps/kafka/bin/zookeeper-server-start.sh"]
#RUN /apps/kafka/bin/zookeeper-server-start.sh
#CMD /apps/kafka/bin/zookeeper-server-start.sh -daemon /apps/kafka/config/zookeeper.properties
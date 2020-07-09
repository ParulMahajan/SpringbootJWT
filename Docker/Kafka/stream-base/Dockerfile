FROM mahajan777/base_image:latest
MAINTAINER Parul  Mahajan


#Create required directories and set access rights
USER root

# Define commonly used JAVA_HOME variable
ENV KAFKA_VERSION=2.5.0 SCALA_VERSION=2.12


#Install Kafka
COPY artifacts/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz /tmp/kafka.tgz
RUN mkdir -p /apps
RUN tar xfz /tmp/kafka.tgz -C /apps
RUN rm /tmp/kafka.tgz
RUN mv /apps/kafka_${SCALA_VERSION}-${KAFKA_VERSION} /apps/kafka

#COPY artifacts/server.properties /apps/kafka/config/server.properties
#RUN ls -l /apps/kafka/config/
#RUN cat /apps/kafka/config/server.properties

#Set access rights to regular user
#/var/log/supervisord/ \
RUN mkdir -p /tmp/zookeeper \
			 /tmp/kafka-logs \
			/apps/kafka/logs \
			&& chmod -R 755 /apps/kafka
#	&& chmod -R 755 /var/log/supervisord/ \
#	&& chown -R sequser:seqgrp /apps/kafka \
#			    /var/log/supervisord/ \
#					/tmp/zookeeper \
#					/tmp/kafka-logs \
#					/apps/kafka/logs \
#	&& chmod -R 755 /apps/kafka

RUN ls -l /tmp

#Define work directory of the container
WORKDIR /apps/kafka

# Expose ZooKeeper
EXPOSE 2181

# Expose Kafka
EXPOSE 9092

#switch user
#USER sequser

RUN echo "Kafka version : " ${SCALA_VERSION}-${KAFKA_VERSION} 
#>> /version.txt

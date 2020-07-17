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
COPY artifacts/consumer.properties /apps/kafka/config/consumer.properties
#RUN cat /apps/kafka/config/server.properties
RUN cat /apps/kafka/config/consumer.properties

#Set access rights to regular user
#/var/log/supervisord/ \
RUN mkdir -p /tmp/zookeeper \
			 /tmp/kafka-logs/server1 \
			 /tmp/kafka-logs/server2 \
			 /tmp/kafka-logs/server3 \
			 /tmp/kafka-logs/zookeper \
			/apps/kafka/logs \
			&& chmod -R 755 /apps/kafka/ \
			/tmp/zookeeper \
			/tmp/kafka-logs
#	&& chmod -R 755 /var/log/supervisord/ \
#	&& chown -R sequser:seqgrp /apps/kafka \
#			    /var/log/supervisord/ \
#					/tmp/zookeeper \
#					/tmp/kafka-logs \
#					/apps/kafka/logs \
#	&& chmod -R 755 /apps/kafka

#RUN ls -l /apps/kafka/config/

#RUN ls -l /tmp

#Define work directory of the container
WORKDIR /apps/kafka


RUN echo "Kafka version : " ${SCALA_VERSION}-${KAFKA_VERSION} 
#>> /version.txt

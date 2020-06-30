#Download base image ubuntu 16.04
FROM centos:latest

MAINTAINER Parul  Mahajan

# Update Software repository
#cRUN  yum check-update

#Java installation
#with unlimited strength JCE policy.
COPY artifacts/jre-8u221-linux-x64.tar.gz /opt/jre-8u221-linux-x64.tar.gz

RUN cd /opt  && tar -xvzf jre-8u221-linux-x64.tar.gz &&  chown -R root: /opt/jre1.8.0_221 \
 && alternatives --install /usr/bin/java java /opt/jre1.8.0_221/bin/java 1 \
 && java -version && echo $JAVA_HOME

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /opt/jre1.8.0_221
RUN echo $JAVA_HOME
EXPOSE 8085

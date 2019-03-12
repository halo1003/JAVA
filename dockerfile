# FROM maven

# RUN mkdir -p ~/workspace
# COPY ~/workspace/JavaPipeline/target/demojenkins-0.0.1-SNAPSHOT.jar ~/workspace/demojenkins-0.0.1-SNAPSHOT.jar

# VOLUME [ "/data" ]
#Download base image ubuntu 16.04

FROM ubuntu:16.04

# Update Ubuntu Software repository
RUN apt-get update && apt-get install -y --no-install-recommends apt-utils

#Install maven
RUN apt-get install -y maven
ENV JAVA_HOME /usr/lib/jvm/default-java

#Install Ansible
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install software-properties-common
RUN apt-add-repository -y ppa:ansible/ansible
RUN apt-get update && apt-get install -y --no-install-recommends apt-utils
RUN apt-get install -y ansible
RUN apt-get install sshpass
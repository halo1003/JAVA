# FROM maven

# RUN mkdir -p ~/workspace
# COPY ~/workspace/JavaPipeline/target/demojenkins-0.0.1-SNAPSHOT.jar ~/workspace/demojenkins-0.0.1-SNAPSHOT.jar

# VOLUME [ "/data" ]
#Download base image ubuntu 16.04

FROM ubuntu:16.04

# Update Ubuntu Software repository
RUN apt-get update && apt-get install -y --no-install-recommends apt-utils
RUN apt-get install -y curl

#Install maven
RUN apt-get install -y maven
ENV JAVA_HOME /usr/lib/jvm/default-java

#Install & config git
RUN apt-get install -y git-core
RUN git config --global user.name "halo1003"
RUN git config --global user.email "do.toan95@gmail.com"

#Install openssh
RUN apt install -y openssh-server
RUN apt-get install -y sshpass

#Install Ansible
RUN \
  apt-get update && \
  apt-get install -y software-properties-common && \
  apt-add-repository ppa:ansible/ansible && \
  apt-get update && \
  apt-get install -y --force-yes ansible

RUN mkdir /ansible
RUN mkdir ~/.ssh
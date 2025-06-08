FROM eclipse-temurin:21-alpine

ENV LANG=C.UTF-8

COPY entrypoint.sh /root/

WORKDIR /root

RUN apt update
RUN apt-get install wget -y
RUN wget https://dlcdn.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp
RUN wget https://download.java.net/openjdk/jdk8u41/ri/openjdk-8u41-b04-linux-x64-14_jan_2020.tar.gz -P /tmp

RUN tar xf /tmp/apache-maven-*.tar.gz -C /opt
RUN tar xf /tmp/openjdk-8u41-b04-linux-x64-14_jan_2020.tar.gz -C /opt

RUN cp /opt/java-se-8u41-ri/lib/tools.jar /usr/local/openjdk-17/../lib/

RUN ln -s /opt/apache-maven-3.6.3 /opt/maven
RUN touch /etc/profile.d/maven.sh

RUN echo "export M2_HOME=/opt/maven" >> /etc/profile.d/maven.sh
RUN echo "export MAVEN_HOME=/opt/maven" >> /etc/profile.d/maven.sh
RUN echo "export PATH=/opt/maven/bin:${PATH}" >> /etc/profile.d/maven.sh
RUN echo "source /etc/profile.d/maven.sh" >> /root/.bashrc

ENTRYPOINT ["/root/entrypoint.sh"]

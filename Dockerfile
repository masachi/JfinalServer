FROM maven:3.5-jdk-8
RUN apt-get update && apt-get install --yes tomcat8
RUN mvn compile
RUN mvn package
ADD target/graduate.war /var/lib/tomcat8/webapps/
CMD ["service tomcat8 run"]

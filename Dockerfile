FROM openjdk:17
EXPOSE 8756
ADD target/apiinjava.jar apiinjava.jar 
ENTRYPOINT ["java","-jar","/apiinjava.jar"]
FROM azul/zulu-openjdk-alpine:11
EXPOSE 8101
ADD target/lms-user-service-1.0.jar lms-user-service-1.0.jar 
ENTRYPOINT ["java","-jar","/lms-user-service-1.0.jar"]
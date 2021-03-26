FROM openjdk:14-alpine
COPY target/annotation-value-*.jar annotation-value.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "annotation-value.jar"]
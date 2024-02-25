FROM openjdk:17
ARG JAR_FILE=./build/libs/shop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# mariadb 10.6 버전 이미지 사용.
FROM mariadb:10.6

ENV MYSQL_ROOT_PASSWORD 123456
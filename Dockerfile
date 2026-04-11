FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

FROM tomcat:11.0.18-jdk17-temurin-noble
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
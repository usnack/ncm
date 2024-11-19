FROM gradle:jdk21-corretto AS build-stage
WORKDIR /app
COPY . /app
RUN ./gradlew bootJar
CMD ["sh"]

FROM amazoncorretto:21.0.5
COPY --from=build-stage /app/build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

# copy pom first for dependency cache
COPY pom.xml .

RUN mvn -B -q -e -DskipTests dependency:go-offline

# copy source
COPY src ./src
COPY testng.xml ./testng.xml

CMD ["mvn", "test"]

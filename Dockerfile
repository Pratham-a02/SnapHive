# Use Java 21
FROM eclipse-temurin:21-jdk

# Create and set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Download Maven dependencies
RUN ./mvnw dependency:go-offline

# Copy entire project and build
COPY . .
RUN ./mvnw clean install -DskipTests

# Run the jar
CMD ["java", "-jar", "target/SnapHive-0.0.1-SNAPSHOT.jar"]

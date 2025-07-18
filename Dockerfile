# Use Java 21
FROM eclipse-temurin:21-jdk

# Create and set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Make mvnw executable (💥 important fix)
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the project and build
COPY . .
RUN ./mvnw clean install -DskipTests

# Run the built jar
CMD ["java", "-jar", "target/SnapHive-0.0.1-SNAPSHOT.jar"]

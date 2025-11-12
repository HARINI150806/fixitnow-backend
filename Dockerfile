# Use a stable and Render-compatible Java image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot app (skip tests to speed up)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 for Render
EXPOSE 8080

# Run the compiled JAR file
ENTRYPOINT ["java", "-jar", "target/*.jar"]

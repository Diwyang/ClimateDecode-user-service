# Use the Eclipse Temurin OpenJDK 17 base image
FROM eclipse-temurin:17-jdk

# Create a volume for temporary files
VOLUME /tmp

# Add the JAR file from the target directory to the container
ADD target/*.jar app.jar

# Set the environment variable for Java options
ENV JAVA_OPTS=""

# Define the entry point for the container
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
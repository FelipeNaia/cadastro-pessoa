mvn clean package -DskipTests
docker build -t cadastropessoa .
docker-compose up
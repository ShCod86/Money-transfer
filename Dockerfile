FROM openjdk:19-jdk-alpine3.16

EXPOSE 5500

ADD target/MoneyTransferApp-0.0.1-SNAPSHOT.jar transfer.jar

CMD ["java", "-jar", "transfer.jar"]

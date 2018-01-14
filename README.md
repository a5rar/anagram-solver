# Anagram-solver
Anagram solver for  MOJ tech test

Checkout repository
cd into directory
run mvn clean install

in order to run :
mvn spring-boot:run 

in order to run it on other boxes copy the target/anagram-solver-0.0.1-SNAPSHOT.jar file to host machine 
and run 
java -jar anagram-solver-0.0.1-SNAPSHOT.jar

in order to override property configurations

java -jar anagram-solver-0.0.1-SNAPSHOT.jar -Dspring.config.location=<path to your application.propertties>

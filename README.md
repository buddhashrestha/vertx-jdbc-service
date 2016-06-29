# vertx-jdbc-service
This is a simple service that accepts request and then runs a database service

Inorder to run it, firstly pull the project and then you can run the project in two different ways:

1. If you want it to run from the jar, you need to go to the project home directory and hit mvn clean install and go to target directory and run the jar 
2. If you want to run from the IDE, setup an application in the IDE with Main Class as : 'io.vertx.core.Launcher'.

Also, you need to hit a post request : http://localhost:8089/execute

BTW, my sample procedure was just a sleeping procedure : 

CREATE DEFINER=`root`@`localhost` PROCEDURE `sleeping`()
BEGIN

select SLEEP(30);

select * from anytableName;

END

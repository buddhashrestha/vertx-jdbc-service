# vertx-jdbc-service
This is a simple service that accepts request and then gives runs the database service

Inorder to run it, you need to go to the project home directory and hit mvn clean install and go to target directory and run the jar.
Also, you need to hit a post request : http://localhost:8089/execute

BTW, my sample procedure was just a sleeping procedure : 

CREATE DEFINER=`root`@`localhost` PROCEDURE `sleeping`()
BEGIN

select SLEEP(30);
select * from anytableName;
END

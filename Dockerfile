FROM jetty
ADD target/Spring-Boot-CRUD-Application-0.0.1-SNAPSHOT.war /var/lib/jetty/webapps/root.war
EXPOSE 8080
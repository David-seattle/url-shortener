Overview
========
    This project is built with Maven 3 and uses Spring Boot to create an executable JAR that launches an
    embedded Jetty server and runs the service.

    The command to run the service is: java -jar url-shortener-0.0.1-SNAPSHOT.jar

Requirements
============
    The service must be run with Java 8.  Also, PostgreSQL must be installed and running on the local machine,
    and have the necessary table(s) created.  The create-table scripts can be found within the jar under
    sql\create-tables.sql

Configuration
=============
    At the root of the jar is a properties file named application.properties, which contains configurable settings
    such as the PostgreSQL location and credentials, the location of log files, and the domain name the service
    will use when generating URLs.
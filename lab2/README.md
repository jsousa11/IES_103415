# Exercise 2.1

A Servlet is a Java class that runs at the server, handles (client) requests, processes them, and reply with a response When an application running in a web server receives a request, the Server hands the request to the Servlet Container which in turn passes it to the target Servlet.

### Running Tomcat server

Inside < path to Tomcat >/bin folder:

```
chmod 744 setup.sh
chmod 744 catalina.sh
./startup.sh
```

We can see the server is running by:

```curl -I 127.0.0.1:8080```

OR access the default page in the browser: http://localhost:8080

### Give manager permissions

In tomcat-users.xml inside apache-tomcat/conf:
````
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="dxogo" password="dxogo" 
roles="manager-gui,manager-script"/>
````

### To Start/Stop TomCat

Inside < path to Tomcat >/bin folder:
````
./catalina.sh stop
./startup.sh
````

### Create a web application and deploy it into Tomcat
````
mvn archetype:generate -DgroupId=com.dxogo -DartifactId=tomcat_webapp -DarchetypeArtifactId=webapp-javaee7 -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeVersion=1.1 -DinteractiveMode=false
````

### Exercise h) Important information to retain
- We create Java project on com.dxogo, execute '$mvn package' and deploy the .war file in /target in the TomCat Manager (we might have to undeploy the old version first). After that we open the web server in:

```
<http://localhost:8080//<webapp_name>/<server_url>
```

- webapp_name : composed by ArtifactId+version (in our case it is tomcat_webapp-1.0-SNAPSHOT)

- server_url : defined in the Java app on : @WebServlet(name = "server_name", urlPatterns = {"/server_url"})

- In this case the URL is MyFirstServlet

# Exercise 2.2

Most times, it is easier to write an application and a jetty server together rather writing an application and deploying a WAR file on jetty server. It saves time and keeps things simple with application handling.

### Steps

1. Create a Maven Project
2. Modify pom.xml to add some dependencies like jetty-server and jetty-servlet
3. Create Java project in src->main->java
4. You can run this project in eclipse by executing the Java code. This runs an HTTP server on port 8680.

# Exercise 2.3
__Spring Boot__ is a convention-over-configuration addition to the Spring platform, useful to get started with minimum effort and create stand-alone, production-grade applications.

__Spring Initializr__ templates contain a collection of all the relevant transitive dependencies that are needed to start a particular functionality and will simplify the setup of the POM. Be sure to add the Spring web dependency.

Running ```./mvnw spring-boot:run``` and access your browser at http://localhost:8080/ (8080 if default port) you should retrieve a page produced by Spring Boot.

### To change the port
- On main/java/resources/application.propreties write ```server.port=XXXX```

On GreetingController.Java we define: ```@GetMapping("/greeting")```

This ensures that HTTP GET requests to /greeting are mapped to the greeting() method and we can see our written code in http://localhost:8080/greeting in this specificic case.

# Exercise 2.4

### Creating a Web Service (REST API)

Java Enterprise allows you to create complex and robust applications for different scenarios.
In this exercise we created a REST API that offers random quotes from movies.

## Review questions

### A. What are the responsibilities/services of a “servlet container”?

A servlet container manages and is responsible for the lifecycle of servlets, and maps URL to a particular servlet to ensure the requester has the correct access-rights.


### B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

Spring Boot uses the MVC pattern in which the application is divided in 3 components:

- __Model:__ middleware between the Controller and the View, which is being controlled and updated by the Controller.
- __Controller:__ after an HTTP request is made, it receives it and processes it.
- __View:__ component that allows the user to see the processed request.

### C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?

It provides all the default dependencies so that we can run the Spring Boot application without adding any other dependencies.


### D. Which annotations are transitively included in the @SpringBootApplication?

- @EnableAutoConfiguration
  - enables Spring Boot to auto-configure the application context. Therefore, it automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us.
- @ComponentScan
  - tell Spring the packages to scan for annotated components. @ComponentScan also used to specify base packages and base package classes using thebasePackageClasses or basePackages attributes of @ComponentScan.
- @Configuration
  - indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime



### E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words
__Use logical nesting on endpoints__

When designing endpoints, it makes sense to group those that contain associated information. That is, if one object can contain another object, you should design the endpoint to reflect that.

__Handle errors gracefully and return standard error codes__

To eliminate confusion for API users when an error occurs, we should handle errors gracefully and return HTTP response codes that indicate what kind of error occurred. This gives maintainers of the API enough information to understand the problem that’s occurred.

__Maintain good security practices__

Most communication between client and server should be private since we often send and receive private information. Therefore, using SSL/TLS for security is a must.

__Versioning our APIs__

We should have different versions of API if we’re making any changes to them that may break clients.

__Cache data to improve performance__

We can add caching to return data from the local memory cache instead of querying the database to get the data every time we want to retrieve some data that users request.
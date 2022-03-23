# Development steps
- Set up the parent maven project
  - Create an empty maven project (the `src` folder is not necessary, delete it)
  - Add required dependencies and plugins in `pom.xml` file.
- Set up the child modules
  - Each child module is a stand alone Spring Boot project (application).
  - Set up them individually
  - Expose the APIs which can be invoked by microservices in the system

# Points
- Two ways to call a REST API in Spring Boot
  - Use `RestTemplate` (will be deprecated in future)
  - Use `WebClient`: reactive programming. But you can still use `block()` to act sequentially
- Better use a wrapper class to wrap the return object of an API (if it is a collection, e.g. a list)
- Spring Cloud uses client side discovery (using a discovery server)
  - Client calls discovery server to get the URLs of the microservice
  - The actual call happens in the client
- Setting up Eureka server
  - Eureka server is a separate Spring Boot application, we should add the `spring-cloud-starter-netflix-eureka-server` artifact to the project (to use the netflix Eureka library) (add dependency in `pom.xml` file).
  - In addition to `@SpringBootApplication`, we also add `@EnableEurekaServer` annotation to tell Spring Boot that this project is used as an eureka server.
- Setting up Eureka client
  - Add `spring-cloud-starter-netflix-eureka-client` artifact dependency in `pom.xml` file.
  - Add `@EnableEurakaClient` to the Spring Boot application (optional if we have the `spring-cloud-starter-netflix-eureka-client` dependency on the classpath.)
  - Application properties configuration
    - Configure the application name (to be registered with the eureka server)
    - Configure where to register this client to the eureka server, for example
      ```yml
      eureka:
        client:
          serviceUrl:
            defaultZone: http://localhost:8761/eureka/
      ```
  - Add `@LoadBalanced` annotation to the `RestTemplate` bean in the project (that are used to invoke APIs provided by other microservices)
  - When using the `RestTemplate`, we do not provide the actual url for the API, instead, we use the name of the microservice to replace the url. What happens behind the scene is that the Eureka client will ask Eureka server to give the url of the corresponding microservice and then the client performs the API call using the actual url (client cache this result, the refresh time is 30 seconds).
  - The microservice url is hard-coded. We can put it into a property file though.


- Set Java system properties via command line
  - You can use `java -D` to specify the system property. For example, the server port: `java -Dserver.port=8079`. In windows terminal, you need to escape `.` character using double quotation: `java -Dserver"."port=8079`
# References
- [Can't connect to Eureka server (ConnectException)](https://stackoverflow.com/questions/56114871/cannot-connect-to-eureka-server-exception-java-net-connectexception-connectio)
- [Configure eureka server and client](https://stackoverflow.com/questions/45615866/changing-default-port-of-eureka-server-using-spring-cloud)

# TODOs
- [x] Create get user movie ratings API (input: userId, output: a list of movies of this particular user, wrapped in an object)
  - [x] Create a wrapper class for the list of user ratings
  - [x] Create the API (return some random dummy data)
- [x] Service discovery
  - [x] Set up Eureka server

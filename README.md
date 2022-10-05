# Engineering Manager Assessment

This is the response to the Spothero Assignment located [here](https://github.com/spothero/eng-mgr-take-home-challenge).

Defined assignment is to write an application using a defied PostressSQL database defined inside a docker container.

## Your task

We ask you to create an API project on top of this provided database schema. You can use any language, any framework you're confident with.

The application has to expose two `GET` endpoints and one `POST`.

1. `GET` all active users

`curl http://localhost:3000/v1/users` should return a JSON like this (we only captured the first record, your solution should return 10 records):

```json
[
  {
    "id": 1,
    "first_name": "Radchelle",
    "last_name": "Haggerty",
    "email": "RachelleTHaggerty@rhyta.com"
  },
  ...
]
```

2. `GET` all worked_hours for users

The following curl request `curl http://localhost:3000/v1/users/1/worked_hours` should return 6 records in a format like this:


```json
[
  {
    "id": 1,
    "date": "2021-01-01",
    "hours": "3.9"
  },
  {
    "id": 1,
    "date": "2021-01-04",
    "hours": "4.134"
  },
  ...
]
```

3. `POST` a new worked_hour record

By using the following curl request:

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"date": "2021-01-11","hours":5.24}' \
  http://localhost:3000/v1/users/1/worked_hours
```

a new `worked_hour` record is inserted into the database.

## Bonus Points

Although it's not required, if you have time, we would love to see:

* Automated tests for your solution,
* A Dockerfile to run the API project in Docker, maybe expanding our [docker-compose.yml](/docker-compose.yml) with it.

## What I did

I write the application in Java 17 using the Spring Boot framework. I did both the defined assignment and the bonus points portion.

- I wrote tests for both the web layer and the data layer.
- I used [testcontainers](https://www.testcontainers.org) to hold and initialize a postgress DB instance embedded inside a docker container that exists for the duration of the test.
- I used the [Maven Jib plugin](https://github.com/GoogleContainerTools/jib) to provide build support for creating a docker container as part of the build process. This removes the need for a separate docker file and means that we can use a single maven build file for all parameterization (and overrides).
- I embedded Swagger Open Api support to create an Open API page so that you can execute the REST Api endpoint via the [swagger page](http://localhost:3000/swagger-ui/index.html)

### To Build the Application

Running the following command, will execute all tests before it builds an application jar. If you are runing this in Jetbrains Intellij then you will see an executable icon next to the command and can execute it from this page.

```
{
   mvn clean package
}
```

### To Run the Application via Maven

Run this command from the Git root directory

```
{
   mvn spring-boot:run
}
```

Then open this [page to see the Rest endpoints](http://localhost:3000/swagger-ui/index.html)

### To run the application as a jar

The shaded jar (all in one jar) is located in the target directory (if you build with mvn clean package). Please use a JDK version >= 17

```
{
   java -jar target/engmgr-0.0.1-SNAPSHOT.jar
}
```

Then open this [page to see the Rest endpoints](http://localhost:3000/swagger-ui/index.html)

### To Build and Install the docker image on docker hub

Note that this will install to [Dockerhub](https://hub.docker.com). It also requires that you login to docker hub (docker login), or have your docker credentials in your local keychain. If you want to use another registry(e.g Amazon ECS, or Google), then you will have to replace the section "registry.hub.docker.com/larry13767/engrmgr-java-jib" with the appropriate reference to it.

```
{
   mvn compile jib:build -Dimage=registry.hub.docker.com/larry13767/engrmgr-java-jib
}
```

### To run as a docker container 

If you build this application as a docker image and installed on docker hub, then copy the docker-compose.yml file to your original directory and run it.

```
{
   docker-compose up
}
```

Then open this [page to see the Rest endpoints](http://localhost:3000/swagger-ui/index.html)



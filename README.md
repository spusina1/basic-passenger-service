# Basic Passenger Service

## ERD
Following entity relationship diagram shows the relationships of entity sets stored in a database.
</br>

![ERD](https://github.com/spusina1/basic-passenger-service/blob/8228545a353813fb7d65c2867ae9dc90d8462cda/src/main/resources/img/basic-passenger-service-erd.png)

## Functionalities
* Creating passenger
* Retrieving single passenger
* Retrieving passenger by filter(firstName, lastName, passportId, address, phoneNumber, email)
* Updating passenger
* Deleting passenger
</br>

* Creating ticket
* Retrieving ticket/s
* Updating ticket
* Deleting ticket

</br>

* Creating route
* Retrieving route/s
* Updating route
* Deleting route

</br>

* Creating company
* Retrieving company/s
* Updating company
* Deleting company


## Architecture Flow
![ArhitectureFlow](https://github.com/spusina1/basic-passenger-service/blob/1d742989dd19f1b85cc36753a59130aa7d85426b/src/main/resources/img/arhitecture.png)


## How To Use :wrench:

To clone and run this application, you will need [Git](https://git-scm.com), [Java](https://www.oracle.com/java/technologies/javase-downloads.html),
[Maven](https://maven.apache.org/download.cgi) and [PostgreSQL](https://www.postgresql.org/download/).

```bash
# Clone this repository
$ git clone https://github.com/spusina1/basic-passenger-service
```

```bash
# Go into the root directory
$ cd basic-passenger-service
```

```bash
# Add following environment variables in application.yml file:
JDBC_DATABASE_URL
JDBC_DATABASE_USERNAME
JDBC_DATABASE_PASSWORD
```

```bash
# Run the server
$ mvn spring-boot:run
```

## Swagger Documentation
To see API documentaton which contains instructions about how to effectively use and integrate with API use this link:
[Swagger Documentation](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
(Make sure your app is running!)

## Postman Collection
To test the endpoints, download the postman collection from the following link [Postman Collection](https://drive.google.com/file/d/1eBnYuz8s4c2ze-JIsdRESs6QZXshaAXP/view?usp=sharing) (Make sure your app is running!)

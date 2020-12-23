# Car rental
CRUD application realizing car rental management system functionality.


## Table of Contents
- [Prerequisites](#prerequisites)
- [Getting Started](#getting_started)

## Prerequisites <a name = "prerequisites"></a>

`java 11` https://www.oracle.com/pl/java/technologies/javase-downloads.html
<br/>`docker with docker-compose` https://docs.docker.com/get-docker/

## Getting Started (Ubuntu) <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Installing

Cloning project:
```
$ cd <place-you-want-to-store-car_rental>
$ git clone https://github.com/Konrad-Abramowski/Car_rental.git
$ cd Car_rental
```

Running postgreSQL database on localhost:5432:
```
$ sudo docker-compose up -d
```
Running the application:
```
$ ./mvnw javafx:run
```


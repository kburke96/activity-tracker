# Activity Tracker

This project started as a way for me to learn some Spring Boot features such as Spring Batch and Spring Security. It has developed a little further, now containing a React frontend and PostgreSQL database. It has also served as a way for me to take baby steps into Docker and microservices.

The project exposes my activity data (runs, cycles, hikes...) as a REST API. The data is stored in Postgres and the API is consumed by a React frontend.

## Features

* Spring Boot 2.5.3
* Spring Security, OAuth 2.0 and JWT 
* Spring Data JPA & PostgreSQL
* Maven 3.8
* Lombok
* React JS
* Docker & docker-compose

![CodeQL Analysis](https://github.com/kburke96/activity-tracker/actions/workflows/codeql-analysis.yml/badge.svg)
![SonarCloud](https://github.com/kburke96/activity-tracker/actions/workflows/build.yml/badge.svg)


## Running with docker-compose

1. Clone the repository
```bash
git clone https://github.com/kburke96/activity-tracker.git
```

2. Use docker-compose to start a DB service and a backend service
```bash
docker-compose up --build
```
This will start a Postgres database container initialised with some data, and a Spring Boot container which makes the REST API available on localhost:8080.


## Warnings

* OAuth2.0 and JWT have been recently added to the backend - the React frontend has not yet been updated to deal with this change.

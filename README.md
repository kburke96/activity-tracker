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




## Installation

1. Clone the repository
```bash
git clone https://github.com/kburke96/activity-tracker.git
```

2. Use Maven to run the Spring Boot application. This starts the rest API on port 8080
```bash
./mvnw spring-boot:run
```

3. In a separate terminal, run the React frontend on port 3000
```bash
cd frontend
npm start
```

## Warnings

* The above instructions to run the Spring Boot app will only work on an environment where Postgres is installed. I am working on creating a Docker image of the database layer at the moment.
* OAuth2.0 and JWT have been recently added to the backend - the React frontend has not yet been updated to deal with this change.

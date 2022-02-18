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
* GitHub Actions for CI/CD

![CodeQL Analysis](https://github.com/kburke96/activity-tracker/actions/workflows/codeql-analysis.yml/badge.svg)
![SonarCloud](https://github.com/kburke96/activity-tracker/actions/workflows/build.yml/badge.svg)


## Running locally with docker-compose

**NOTE:**
These steps assume you have a recent version of Docker and docker-compose installed on your system.


**1.** Clone the repository
```bash
git clone https://github.com/kburke96/activity-tracker.git
```

**2.** Use docker-compose to start a DB service and a backend service
```bash
docker-compose up --build
```
This will start up 3 layers: database, api and frontend. 

The PostgreSQL database layer will be initialised with some seed data. The API later is a Spring Boot REST API. The frontend layer is a React application.

The REST API is available on port 8080. The frontend is available on port 3000.


## GitHub Actions for CI/CD

This project utilises GitHub Actions to create a CI/CD pipeline, of sorts.

At the moment, the flow goes like this:

1. A commit is made to the main branch
2. The workflow defined in .github/workflows/build.yml is kicked off, which:
    * Checks out the repo
    * Builds the activity-tracker Java API, and upload to SonarCloud for analysis
    * Builds a Docker image of activity-tracker and pushes to DockerHub under user kburke96
    * Builds a Docker image of activity-tracker-ui and pushes to DockerHub under user kburke96
    * Caches Docker layers in order to speed up future builds


## Future enhancements & ToDo
* Create a stats service which will enable a user to query for activities by type, duration, in time range, etc.
* Set up a Kubernetes cluster on a cloud provider and enhance the CI/CD pipeline to allow automated deployment to the cluster using Helm chart.
* Learn about Swagger and document available API endpoints.
* Explore service registry possibilities (Eureka), investigate how these are implemented/interact with a K8s cluster. 
* Add Spring Boot actuator to monitor REST API.

### Reference Material
This is a list of tutorials, blog posts, StackOverflow answers etc. that I found useful for different aspects of this project. This is a living list and will be appended to as the project progresses.

- GitHub Actions setup: https://bullyrooks.com/index.php/2022/01/02/kubernetes-application-hosted-in-the-cloud/
- Docker layer caching: https://evilmartians.com/chronicles/build-images-on-github-actions-with-docker-layer-caching

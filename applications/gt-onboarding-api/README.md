# Golden Tokens Onboarding API

Application to onboarding new players

## Requirement

 - Docker
 - Java 18

## Running Instructions

### 1st Option: docker-compose

First is necessary to build the application locally with maven, executing command below:

```shell
./mvnw clean install
```

Will be create a `target` folder with `jar` file, then build docker image:

```shell
docker build -t gt-onboarding-api .
```

Now you can just run docker-compose-local file.

```shell
docker-compose -f local-deploy.yml up
```

When docker-compose log for localstack finish with message below you are ready to make requests.
```
Done to create GTCustomers successfully!
```

### 2nd Option: locally with Maven

First you need to run docker-compose with dynamo doing command below:

```shell
docker-compose up --build
```

Then using maven wrapper, use command below:

```shell
./mvnw clean spring-boot:run
```

## Documentation

Using OpenAPI pattern `gt-onboarding-api` have a swagger available in our default port:

[swagger-url](http://localhost:37001/swagger-ui/)




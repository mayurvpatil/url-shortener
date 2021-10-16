# URL Shortener

## Prerequisites

- Docker 
- Java 8

## How to use

### With Docker :

- ```docker pull mayurvpatil/url-shortener:2.0.0```
- ```docker run -p 8080:8080 mayurvpatil/url-shortener:2.0.0```

[DockerHub image link](https://hub.docker.com/repository/docker/mayurvpatil/url-shortener)

### Local setup:
- Clone code ```git clone https://github.com/mayurvpatil/url-shortener.git```
- Build maven project using ```mvn clean install```
- Run the project using ```java -jar target/url-shortener-0.0.1-SNAPSHOT.jar```

### API Documentation

- Open http://localhost:8080/swagger-ui.html to see endpoints.

### API Details

- Create Short URL :<br>
http://localhost:8080/url-shortener<br>

```
Request body
{
    "longUrl": "http://www.google.com"
}

Response body:

{
    "redirectUrl": "http://localhost:8080/url-shortener/253d142703"
}
```

- Use Short URL :<br>
  Hit reponce redirectUrl in any browser to open original URL.






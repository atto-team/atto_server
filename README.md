# Specification

1. Core
   - Kotlin 1.3
   - Spring Framework 5+
   - Spring Boot 2+
   - Spring Web Mvc

2. Persistence Framework
   - JPA
   - Spring Data JPA

3. Database Engine
   - MySQL 8

4. Test Framework
   - Junit5
   - mokito

5. Etc
   - kapt
   - gradle 5.4 version


# Test
```
(window)
gradlew test

(macOs, Linux)
./gradlew test
```


# 도커를 이용한 Mysql 로컬에 설치

```bash
docker pull mysql:8
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:8
```

# 환경변수 세팅

```
GMAIL_USERNAME 
GMAIL_PASSWORD
FIREBASE_SERVER_KEY
GOOGLE_APPLICATION_CREDENTIALS
  - export GOOGLE_APPLICATION_CREDENTIALS="/home/user/Downloads/service-account-file.json"
``` 

# Build & QueryDSL Generate

```
gradlew :nimontoy-api:build
```
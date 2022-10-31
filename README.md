# BookInfoJPA
borrow and return a book

ref 1. [[로켓 학습] 스프링부트 CRUD REST API (JPA, MySQL, Gradle)](https://covenant.tistory.com/243) 

ref 2. [[spring 토이프로젝트] REST API CRUD 구현하기-1. 프로젝트 생성, Model, Repository](https://velog.io/@rg970604/spring-%ED%86%A0%EC%9D%B4%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-REST-API-CRUD-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0)

ref 3. [[Git] GitHub README 작성법 (마크다운, 이미지, Gif, 코드 블록 사용법)](https://coding-factory.tistory.com/620)

![image](https://user-images.githubusercontent.com/91974804/198932459-55feebfa-50f4-456b-a923-c722747295f4.png)


+ Java 11
  + Spring Boot
    + Spring Boot Start data JPA
    + Spring Boot Start web
    + lombok
    + Postgresql
    + Swagger2 2.9.2
    + Swagger UI 2.9.2
  + Gradle
+ STS 4


###### ignore된 application.properties 내용
```
# swagger 에러 해결 코드
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

#database (postgresql)
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://[ip]:[port]/[db-name]
spring.datasource.username=[username]
spring.datasource.password=[password]

spring.jpa.show-sql=false 
spring.jpa.hibernate.ddl-auto=update
```

![image](https://user-images.githubusercontent.com/91974804/198935671-905b2248-2c88-4435-b3a7-ea1b5966a963.png)

구조


1. JPA 배운거 클론 코딩 해봤음
2. 생각보다 나쁘지 않게 완성했음
3. ref 1 에서 보완한 점은 도서 대여를 약간 응용해서 도서 반납도 추가해봤음
4. LendStatus enum과 LibraryService, LibraryController에 반납 상태, 서비스, 컨트롤러만 추가했음
5. JPA, 기능 설명은 ref에서 잘 되어있으니 참조할 것
6. 마크다운도 약간 배우고 나니 쓸만함을 느낌
7. STS, Git 연동은 아직도 불편함





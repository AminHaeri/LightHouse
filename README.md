<!--suppress HtmlDeprecatedAttribute -->

<h1 align="center">
  Hexagonal Architecture, DDD & TDD in Spring
</h1>

<p align="center">
    <a href="#"><img src="https://img.shields.io/badge/Spring-5.4-purple.svg?style=flat-square&logo=Spring" alt="Spring 5.4"/></a>
</p>

<p align="center">
   Example of a <strong>Spring application using Domain-Driven Design (DDD) and <br /> 
   Test Driver Development (TDD) principles</strong> keeping the code as simple as possible.
  <br />
  <br />
</p>

## 🚀 Environment Setup

This project is made with [SpringBoot][1] 2.7.2.

### 🐳 Needed tools

1. Java 11 or higher;
2. Gradle
3. and the [usual Spring application requirements][2].
6. Clone this project: `git clone https://github.com/AminHaeri/LightHouse.git`

### 🔥 Application execution

1. Run docker compose: `docker compose up -d`.
2. build and project `./gradlew bootRun`

### ✅ Tests execution

1. Execute tests: `gradlew clean test`

### 🎯 Hexagonal Architecture

This repository follows the Hexagonal Architecture pattern. Also, it's structured using `modules`.
With this, we can see that the current structure of a Bounded Context is:

```scala
$ tree -L 5 src
    
user-management // The module for user managment
├── user.application // The applicat and domain layer of the user-managment module
│   └── port // Inside the application layer all is ports in and out
│       └── in // Inside ports with Coammnd and Query alogside of usecases
│           ├── command
│               └── RegisterUserCommand.java
│               └── UserAuthInfoCommand.java
│           ├── query
│               └── UserAuthInfoQuery.java
│           ├── usecase
│               └── RegisterUserUseCase.java
│       └── out // Outside ports with Coammnd and Query
│           ├── command
│               └── AddUserCommand.java
│               └── UserInfoCommand.java
│           ├── UserPort.java
│       └── service // Contains all adpaters as service
│           ├── UserAuthService.java // Adapter for inside port
│           ├── UserRegistrationService.java // Adapter for inside port usecase
├── user.web // The layer web infrastructure of our app
│   └── controller
│       ├── UserRegistrationController.java
│   └── dto
│       ├── requset
│           └── RegisterUserRequestDTO.java
│       ├── response
│           └── RegisterUserResponseDTO.java
│   └── middleware
│   └── mapper
├── user.persistence // The layer persistence infrastructure of our app
│   └── entity
│       ├── UserEntity.java
│       ├── AccountEntity.java
│       ├── RoleEntity.java
│   └── repository
│       ├── UserRepository.java
│       ├── AccountRepository.java
│       ├── RoleRepository.java
│   └── seed
│   └── mapper
└── gradlew
```

[1]: https://docs.spring.io/spring-boot/docs/2.7.2/reference/html/

[2]: https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/html/getting-started-system-requirements.html

[3]: https://Spring.com/doc/5.4/setup/web_server_configuration.html
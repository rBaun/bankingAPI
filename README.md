# Introduction
This is a hobby project for developing the backend part of a banking application. The goal is to make something that can be released and maintained over time, such that features can be implemented as I see fit or when I have the time to do so.

## Getting started
To get started, you need to visit the [Swagger UI](https://bankingapi-5ruj.onrender.com/api/swagger-ui/index.html) to test the endpoints.  
You need a valid token to be able to use the endpoints. You can get a token by using the Auth API endpoints available from the Swagger UI. When using the Swagger UI, you need to add the token to the `Authorize` button in the top right corner.  
Please note that this is deployed on a free version of render and **delays are expected during inactivity**.

To get started locally, then make sure that the application-development.properties are updated with your local database settings.

# Tech stack
- Java / Spring Boot
- MS SQL / JPA / Hibernate / Flyway
- Spring Security / JWT
- JUnit / Mockito
- Swagger/OpenAPI
- Docker
- Github Actions
- Render

# Features
- [x] User registration and login
- [x] Signup as Customer in the Bank
- [x] Create different types of accounts for the Customer
- [x] Deposit/withdraw money to/from the account
- [x] Transfer money between accounts
- [x] Check transaction history for the owned accounts


- [ ] Adding advisors/employees to the bank
- [ ] Setup a loan system and loan application
- [ ] Setup a credit card system
- [ ] Add support for interest rates/credit scores
- [ ] Add support for different currencies
- [ ] Notify customers about transactions and account changes
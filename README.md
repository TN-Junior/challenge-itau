# 🟧 Itaú Unibanco - Technical Challenge

### 💳 Transactions and Statistics API (60-second real-time processing)



This project was developed as a solution for the technical challenge proposed by Itaú Unibanco. It is a REST API built with Java and Spring Boot that receives financial transactions and returns real-time statistics for the transactions that occurred in the last 60 seconds.

---

## Features

- `POST /transacao`: Receives and stores a valid transaction
- `GET /estatistica`: Returns statistics for transactions from the last 60 seconds
- `DELETE /transacao`: Deletes all stored transactions

---

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation
- JUnit 5
- Maven Wrapper
- Lombok (optional)

---

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/TN-Junior/challenge-itau.git
```

### 2. Run the application
```bash
./mvnw spring-boot:run
```
The application will be available at:
http://localhost:8081


## Request Examples
### POST /transacao
```bash
POST /transacao
Content-Type: application/json
```

```bash
{
  "valor": 100.50,
  "dataHora": "2025-03-31T22:30:00-03:00"
}
```
Responses:
- 201 Created: Transaction accepted
- 422 Unprocessable Entity: Negative value or future date
- 400 Bad Request: Malformed JSON

### GET /estatistica
```bash
GET /estatistica
```
```bash
{
  "count": 1,
  "sum": 100.5,
  "avg": 100.5,
  "min": 100.5,
  "max": 100.5
}
```

### DELETE /transacao
```bash
DELETE /transacao
```
Response:
- 200 OK: All transactions were successfully removed

## Validation Rules
- valor must be greater than or equal to 0
- dataHora must be a timestamp in the past
- Future-dated or negative-value transactions are rejected
- Transactions are stored in-memory only

## Automated Tests
To run unit tests:
```bash
./mvnw test
```

## Project Structure
```bash
src/
├── main/
│   └── java/com/challenge/itau/
│       ├── controller/
│       │   ├── TransacaoController.java
│       │   └── EstatisticaController.java
│       ├── dto/
│       │   ├── TransacaoDTO.java
│       │   └── EstatisticaDTO.java
│       ├── domain/
│       │   └── Transacao.java
│       ├── service/
│       │   └── TransacaoService.java
│       └── ItauApplication.java
└── test/
    └── java/com/challenge/itau/
        └── service/
            └── TransacaoServiceTest.java

```

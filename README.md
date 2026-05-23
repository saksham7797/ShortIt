# ShortIt 🔗

A RESTful URL Shortening Service built with **Spring Boot** and **MySQL**. ShortIt lets you shorten long URLs, retrieve them, update them, delete them, and track access statistics — all through a clean REST API.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.6 |
| Database | MySQL 8.4.9 |
| ORM | Spring Data JPA + Hibernate |
| Build Tool | Maven |

---

## Project Structure

```
ShortIt/
├── src/main/java/com/saksham/ShortIt/
│   ├── Controller/
│   │   └── UrlController.java       # REST endpoints
│   ├── Service/
│   │   └── UrlService.java          # Business logic
│   ├── repository/
│   │   └── UrlRepository.java       # DB operations
│   ├── Entity/
│   │   └── UrlMapping.java          # DB table model
│   ├── DTO/
│   │   ├── CreateUrlRequest.java    # POST request body
│   │   ├── UpdateUrlRequest.java    # PUT request body
│   │   └── UrlResponse.java         # API response shape
│   └── exception/
│       └── ControllerException.java # Custom exception
├── src/main/resources/
│   ├── application.properties       # Local config (not committed)
│   └── application.properties.example
└── pom.xml
```

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL 8.x

### Setup

**1. Clone the repo**
```bash
git clone https://github.com/Saksham/ShortIt.git
cd ShortIt
```

**2. Create the database**
```sql
CREATE DATABASE shortit;
```

**3. Configure application.properties**
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Then fill in your MySQL password in `application.properties`.

**4. Run the app**
```bash
mvn spring-boot:run
```

App will start on `http://localhost:8080`

---

## API Reference

Base URL: `http://localhost:8080/ShortIt`

---

### Create Short URL
```
POST /shorten
```
**Request Body**
```json
{
  "originalUrl": "https://www.example.com/some/very/long/url"
}
```
**Response** `201 Created`
```json
{
  "id": 1,
  "originalUrl": "https://www.example.com/some/very/long/url",
  "shortCode": "a1b2c3",
  "createdAt": "2026-05-23T10:00:00",
  "updatedAt": "2026-05-23T10:00:00",
  "accessCount": 0
}
```

---

### Retrieve Original URL
```
GET /shorten/{shortCode}
```
**Response** `200 OK`
```json
{
  "id": 1,
  "originalUrl": "https://www.example.com/some/very/long/url",
  "shortCode": "a1b2c3",
  "createdAt": "2026-05-23T10:00:00",
  "updatedAt": "2026-05-23T10:00:00",
  "accessCount": 1
}
```

---

### Update Short URL
```
PUT /shorten/{shortCode}
```
**Request Body**
```json
{
  "originalUrl": "https://www.new-url.com"
}
```
**Response** `200 OK`

---

### Delete Short URL
```
DELETE /shorten/{shortCode}
```
**Response** `204 No Content`

---

### Get URL Statistics
```
GET /shorten/{shortCode}/stats
```
**Response** `200 OK`
```json
{
  "id": 1,
  "originalUrl": "https://www.example.com/some/very/long/url",
  "shortCode": "a1b2c3",
  "createdAt": "2026-05-23T10:00:00",
  "updatedAt": "2026-05-23T10:00:00",
  "accessCount": 42
}
```

---

### Error Responses

| Status | Meaning |
|---|---|
| `400` | Bad Request — invalid input |
| `404` | Not Found — shortCode doesn't exist |

---

## Database Schema

```sql
CREATE TABLE url_mapping (
  id           INT PRIMARY KEY AUTO_INCREMENT,
  original_url VARCHAR(255),
  short_code   VARCHAR(255) UNIQUE,
  created_at   DATETIME(6),
  updated_at   DATETIME(6),
  access_count INT NOT NULL DEFAULT 0
);
```

---

## Author

**Saksham** — [GitHub](https://github.com/saksham7797)

---

## License

This project is open source and available under the [MIT License](LICENSE).

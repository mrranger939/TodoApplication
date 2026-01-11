
# 📝 Todo App – Spring Boot REST API

A **production-grade Spring Boot REST API** for managing todo lists and todo items with:

* JWT-based authentication
* Ownership-based authorization
* Proper HTTP status codes
* Centralized exception handling
* Clean layered architecture

This project demonstrates how a real-world backend should be designed, structured, and secured.

---

## Database Design (Version 1)

### Entity Relationship Overview

```
User (1) ──── (N) TodoList (1) ──── (N) TodoItem
```

---

## Tables

### User

| Column   | Type    | Constraints      |
| -------- | ------- | ---------------- |
| id       | BIGINT  | Primary Key      |
| email    | VARCHAR | UNIQUE, NOT NULL |
| username | VARCHAR | NOT NULL         |
| password | VARCHAR | NOT NULL         |

---

### TodoList

| Column  | Type    | Constraints             |
| ------- | ------- | ----------------------- |
| id      | BIGINT  | Primary Key             |
| name    | VARCHAR | NOT NULL                |
| user_id | BIGINT  | FK → User(id), NOT NULL |

Unique constraint:

* `(user_id, name)` — a user cannot have two todo lists with the same name

---

### TodoItem

| Column       | Type    | Constraints                 |
| ------------ | ------- | --------------------------- |
| id           | BIGINT  | Primary Key                 |
| title        | VARCHAR | NOT NULL                    |
| status       | ENUM    | NOT NULL                    |
| deadline     | DATE    | NULLABLE                    |
| todo_list_id | BIGINT  | FK → TodoList(id), NOT NULL |

Allowed status values:

```
CREATED
IN_PROGRESS
COMPLETED
NOT_APPLICABLE
```

---

## Foreign Keys and Cascade Rules

* `TodoList.user_id → User.id`
* `TodoItem.todo_list_id → TodoList.id`

Cascade behavior:

* Deleting a User deletes all TodoLists and TodoItems
* Deleting a TodoList deletes all TodoItems

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (io.jsonwebtoken)
* MySQL
* Flyway
* Lombok

---

## Base URL

```
http://localhost:8080/api/v1
```

---

## Authentication & Security

### Authentication Model

* Stateless JWT authentication
* No sessions
* No refresh tokens (Version 1)
* JWT contains `userId`
* `userId` is extracted as `Authentication.getPrincipal()`

---

### Authorization Model

* Every TodoList belongs to exactly one user
* Every TodoItem belongs to exactly one TodoList
* Users can only access resources they own
* Ownership is enforced in the service layer
* Prevents IDOR vulnerabilities

---

## Auth APIs

### Register User

**POST** `/auth/register`
Bearer Token: NOT required

Request body:

```json
{
  "email": "user@example.com",
  "username": "john",
  "password": "password123"
}
```

Successful response – 201 Created:

```json
{
  "id": 1,
  "email": "user@example.com",
  "username": "john"
}
```

Possible errors:

* 409 Conflict – email already registered
* 400 Bad Request – invalid input

---

### Login User

**POST** `/auth/login`
Bearer Token: NOT required

Request body:

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Successful response – 200 OK:

```json
{
  "accessToken": "jwt-token-here"
}
```

Possible errors:

* 401 Unauthorized – invalid email or password
* 400 Bad Request – invalid input

---

## User APIs

### Get Current User Profile

**GET** `/users/me`
Bearer Token: REQUIRED

Headers:

```
Authorization: Bearer <jwt-token>
```

Successful response – 200 OK:

```json
{
  "id": 1,
  "email": "user@example.com",
  "username": "john"
}
```

Possible errors:

* 401 Unauthorized – missing or invalid token
* 404 Not Found – user not found

---

## Todo List APIs

All TodoList APIs require authentication.

---

### Create Todo List

**POST** `/lists`
Bearer Token: REQUIRED

Request body:

```json
{
  "name": "Work"
}
```

Successful response – 201 Created:

```json
{
  "id": 1,
  "name": "Work"
}
```

Possible errors:

* 401 Unauthorized – missing or invalid token
* 409 Conflict – duplicate list name for user
* 400 Bad Request – invalid input

---

### Get All Todo Lists of Current User

**GET** `/lists`
Bearer Token: REQUIRED

Successful response – 200 OK:

```json
[
  {
    "id": 1,
    "name": "Work"
  },
  {
    "id": 2,
    "name": "Personal"
  }
]
```

Possible errors:

* 401 Unauthorized – missing or invalid token

---

### Update Todo List

**PUT** `/lists/{listId}`
Bearer Token: REQUIRED

Path variable:

* `listId` – ID of the todo list

Request body:

```json
{
  "name": "Updated Work"
}
```

Successful response – 200 OK:

```json
{
  "id": 1,
  "name": "Updated Work"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden – list belongs to another user
* 404 Not Found – list does not exist
* 409 Conflict – duplicate list name

---

### Delete Todo List

**DELETE** `/lists/{listId}`
Bearer Token: REQUIRED

Successful response – 200 OK:

```json
{
  "id": 1,
  "name": "Work"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden – list belongs to another user
* 404 Not Found – list does not exist

Cascade delete applies to todo items.

---

## Todo Item APIs

All TodoItem APIs are scoped under a TodoList.

Base path:

```
/lists/{listId}/items
```

Bearer Token: REQUIRED for all endpoints below.

---

### Create Todo Item

**POST** `/lists/{listId}/items`

Request body:

```json
{
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

Successful response – 201 Created:

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden – list belongs to another user
* 404 Not Found – list does not exist
* 400 Bad Request – invalid status or input

---

### Get All Todo Items in a List

**GET** `/lists/{listId}/items`

Successful response – 200 OK:

```json
[
  {
    "id": 10,
    "title": "Buy milk",
    "status": "CREATED",
    "deadline": "2026-01-10"
  },
  {
    "id": 11,
    "title": "Pay bills",
    "status": "IN_PROGRESS",
    "deadline": null
  }
]
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden
* 404 Not Found – list does not exist

---

### Get One Todo Item

**GET** `/lists/{listId}/items/{itemId}`

Successful response – 200 OK:

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden
* 404 Not Found – item or list not found

---

### Update Todo Item

**PUT** `/lists/{listId}/items/{itemId}`

Partial updates allowed.

Request body:

```json
{
  "status": "COMPLETED"
}
```

Successful response – 200 OK:

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "COMPLETED",
  "deadline": "2026-01-10"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden
* 404 Not Found
* 400 Bad Request

---

### Delete Todo Item

**DELETE** `/lists/{listId}/items/{itemId}`

Successful response – 200 OK:

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "COMPLETED",
  "deadline": "2026-01-10"
}
```

Possible errors:

* 401 Unauthorized
* 403 Forbidden
* 404 Not Found

---

## Error Handling

All errors are handled via `GlobalExceptionHandler`.

Error response format:

```json
{
  "message": "Human readable error message",
  "status": 404,
  "timestamp": "2026-01-11T14:30:00Z"
}
```

Mapped exceptions:

| Exception             | HTTP Status |
| --------------------- | ----------- |
| NotFoundException     | 404         |
| ForbiddenException    | 403         |
| UnauthorizedException | 401         |
| ConflictException     | 409         |
| BadRequestException   | 400         |

---

## Database & Migrations

* Managed using Flyway
* Initial schema defined in `V1__init_schema.sql`
* Schema validated on application startup
* Safe, versioned migrations

---

## Architectural Principles

* Layered architecture (Controller → Service → Repository)
* DTO-based API (entities never exposed)
* Stateless security
* Ownership enforced in service layer
* Centralized exception handling
* Proper HTTP semantics
* Clean separation of concerns

---

## Current Status

* JWT authentication implemented
* All TodoList and TodoItem APIs secured
* Ownership enforced across all operations
* Proper HTTP status codes used
* Global exception handling in place
* Backend is production-ready



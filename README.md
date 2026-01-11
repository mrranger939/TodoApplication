

# 📝 Todo App – Spring Boot REST API

A **Spring Boot RESTful backend** for managing todo lists and todo items.
This version focuses on **core CRUD functionality** without authentication.
User handling and security will be added in a later version.

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL
* Flyway (database migrations)
* Lombok

---

## 📦 Project Structure

```
src/main/java/com/shujath/todoapp
│
├── controller
│   ├── TodoListController.java
│   └── TodoItemController.java
│
├── service
│   ├── TodoListService.java
│   ├── TodoItemService.java
│   └── impl
│       ├── TodoListServiceImpl.java
│       └── TodoItemServiceImpl.java
│
├── repository
│   ├── UserRepository.java
│   ├── TodoListRepository.java
│   └── TodoItemRepository.java
│
├── dto
│   ├── todolist
│   └── todoitem
│
├── mapper
│   ├── TodoListMapper.java
│   └── TodoItemMapper.java
│
└── entity
    ├── User.java
    ├── TodoList.java
    └── TodoItem.java
```

---

## 🌐 Base URL

```
http://localhost:8080/api/v1
```

---

## 🧠 Important Design Notes (Version 1)

* ❌ No authentication yet
* ❌ No JWT / SecurityConfig
* ✅ `userId` is passed explicitly (temporary)
* ✅ Entities are **never exposed** directly
* ✅ All responses use DTOs
* ✅ Ownership is enforced via path variables (`listId`, `itemId`)

> When authentication is added, `userId` will be derived from the logged-in user instead of request parameters.

---

# 📋 Todo List APIs

## 1️⃣ Create Todo List

**POST** `/lists`

### Request Body

```json
{
  "name": "Work",
  "userId": 1
}
```

### Response

```json
{
  "id": 1,
  "name": "Work"
}
```

---

## 2️⃣ Get All Todo Lists (for a User)

**GET** `/lists?userId=1`

### Response

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

---

## 3️⃣ Update Todo List Name

**PUT** `/lists/{listId}`

### Path Variable

* `listId` – ID of the todo list

### Request Body

```json
{
  "name": "Updated Work List"
}
```

### Response

```json
{
  "id": 1,
  "name": "Updated Work List"
}
```

---

## 4️⃣ Delete Todo List

**DELETE** `/lists/{listId}`

### Response

```json
{
  "id": 1,
  "name": "Work"
}
```

> Deleting a list automatically deletes all its todo items (cascade delete).

---

# ✅ Todo Item APIs

All todo items are scoped under a **specific todo list**.

Base path:

```
/lists/{listId}/items
```

---

## 5️⃣ Create Todo Item

**POST** `/lists/{listId}/items`

### Request Body

```json
{
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

### Allowed Status Values

```
CREATED
IN_PROGRESS
COMPLETED
NOT_APPLICABLE
```

### Response

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

---

## 6️⃣ Get All Todo Items in a List

**GET** `/lists/{listId}/items`

### Response

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

---

## 7️⃣ Get One Todo Item

**GET** `/lists/{listId}/items/{itemId}`

### Response

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "CREATED",
  "deadline": "2026-01-10"
}
```

> The API ensures the item belongs to the given list.

---

## 8️⃣ Update Todo Item

**PUT** `/lists/{listId}/items/{itemId}`

### Request Body (partial updates allowed)

```json
{
  "status": "COMPLETED"
}
```

### Response

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "COMPLETED",
  "deadline": "2026-01-10"
}
```

---

## 9️⃣ Delete Todo Item

**DELETE** `/lists/{listId}/items/{itemId}`

### Response

```json
{
  "id": 10,
  "title": "Buy milk",
  "status": "COMPLETED",
  "deadline": "2026-01-10"
}
```

---

## 🛠 Database

* Managed via **Flyway**
* Schema includes:

    * `users`
    * `todo_lists`
    * `todo_items`
* Foreign key relationships are enforced
* Cascading deletes are enabled

---

## 🚀 Future Enhancements

Planned for next versions:

* Authentication & JWT
* `/auth/register`, `/auth/login`
* `/users/me`
* Global exception handling
* Validation annotations
* HTTP status codes (`201`, `404`, `409`)
* Pagination & sorting

---

## ✅ Current Status

* ✔ All TodoList CRUD APIs implemented
* ✔ All TodoItem CRUD APIs implemented
* ✔ Clean layered architecture
* ✔ Ready for auth integration

---



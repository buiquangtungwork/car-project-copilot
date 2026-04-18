# Car Management API - Postman Guide

## Base URL
```
http://localhost:8081/api/cars
```

---

## API Endpoints Overview

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/{id}` | Get car by ID |
| POST | `/api/cars` | Create a new car |
| PUT | `/api/cars/{id}` | Update a car |
| DELETE | `/api/cars/{id}` | Delete a car |

---

## 1. CREATE - Add a New Car

### Curl Command
```bash
curl -X POST http://localhost:8081/api/cars \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Camry","year":2023}'
```

### Postman Setup
- **Method:** POST
- **URL:** http://localhost:8081/api/cars
- **Headers:** 
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "make": "Toyota",
  "model": "Camry",
  "year": 2023
}
```

### Sample Response (201 Created)
```json
{
  "id": 1,
  "make": "Toyota",
  "model": "Camry",
  "year": 2023
}
```

---

## 2. CREATE - Add Another Car

### Curl Command
```bash
curl -X POST http://localhost:8081/api/cars \
  -H "Content-Type: application/json" \
  -d '{"make":"Honda","model":"Civic","year":2022}'
```

### Postman Setup
- **Method:** POST
- **URL:** http://localhost:8081/api/cars
- **Headers:** 
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "make": "Honda",
  "model": "Civic",
  "year": 2022
}
```

---

## 3. CREATE - Add Third Car

### Curl Command
```bash
curl -X POST http://localhost:8081/api/cars \
  -H "Content-Type: application/json" \
  -d '{"make":"BMW","model":"3 Series","year":2024}'
```

### Postman Setup
- **Method:** POST
- **URL:** http://localhost:8081/api/cars
- **Headers:** 
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "make": "BMW",
  "model": "3 Series",
  "year": 2024
}
```

---

## 4. READ - Get All Cars

### Curl Command
```bash
curl http://localhost:8081/api/cars
```

### Postman Setup
- **Method:** GET
- **URL:** http://localhost:8081/api/cars
- **Headers:** None required
- **Body:** None

### Sample Response (200 OK)
```json
[
  {
    "id": 1,
    "make": "Toyota",
    "model": "Camry",
    "year": 2023
  },
  {
    "id": 2,
    "make": "Honda",
    "model": "Civic",
    "year": 2022
  },
  {
    "id": 3,
    "make": "BMW",
    "model": "3 Series",
    "year": 2024
  }
]
```

---

## 5. READ - Get Car by ID

### Curl Command
```bash
curl http://localhost:8081/api/cars/1
```

### Postman Setup
- **Method:** GET
- **URL:** http://localhost:8081/api/cars/1
- **Headers:** None required
- **Body:** None
- **Note:** Replace `1` with desired car ID

### Sample Response (200 OK)
```json
{
  "id": 1,
  "make": "Toyota",
  "model": "Camry",
  "year": 2023
}
```

---

## 6. UPDATE - Modify a Car

### Curl Command
```bash
curl -X PUT http://localhost:8081/api/cars/1 \
  -H "Content-Type: application/json" \
  -d '{"make":"Toyota","model":"Corolla","year":2024}'
```

### Postman Setup
- **Method:** PUT
- **URL:** http://localhost:8081/api/cars/1
- **Headers:** 
  - Content-Type: application/json
- **Body (raw JSON):**
```json
{
  "make": "Toyota",
  "model": "Corolla",
  "year": 2024
}
```
- **Note:** Replace `1` with desired car ID

### Sample Response (200 OK)
```json
{
  "id": 1,
  "make": "Toyota",
  "model": "Corolla",
  "year": 2024
}
```

---

## 7. DELETE - Remove a Car

### Curl Command
```bash
curl -X DELETE http://localhost:8081/api/cars/2
```

### Postman Setup
- **Method:** DELETE
- **URL:** http://localhost:8081/api/cars/2
- **Headers:** None required
- **Body:** None
- **Note:** Replace `2` with desired car ID

### Sample Response (204 No Content)
```
No body returned
```

---

## 8. GET - Verify Car Deletion

### Curl Command
```bash
curl http://localhost:8081/api/cars
```

### Postman Setup
- **Method:** GET
- **URL:** http://localhost:8081/api/cars
- **Headers:** None required
- **Body:** None

---

## 9. GET - Test Invalid ID

### Curl Command
```bash
curl http://localhost:8081/api/cars/999
```

### Postman Setup
- **Method:** GET
- **URL:** http://localhost:8081/api/cars/999
- **Headers:** None required
- **Body:** None

### Sample Response (404 Not Found)
```
No body returned (Not Found)
```

---

## How to Import into Postman

### Option 1: Manual Import
1. Open Postman
2. Click **"New"** → **"Request"**
3. Copy each curl command from the guide above
4. For each request:
   - Set the Method (GET, POST, PUT, DELETE)
   - Enter the URL
   - Add Headers if needed
   - Add Body (for POST/PUT requests)
5. Click **"Save"** and organize in collections

### Option 2: Import from cURL
1. Open Postman
2. Click **"File"** → **"Import"**
3. Select the **"Raw text"** tab
4. Paste the curl command
5. Click **"Continue"** and then **"Import"**

---

## Collection Structure in Postman

Organize your requests in Postman as follows:

```
Car Management API
├── CREATE
│   ├── Add Car 1 (POST)
│   ├── Add Car 2 (POST)
│   └── Add Car 3 (POST)
├── READ
│   ├── Get All Cars (GET)
│   └── Get Car by ID (GET)
├── UPDATE
│   └── Update Car (PUT)
└── DELETE
    ├── Delete Car (DELETE)
    └── Verify Deletion (GET)
```

---

## Testing Order
1. **Create** - Add 3 sample cars first
2. **Read** - Get all cars and specific car
3. **Update** - Modify a car's details
4. **Delete** - Remove a car
5. **Verify** - Confirm deletion

---

## Database Configuration
```
URL: jdbc:mysql://localhost:3506/admin
Username: admin
Password: admin
Driver: com.mysql.cj.jdbc.Driver
```

---

## Notes
- Replace IDs (1, 2, 3, etc.) based on your actual database records
- Responses use 200 OK for successful operations
- 404 Not Found is returned for non-existent cars
- 204 No Content is returned for successful DELETE operations
- Ensure the Spring Boot application is running on port 8081
- Ensure MySQL service is running on port 3506

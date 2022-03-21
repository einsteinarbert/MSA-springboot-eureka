## GATEWAY + JWT TOKEN + ZUUL

### 1. get token
- POS http://localhost:8761/auth/login
```json
{
    "username": "user",
    "password": "user"
}
```

### 2. call gateway test internal
- GET http://localhost:8761/resource/user
- With HEADER: 
  - Authorization: Bearer xxxxxx

### 3. call microservice
GET http://localhost:8761/api/ping
>> pong

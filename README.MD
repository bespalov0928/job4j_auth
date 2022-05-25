
### Описание проекта
Сервис выполняет функцию конвертации длинной ссылки в короткую.Все ссылки сохраняются в базу данных.

### Стек технологий
* Java 14
* Spring Boot 2
* Spring Security & JWT authorization
* Spring Data JPA
* PostgreSQL
* Liquibase


### Запуск проекта из Docker

#### Сборка проекта 
```
mvn install
```

#### сборка образа
```
docker build -t shortcut .
```

#### Запуск приложения 
```
docker-compose up
```

### Using REST API

#### register site
```
curl -H "Content-Type: application/json" -X POST -d '{
"username":"bespalov1",
"password":"123456"}' http://localhost:80/users/sign-up
```

#### Getting token
```
curl -i -H "Content-Type: application/json" -X POST -d '{
"username":"bespalov1",
"password":"123456"
}' http://localhost:80/login
```

#### getting all person
```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/person/
```

#### create room
```
curl -i -H "Content-Type: application/json" -X POST -d '{
"name":"smoking1"
}' http://localhost:80/room/
```

#### get all room
```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/room/
```

#### create massage
```
curl -i -H "Content-Type: application/json" -X POST -d '{
"mes":"hello000000001",
"room": {
"id":"1",
"name":"smoking"
},
"person":   {
"id":"2",
"username":"bespalov1",  
"password":"123456",
"empid":"0"
}
}' http://localhost:80/message/
```

#### get all message
```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/message/
```

#RestFull API архитектура.

####RestFull API архитектура - это архитектура клиент-серверного приложения базирующаяся на 6 принципах.

1. Универсальный интерфейс взаимодействия. (Uniform Interface)

2. Запросы без состояния. (Stateless)

3. Поддержка кеширования. (Cacheable)

4. Клиент-серверная ориентация.

5. Поддержка слоев (Layered System)

6. Возможность выполнять код на стороне клиента (Code on Demand)


##Запуск проекта из Docker

###Собираем проект - mvn install

###Собираем Образ - docker build -t shortcut .

###Запустим - docker-compose up


##Using REST API

```Register site```
curl -H "Content-Type: application/json" -X POST -d '{
"username":"bespalov1",
"password":"123456"
}' http://localhost:80/users/sign-up


```Getting token```
curl -i -H "Content-Type: application/json" -X POST -d '{
"username":"bespalov1",
"password":"123456"
}' http://localhost:80/login


```getting all person```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/person/


```create room```
curl -i -H "Content-Type: application/json" -X POST -d '{
"name":"smoking1"
}' http://localhost:80/room/


```get all room```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/room/


```create massage```
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


```get all message```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZXNwYWxvdiIsImV4cCI6MTY1Mzg0NjQxN30.8ise2_1wbq0Mtt1rNxcVwvVJOoRSfeVLEaG0h9oXGIELoPemvk0LFttfEC5QxQw0_3oOovJh-44njpt8Fbyutg" http://localhost:80/message/

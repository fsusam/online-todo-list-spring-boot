# TODO List with Spring Boot
create simple TODO list task and store database (in memory or RDBMS)
support multi users

## Prerequisites
- Apache Maven 3.5.4 or above
- java version 1.8
- NodeJS v8.12.0 or above
- Postman or Curl (Optional for test)

## Running And Tests
### Running
Clone the project go to project directory

```
git clone https://github.com/fsusam/online-todo-list-spring-boot.git
cd online-todo-list-spring-boot 
```

Build the project
```
mvn clean install
```

Run the backend
```
mvn clean install spring-boot:run
```

Run the frontend under nline-todo-list-web folder
```
npm install
npm start
```

### Test
Frontend Link : http://localhost:8080

Sample Users
```
username : user1@example.com
IBAN : user1234
---------------------------
username : user2@example.com
IBAN : user1234
``` 

## Run with Docker
Go to project directory
```
docker-compose up --build
```
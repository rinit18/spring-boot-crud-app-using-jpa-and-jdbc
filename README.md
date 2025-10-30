# 📘 Spring Boot CRUD Application (JDBC + JPA)

## 🧩 Overview
This project demonstrates how to build a **Spring Boot CRUD (Create, Read, Update, Delete)** application that supports **both JDBC and JPA** approaches using the **same model, controller, and service layers**.

It’s designed for learning and showcasing multiple persistence techniques in one project — a common approach used in enterprise-level applications transitioning from legacy JDBC to modern JPA/Hibernate.

---

## ⚙️ Tech Stack
| Layer | Technology Used |
|--------|------------------|
| Backend Framework | **Spring Boot 3.x** |
| Database | **MySQL 8+** |
| ORM / JDBC | **Spring Data JPA** and **Spring JDBC Template** |
| Build Tool | **Maven** |
| Language | **Java 17+** |
| API Testing | **Postman / Frontend Client** |

---

## 🏗️ Project Structure

```
src/main/java/org/rinit/springbootjdbc
│
├── Controller/
│   └── StudentController.java        # REST Controller exposing CRUD APIs
│
├── Model/
│   └── Student.java                  # Entity class (used by both JDBC & JPA)
│
├── repositry/
│   ├── StudentDao.java               # Common interface for persistence operations
│   ├── impl/
│   │   └── JdbcStudentDao.java       # JDBC implementation using JdbcTemplate
│   └── jpa/
│       └── StudentJpaRepository.java # JPA Repository (extends JpaRepository)
│       └── JpastudentDao.java # JPA implementation using JpaRepository
├── service/
│   ├── StudentService.java           # Business logic layer
│   └── impl/
│       └── StudentServiceImpl.java   # Service implementation using selected DAO
│
└── SpringbootJdbcApplication.java    # Main Spring Boot entry point
```

---

## 🧠 How It Works

### 🔹 1. Model Layer (`Student.java`)
Used by **both JDBC and JPA**.  
It’s annotated with `@Entity` so JPA can manage it, but it also works fine with plain JDBC.

```java
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int marks;

    // Getters & Setters
}
```

---

### 🔹 2. DAO Layer
Both persistence approaches share a **common interface**:

```java
public interface StudentDao {
    Student save(Student student);
    List<Student> findAll();
    Student findById(int id);
    void deleteById(int id);
}
```

#### ✅ JDBC Implementation (`StudentRepoDao.java`)
Uses **Spring JdbcTemplate** for manual SQL operations.  
You can switch to this by annotating it with:
```java
@Repository("jdbcDao")
```

#### ✅ JPA Implementation (`StudentJpaRepository.java`)
Extends `JpaRepository<Student, Integer>` and is auto-configured by Spring Boot.

---

### 🔹 3. Service Layer
Implements logic that calls DAO methods.  
By changing only **one bean reference**, you can switch between JDBC and JPA without touching controller or frontend code.

```java
@Service
public class StudentServiceImpl implements StudentService {

    @Qualifier("jdbcDao") // or "jpaDao"
    private final StudentDao studentDao;

    public StudentServiceImpl(@Qualifier("jdbcDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
```

---

### 🔹 4. Controller Layer
Handles REST endpoints like:
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/students` | Get all students |
| `GET` | `/students/{id}` | Get student by ID |
| `POST` | `/students` | Add new student |
| `PUT` | `/students/{id}` | Update existing student |
| `DELETE` | `/students/{id}` | Delete student by ID |

---

## ⚙️ Configuration

**`application.properties`**
```properties
spring.application.name=SPRING BOOT JDBC

spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=rinit
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

This configuration allows **both JDBC and JPA** to share the same `DataSource`.

---

## 🚀 Running the Application

1. **Create Database:**
   ```sql
   CREATE DATABASE studentdb;
   ```
2. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```
3. **Test APIs (Example using Postman):**
    - `GET http://localhost:8080/students`
    - `POST http://localhost:8080/students`
      ```json
      {
        "name": "Rinit",
        "marks": 88
      }
      ```

---

## 🔄 Switching Between JDBC & JPA

You can easily switch persistence layers by modifying the bean qualifier in the service layer:

```java
@Qualifier("jdbcDao") // Uses JDBC
@Qualifier("jpaDao")  // Uses JPA
```

No other change is needed in the controller or frontend.

---

## 🧾 Features
✅ Full CRUD Operations  
✅ Works with both JDBC and JPA  
✅ Single Model Class  
✅ Common DAO Interface  
✅ Easily Switch Between Implementations  
✅ Production-Ready REST API

---

## 🧑‍💻 Author
**Rinit Bhowmick**  
Spring Boot | Full Stack Developer (MCA)  
💼 Designed for interview demonstration and enterprise learning.

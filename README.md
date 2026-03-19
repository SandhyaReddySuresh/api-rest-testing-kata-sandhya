
## 📌 Project Overview
This repository contains a solution to the API Testing Kata, a challenge designed in API testing using Java, Rest-Assured, and Cucumber. The goal of this kata is to create a comprehensive test suite for a booking API, covering various scenarios and edge cases.
It automates hotel api booking flow on [automationintesting.online]([(https://automationintesting.online]).
This project is an API automation testing framework built using:
- Serenity BDD  
- Cucumber  
- REST Assured  
- JUnit  

It is designed to test REST APIs using Behavior-Driven Development (BDD) practices, providing readable test scenarios and detailed reporting.

---

## ⚙️ Tech Stack 

- Java 17
- Maven
- Serenity BDD (v4.2.0)
- Cucumber (v7.14.0)
- REST Assured (v5.4.0)
- JUnit 4
- Hamcrest
- JSON Schema Validator

---


## 🗂️ Project Structure
```

APIRestAssuredTesting/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   ├── resources/
 │   │        ├── serenity.conf       → Serenity configuration (env, base URL)
 │   │
 │   └── test/
 │       ├── java/
 │   │   │   ├── api/                 → API request logic (REST Assured)
 │   │   │   ├── check/               → Health Check
 │   │   │   ├── pojo/                → Data models / request & response bodies
 │   │   │   ├── runner/              → Cucumber test runners (RunnerTest)
 │   │   │   ├── stepdefinitions/     → Step definitions (BDD steps)
 │   │   │   ├── TestData/            → Random Test Data Generator
 │   │   │   ├── utils/               → Utility classes (helpers, configs)
 │       │
 │       └── resources/
 │           ├── features/            → Cucumber feature files
 │           ├── schema/              → Response Schema
 │           ├── serinity.properties  → Define base url and other configuration
 │
 ├── target/
 │   ├── site/serenity/               → Generated Serenity reports (index.html)
 │   ├── logs/		                  → Generated logs
 │
 ├── pom.xml                          → Maven dependencies & plugins
 └── README.md                        → Project documentation

```

---

🚀 Getting Started

✅ Prerequisites

Make sure you have installed:
- Java 17+
- Maven 3.x
- IDE (IntelliJ)

## ▶️ How to Run

- Clone the repository using git clone https://github.com/SandhyaReddySuresh/api-rest-testing-kata-sandhya ([View GitHub Repository](https://github.com/SandhyaReddySuresh/api-rest-testing-kata-sandhya))
  
### 🖥️ Run from IntelliJ IDEA
```bash
Right-click on `RunnerTest.java` → **Run 'RunnerTest.java'**
```

### 🧩 Run from Terminal (Maven)
```bash
mvn clean verify
```

## 💡 Features
The test suite covers the following scenarios:

- Authentication of user
- Fetch List of rooms
- Check Availability
- View the room details
- Creating a booking
- Fetch Room Summary
- Fetch Booking details by Room ID
- Fetch Booking details by Booking ID
- Editing/Deleting a booking
- Fetch the booking report

📊 Test Reports (Serenity)

After execution, Serenity automatically generates detailed reports.

📍 Location:

target/site/serenity/index.html

Reports include:
- Test execution summary
- Step-by-step results
- Request & response details
- Failure analysis

---

## 🏁 License
This project is intended for professional QA automation portfolio use.

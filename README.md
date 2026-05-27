JavaFX Student Management System

A desktop application for managing student records, built with JavaFX and PostgreSQL. This project demonstrates essential CRUD (Create, Read, Update, Delete) operations with a modern graphical user interface and a persistent SQL database backend.

**Author:** Umandap, Sam Matthew B.

**Section:** BSIT 2-2

## Features

* **Add Students:** Register new students with their Name, Course, and Year Level.
* **View Records:** Display all registered students in a dynamic, easy-to-read table (`TableView`).
* **Update Information:** Modify existing student details instantly.
* **Delete Records:** Remove students from the database securely.
* **Clear Form:** Quickly reset input fields for new entries.

## Technologies & Tools

* **Language:** Java (JDK 21)
* **UI Framework:** JavaFX (via OpenJFX) & FXML
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **IDE:** Visual Studio Code

---

## Getting Started

### 1. Prerequisites

Ensure you have the following installed on your system:

* **Java Development Kit (JDK):** Version 17 or higher.
* **PostgreSQL:** Installed and running on your local machine.
* **Maven:** For dependency management.
* **VS Code:** With the *Extension Pack for Java* installed.

### 2. Database Setup

Before running the application, you must set up the database.

1. Open your PostgreSQL tool (e.g., pgAdmin or psql).
2. Create a new database (e.g., `student_db`).
3. Run the following SQL script to create the required table:

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(50) NOT NULL,
    year_level VARCHAR(20) NOT NULL
);

```

### 3. Application Configuration

You need to connect the Java app to your new database.

1. Open `src/main/java/com/example/DBConnection.java` (or wherever your connection class is located).
2. Update the connection string, username, and password to match your PostgreSQL setup:

```java
// Example configuration
String url = "jdbc:postgresql://localhost:5432/student_db";
String user = "postgres";
String password = "admin123";

```

### 4. Running the Application

1. Open the project folder in VS Code.
2. Allow Maven a few seconds to synchronize the dependencies (including the PostgreSQL JDBC driver).
3. Open your `MainApp.java` file.
4. Click the **Run** button (or press `F5`).

---

## Frequently Asked Questions (FAQ)

**Q: I deleted a student (e.g., ID 3), but when I added a new one, the ID skipped to 4 instead of reusing 3. Is this a bug?** **A:** No, this is standard database behavior! Auto-incrementing Primary Keys (`SERIAL`) never reuse old numbers to prevent data overlapping. If you want to completely wipe your test data and reset the counter back to 1, run this SQL command in your database:
`TRUNCATE TABLE students RESTART IDENTITY;`

**Q: I am getting a `java.lang.module.FindException` or `ClassNotFoundException` when trying to run.** **A:** This usually means the project needs a clean build, or the `pom.xml` dependencies haven't fully downloaded. In VS Code, open the Command Palette (`Ctrl + Shift + P`) and run **Java: Clean Workspace**, then try running the app again.

**Q: I get a "Content is not allowed in prolog" error pointing to my `main.fxml` file.** **A:** This means there are invisible formatting characters or invalid XML comments before the very first line of your FXML file. Ensure `<?xml version="1.0" encoding="UTF-8"?>` is at row 1, column 1, with no spaces before it.

---

*Developed for academic purposes.*

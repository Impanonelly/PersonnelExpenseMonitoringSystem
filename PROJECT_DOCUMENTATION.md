# PROJECT DOCUMENTATION: Personnel Expense Monitoring System (PEMS)
**Course:** BEST PROGRAMMING PRACTICES AND DESIGN PATTERNS (SENG 8240)
**Academic Year:** 2025/2026
**Instructor:** RUTARINDWA JEAN PIERRE

---

## PHASE 1: SYSTEM ANALYSIS AND DESIGN

### 1. Problem Statement
Many individuals struggle to track their daily expenses accurately using manual methods (paper or spreadsheets). This lead to financial mismanagement, lost records, and difficulty in analyzing spending habits over time. The **Personal Expense Monitoring System (PEMS)** provides a secure, digital, and automated solution to record, search, and analyze expenses in real-time.

### 2. UML Diagram Descriptions
*Use these to draw your diagrams for the PowerPoint.*

*   **Use Case Diagram**: The **User** interacts with the system to: 
    *   Register/Login (Security)
    *   Manage Expenses (Add, Update, Delete)
    *   Search Records (Filter by Date)
    *   Manage Profile (Update personal info)
    *   Generate Reports (Export to PDF)
*   **Class Diagram**:
    *   `User`: id, username, password, fullname, phoneNumber.
    *   `Expense`: id, amount, category, date, description, user_id.
    *   `Relation`: One User can have Many Expenses.
*   **Sequence Diagram (The RMI Flow)**:
    1.  Client clicks "Save".
    2.  Client calls RMI Service.
    3.  Server calls DAO.
    4.  DAO persists to PostgreSQL.
    5.  Success message returns to Client UI.

---

## PHASE 2: PROTOTYPING & BEST PRACTICES

### 1. Software Development Prototype
The developed prototype for **PEMS** is a functional, distributed application using **Java Swing** for the frontend and **Java RMI** for the backend. 
*   **Purpose**: This prototype validates the feasibility of real-time expense tracking and remote data persistence.
*   **Validation**: It tests the RMI connection between the Client and Server, ensuring that even if they are on different machines, data is synchronized correctly.

### 2. Software Programming Best Practices (Google Java Style)
We adhered to the **Google Java Style Guide** to ensure professional code quality:
*   **Naming Conventions**: All classes use `UpperCamelCase` (e.g., `ExpenseDao`), and variables/methods use `lowerCamelCase` (e.g., `saveExpense`).
*   **Indentation**: Used consistent 4-space indentation and standard block structures.
*   **Package Structure**: Followed a modular structure (`model`, `dao`, `service`, `controller`) for high maintainability.

### 3. Design Patterns Implemented
We referred to two primary design patterns:
*   **DAO (Data Access Object) Pattern**: 
    - *How it's used*: We created `ExpenseDao` and `UserDao` to separate the database logic from the business logic. 
    - *Benefit*: This makes the code decoupled; changing the database does not require changing the UI logic.
*   **Singleton Pattern**: 
    - *How it's used*: Implemented in `HibernateUtil.java`. It ensures that only **one instance** of the Hibernate `SessionFactory` exists.
    - *Benefit*: Prevents multiple database connections from slowing down the system.

---

## PHASE 3: DEPLOYMENT & VERSION CONTROL

### 1. The Dockerization Process
Dockerizing an application involves three main steps:
1.  **Writing a Dockerfile**: Creating a script that contains all the commands needed to assemble an image (OS, Java environment, and application files).
2.  **Building the Image**: Using the `docker build` command to create a portable package of the software.
3.  **Running the Container**: Using `docker-compose` to start the application in an isolated environment where it can communicate with other containers (like the Database).

### 2. Our Dockerization Strategy
The **PEMS** system is containerized using a central **Docker Compose** file in the root directory, which orchestrates three main services:
*   **Container 1 (PostgreSQL Database)**: Stores all user and expense data.
*   **Container 2 (Java RMI Backend Server)**: Handles business logic, security, and DB communication.
*   **Container 3 (Java Swing Frontend Client)**: Packaged as a container for portability and deployment testing.
*   **Benefit**: The entire stack (Frontend, Backend, and Database) can be launched with a single command: `docker-compose up`.

### 3. Version Control System (VCS) - Git
We have selected **Git** (managed via **GitHub**) as our Version Control System. 
*   **Configuration**: We initialized a local Git repository and connected it to GitHub.
*   **Capturing Necessary Parts**: We used a `.gitignore` file to ensure that only source code and configuration files are tracked, while temporary IDE files and build artifacts are excluded.
*   **Repository Link**: [https://github.com/Impanonelly/PersonnelExpenseMonitoringSystem](https://github.com/Impanonelly/PersonnelExpenseMonitoringSystem)

---

## PHASE 4: SOFTWARE TEST PLAN

### 1. Test Plan Roadmap
*   **What will be tested**: All core features including User Authentication, Expense CRUD (Create, Read, Update, Delete), Search filters, and PDF Report generation.
*   **How it will be tested**: **Manual Functional Testing** (using the UI) and **Integration Testing** (verifying the RMI connection between Client, Server, and Database).
*   **Who will test**: The primary developer (the student) will perform the testing.
*   **Schedule**: Testing is performed after each phase of development and a final "Smoke Test" is done before submission.

### 2. Test Cases and Results

| Test ID | Feature | Test Case | Expected Result | Result |
| :--- | :--- | :--- | :--- | :--- |
| **TC-01** | Authentication | Login with valid credentials | Redirect to Dashboard | **PASS** |
| **TC-02** | Validation | Login with invalid email format | Error Popup: "Invalid format" | **PASS** |
| **TC-03** | CRUD: Create | Fill expense form and click Save | Table updates, "Success" popup | **PASS** |
| **TC-04** | CRUD: Update | Modify existing expense amount | Record updated in Database | **PASS** |
| **TC-05** | CRUD: Delete | Delete a selected record | Row removed, "Success" popup | **PASS** |
| **TC-06** | Reporting | Click "Save Report as PDF" | PDF file generated locally | **PASS** |
| **TC-07** | Integration | Start Docker containers | Server & DB link successfully | **PASS** |

---

## BEST PRACTICES IMPLEMENTED
1.  **DAO Design Pattern**: Separation of data access from business logic.
2.  **Singleton Pattern**: Efficient management of Hibernate SessionFactory.
3.  **Clean Code**: Meaningful naming conventions (`ExpenseDao`, `UserImpl`) and proper indentation.
4.  **Error Handling**: Robust use of `JOptionPane` for all user feedback.

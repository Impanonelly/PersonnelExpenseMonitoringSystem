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

## PHASE 3: DEPLOYMENT & VERSION CONTROL

### 1. Dockerization Strategy
The system is containerized using **Docker Compose** for high portability:
*   **Container 1 (PostgreSQL)**: Stores all user and expense data.
*   **Container 2 (Java RMI Server)**: Handles business logic and DB communication.
*   **Benefit**: The instructor can deploy the entire stack with one command: `docker-compose up`.

### 2. Version Control (Git)
*   **Platform**: GitHub
*   **Link**: [https://github.com/Impanonelly/PersonnelExpenseMonitoringSystem](https://github.com/Impanonelly/PersonnelExpenseMonitoringSystem)
*   **Strategy**: Initialized with a `.gitignore` to exclude build artifacts and track only clean source code.

---

## PHASE 4: SOFTWARE TEST PLAN

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

# Bankify - Simple Banking Application
**Bankify** is a JavaFX-based banking application designed to provide client and admin functionalities in a streamlined and user-friendly interface. This project is **still under development** and aims to deliver core banking features including account creation, transaction management, and more.

## Features
### Client Features:
- **Dashboard**: Displays account details, income, expenses, and quick links for actions.
- **Accounts Management**:
    - Checking account details with transaction limits.
    - Savings account details with withdrawal limits.
    - Ability to transfer funds between accounts.

- **Transactions**:
    - View transaction history in a user-friendly list.
    - Send money to other accounts by entering payee details and transaction messages.

- **Responsive Design**: All screens are designed with an intuitive, responsive layout for the best user experience.

### Admin Features:
- **Create Client Account**:
    - Add new clients with checking and savings accounts.
    - Initialize account balances and set unique identifiers.

- **View Clients**:
    - List and manage all registered clients.
    - Retrieve and display client details linked to their accounts.

- **Deposit Funds**:
    - Deposit money directly into client accounts.

- **Session Management**:
    - Admin functionalities are securely separated and manageable via a menu system.

## Architecture
The project has been structured to enforce an efficient Model-View-Controller (MVC) architecture, separating the application's functionalities and responsibilities into modular components:
1. **Models**: Represent backend logic and data structures, such as accounts (`CheckingAccount`, `SavingsAccount`), clients (`Client`), and transactions (`Transaction`).
2. **Views**: Includes all FXML layouts and JavaFX controls for creating highly customizable UI components.
3. **Controllers**: Connect the models and views to handle user interactions and data manipulation.


## Technology Stack
- **Programming Language**: Java
- **Framework**: JavaFX
- **Database**: SQLite
- **Dependencies**:
    - **[FontAwesomeFX]()**: For stunning icons in the user interface.
    - Control libraries such as `ControlsFX`, `TilesFX` for enhanced UI components.

## Installation
1. **Clone the Repository**:
``` bash
   git clone https://github.com/your-username/Bankify.git
   cd Bankify
```
1. **Prerequisites**:
    - Install Java 17 or later.
    - Install your IDE (e.g., IntelliJ IDEA) with JavaFX SDK configured.

2. **Run the Application**:
    - Open the project in your favorite IDE.
    - Run the `App.java` file located in the `com.example.bankify` package.

## Usage
### Login
- Choose account type (`Admin` or `Client`) from the dropdown menu on the login page.
- Admins can access client management features; clients can manage their accounts, make transactions, and view account summaries.

### Admin Operations
1. Navigate to the **Create Client** menu to register new clients.
2. View and manage clients under the **Clients** section.
3. Deposit funds through the **Deposit** menu.

### Client Operations
1. Use the **Dashboard** for quick insights on account balances and performance (income/expenses).
2. View and manage accounts under **Accounts**.
3. Send/receive money through the **Transactions** menu.

## File Structure
Below is a summarized breakdown of the project's structure:
``` 
src/main/java/com/example/bankify/
├── Controllers/
│   ├── Admin/
│   ├── Client/
│   └── loginController.java
├── Models/
│   ├── Account.java
│   ├── Client.java
│   ├── CheckingAccount.java
│   ├── SavingsAccount.java
│   ├── Transaction.java
│   └── DatabaseDriver.java
├── Views/
│   ├── Admin/
│   ├── Client/
│   └── Fxml files for layouts
└── App.java
```
## Known Issues and Future Improvements
### Current Limitations:
- Database connection and query execution require improved error handling.
- Limited functionality for admin actions (e.g., reports, account deletion).

### Planned Improvements:
1. Enhance security by encrypting sensitive data like passwords.
2. Add validation rules for balance transfers and client data.
3. Implement a report generation system for both clients and admins.
4. Refine the UI to make the application fully responsive and interactive.
5. Optimize database queries for better performance.

## Contribute
As this project is still in progress, contributions are welcome. You can:
- Submit bug reports via issues.
- Fork the repository and create pull requests.
- Suggest feature/enhancement ideas.

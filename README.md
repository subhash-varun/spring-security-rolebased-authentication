# spring-security-rolebased-authentication
Role based authentication using springboot and spring security. For data persistent I'm using mysql.

## Features

- **User Roles**: Supports roles such as `EMPLOYEE`, `MANAGER`, and `ADMIN`, each with specific access rights.
- **Role-Based Endpoints**:
  - Public: Accessible to anyone.
  - Employee: Restricted to users with the `EMPLOYEE` role.
  - Manager: Restricted to users with the `MANAGER` role.
  - Admin: Restricted to users with the `ADMIN` role.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- MySQL
- Postman (for API testing)


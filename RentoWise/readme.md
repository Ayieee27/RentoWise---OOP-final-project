# House Rent Management System

## Project Objectives

The main objectives of this project are:
- **Display Available Houses**: List all available rental houses in the city.
- **Select Desired House**: Allow users to choose a rental house from the displayed list.
- **Pay Rent and Report Issues**: Enable tenants to pay rent and report any issues using the application.

### Project Outcomes
- Displaying the prices of selected rental houses.
- Facilitating the booking of rental houses.
- Simulating the updating of house details.

---

## Project Overview

The House Rent Management System is a console-based application designed to streamline the management of rental properties. It provides an intuitive interface for both tenants and administrators, offering functionalities like viewing houses, paying rent, and booking houses. The project aligns with **Sustainable Development Goal (SDG) 11: Sustainable Cities and Communities**, aiming to promote sustainable urban living through efficient housing management.

---

## OOP Principles Applied

This project integrates core Object-Oriented Programming (OOP) principles:

- **Inheritance**: The `Admin` and `Tenant` classes inherit from the `User` base class, reusing common functionality while allowing role-specific implementations.
- **Polymorphism**: The `menu` method in the `User` class is overridden in `Admin` and `Tenant` to provide unique behaviors for each role.
- **Encapsulation**: Data related to bookings and rent payments is encapsulated in the `Booking` and `RentPayment` classes, ensuring proper data management.
- **Abstraction**: The complexity of tenant and admin interactions is abstracted into separate methods for clarity and modularity.

---

## Alignment with SDG 11: Sustainable Cities and Communities

This project supports SDG 11 by promoting sustainable housing practices and efficient property management. By simplifying the rental process and improving tenant experience, the application fosters inclusive and sustainable urban development.

---

## Instructions for Running the Program

### Prerequisites
- **Java Development Kit (JDK)**: Ensure that JDK 8 or higher is installed on your system.
- **IDE/Text Editor**: Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor with Java support.

### Steps to Run
1. Clone or download the project code.
2. Open the project in your IDE or compile it using the terminal.
3. Compile the `RentoWise.java` file:
   ```bash
   javac RentoWise.java

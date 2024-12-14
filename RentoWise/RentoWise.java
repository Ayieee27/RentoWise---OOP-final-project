import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentoWise {

    private List<RentPayment> rentPayments = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        new RentoWise().start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to RentoWise: A Sustainable Rental Solution");
        System.out.println("Aligned with SDG 11: Sustainable Cities and Communities");

        while (true) {
            System.out.println("\n--- RentoWise System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginMenu(scanner);
                    break;

                case 2:
                    System.out.println("Exiting RentoWise. Thank you for supporting sustainable housing!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void loginMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Login Menu ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Tenant Login");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    User admin = new Admin("Admin");
                    admin.menu(scanner, this);
                    break;
                case 2:
                    User tenant = new Tenant("Tenant");
                    tenant.menu(scanner, this);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addHouseDetails(Scanner scanner) {
        System.out.print("Enter house ID: ");
        int houseId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter house address: ");
        String address = scanner.nextLine();
        System.out.print("Enter rent amount: ");
        double rentAmount = scanner.nextDouble();

        System.out.println("House details added successfully. (Simulated)");
        System.out.println("ID: " + houseId + ", Address: " + address + ", Rent: " + rentAmount);
    }

    public void viewAllHouses() {
        System.out.println("\n--- House Details ---");
        System.out.println("ID: 1, Address: 123 Main St, Rent: 1200.0 (Simulated)");
        System.out.println("ID: 2, Address: 456 Oak St, Rent: 1500.0 (Simulated)");
    }

    public void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            bookings.forEach(System.out::println);
        }
    }

    public void payRent(Scanner scanner) {
        System.out.println("\n--- Pay Rent ---");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your credit card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter the amount to pay: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        boolean alreadyPaid = rentPayments.stream().anyMatch(payment -> payment.getName().equals(name));

        if (alreadyPaid) {
            System.out.println("You've already paid the rent.");
        } else {
            RentPayment payment = new RentPayment(name, cardNumber, amount);
            rentPayments.add(payment);
            System.out.println("Rent paid successfully!");
            System.out.println("Details: " + payment);
        }
    }

    public void reportIssue(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Describe your issue: ");
        String description = scanner.nextLine();

        System.out.println("Issue reported successfully. (Simulated)");
        System.out.println("Name: " + name + ", Email: " + email + ", Issue: " + description);
    }

    public void bookHouse(Scanner scanner) {
        System.out.println("\n--- Book a House ---");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter house ID to book: ");
        int houseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Booking booking = new Booking(name, houseId);
        bookings.add(booking);
        System.out.println("House booked successfully!");
        System.out.println("Details: " + booking);
    }
}

// --- Supporting Classes ---
abstract class User {
    private String role;

    public User(String role) {
        this.role = role;
    }

    public abstract void menu(Scanner scanner, RentoWise system);
}

class Admin extends User {
    public Admin(String role) {
        super(role);
    }

    @Override
    public void menu(Scanner scanner, RentoWise system) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add House Details");
            System.out.println("2. View All Houses");
            System.out.println("3. View All Bookings");
            System.out.println("4. Back to Login Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.addHouseDetails(scanner);
                    break;
                case 2:
                    system.viewAllHouses();
                    break;
                case 3:
                    system.viewAllBookings();
                    break;
                case 4:
                    return; // Exit menu loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Tenant extends User {
    public Tenant(String role) {
        super(role);
    }

    @Override
    public void menu(Scanner scanner, RentoWise system) {
        while (true) {
            System.out.println("\n--- Tenant Menu ---");
            System.out.println("1. Pay Rent");
            System.out.println("2. Report Issue");
            System.out.println("3. Book a House");
            System.out.println("4. Back to Login Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.payRent(scanner);
                    break;
                case 2:
                    system.reportIssue(scanner);
                    break;
                case 3:
                    system.bookHouse(scanner);
                    break;
                case 4:
                    return; // Exit menu loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class RentPayment {
    private String name;
    private String cardNumber;
    private double amount;

    public RentPayment(String name, String cardNumber, double amount) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Card Number: " + cardNumber + ", Amount: " + amount;
    }
}

class Booking {
    private String tenantName;
    private int houseId;

    public Booking(String tenantName, int houseId) {
        this.tenantName = tenantName;
        this.houseId = houseId;
    }

    @Override
    public String toString() {
        return "Tenant Name: " + tenantName + ", House ID: " + houseId;
    }
}

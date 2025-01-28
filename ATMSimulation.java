import java.util.*;

public class ATMSimulation {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Account> accounts = new HashMap<>();
   
    public static void main(String[] args) {
        initializeAccounts();
        System.out.println("Welcome to the ATM Simulation!");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeAccounts() {
        accounts.put(101, new Account(101, "John Doe", 1234, 5000.00));
        accounts.put(102, new Account(102, "Jane Smith", 5678, 3000.00));
        accounts.put(103, new Account(103, "Sam Wilson", 9101, 10000.00));
    }

    private static void login() {
        System.out.println("\nEnter your account number:");
        int accountNumber = scanner.nextInt();
        System.out.println("Enter your PIN:");
        int pin = scanner.nextInt();

        Account account = accounts.get(accountNumber);

        if (account != null && account.validatePin(pin)) {
            System.out.println("\nLogin successful. Welcome, " + account.getName() + "!");
            manageAccount(account);
        } else {
            System.out.println("\nInvalid account number or PIN. Please try again.");
        }
    }

    private static void manageAccount(Account account) {
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("$" + depositAmount + " deposited successfully.");
                    break;
                case 3:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawalAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawalAmount)) {
                        System.out.println("$" + withdrawalAmount + " withdrawn successfully.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out. Have a great day, " + account.getName() + "!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Account {
    private int accountNumber;
    private String name;
    private int pin;
    private double balance;

    public Account(int accountNumber, String name, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

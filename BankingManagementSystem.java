import java.util.Scanner;

public class BankingManagementSystem {

    static Scanner sc = new Scanner(System.in);

    // Maximum number of accounts
    static final int MAX = 100;

    // Arrays to store account information
    static int[] accountNumber = new int[MAX];
    static String[] accountHolder = new String[MAX];
    static double[] balance = new double[MAX];

    static int count = 0;

    // Create a new account
    static void createAccount() {

        if (count >= MAX) {
            System.out.println("Account limit reached!");
            return;
        }

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        // Check duplicate account number
        if (findAccount(accNo) != -1) {
            System.out.println("Account number already exists!");
            return;
        }

        sc.nextLine(); // Clear buffer

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double initialDeposit = sc.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }

        accountNumber[count] = accNo;
        accountHolder[count] = name;
        balance[count] = initialDeposit;

        count++;

        System.out.println("Account created successfully!");
    }

    // Find account by account number
    static int findAccount(int accNo) {

        for (int i = 0; i < count; i++) {
            if (accountNumber[i] == accNo) {
                return i;
            }
        }

        return -1;
    }

    // Deposit money
    static void deposit() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        int index = findAccount(accNo);

        if (index == -1) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance[index] += amount;

        System.out.println("Amount deposited successfully!");
        System.out.printf("Current Balance: %.2f%n", balance[index]);
    }

    // Withdraw money
    static void withdraw() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        int index = findAccount(accNo);

        if (index == -1) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance[index]) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance[index] -= amount;

        System.out.println("Amount withdrawn successfully!");
        System.out.printf("Remaining Balance: %.2f%n", balance[index]);
    }

    // Check balance
    static void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        int index = findAccount(accNo);

        if (index == -1) {
            System.out.println("Account not found!");
            return;
        }

        System.out.println("\n----- Account Details -----");
        System.out.println("Account Number : " + accountNumber[index]);
        System.out.println("Account Holder : " + accountHolder[index]);
        System.out.printf("Balance       : %.2f%n", balance[index]);
    }

    // Display all accounts
    static void displayAccounts() {

        if (count == 0) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n========== ALL ACCOUNTS ==========");

        for (int i = 0; i < count; i++) {

            System.out.println("\nAccount Number : " + accountNumber[i]);
            System.out.println("Account Holder : " + accountHolder[i]);
            System.out.printf("Balance        : %.2f%n", balance[i]);
        }
    }

    // Main method
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("      BANKING MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    displayAccounts();
                    break;

                case 6:
                    System.out.println("Thank you for using Banking Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
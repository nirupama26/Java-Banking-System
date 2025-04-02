import java.util.*;

// BankAccount class representing each user's account
class BankAccount {
    private static int nextAccountNumber = 1001;
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String name, double initialDeposit) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolderName = name;
        this.balance = initialDeposit;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Invalid or insufficient amount.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}

public class BankingSystem {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositAmount();
                case 3 -> withdrawAmount();
                case 4 -> displayAccount();
                case 5 -> System.out.println("Exiting. Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

private static void createAccount() {
    scanner.nextLine();  // consume newline
    System.out.print("Enter account holder name: ");
    String name = scanner.nextLine();
    System.out.print("Enter initial deposit: ");
    double deposit = scanner.nextDouble();
    BankAccount account = new BankAccount(name, deposit);
    accounts.add(account);
    System.out.println("Account created successfully!");
    System.out.println("Your Account Number is: " + account.getAccountNumber());
}


    private static void depositAmount() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawAmount() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void displayAccount() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount account = findAccount(accNum);
        if (account != null) {
            account.displayAccountInfo();
        } else {
            System.out.println("Account not found.");
        }
    }

    private static BankAccount findAccount(int accountNumber) {
        return accounts.stream()
                .filter(acc -> acc.getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(null);
    }
}

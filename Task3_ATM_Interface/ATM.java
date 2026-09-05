import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;
    private Account currentAccount;

    public ATM(Bank bank) {

        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("========================================");
        System.out.println("       WELCOME TO JAVA ATM SYSTEM");
        System.out.println("========================================");

        createAccounts();

        if (!login()) {

            System.out.println("\nAccess Denied.");
            System.out.println("Thank you for using Java ATM.");

            return;
        }

        showMenu();
    }

    private void createAccounts() {

        while (true) {

            System.out.println("\n========================================");
            System.out.println("          CREATE NEW ACCOUNT");
            System.out.println("========================================");

            String userId;

            while (true) {

                System.out.print("Create User ID: ");
                userId = scanner.nextLine().trim();

                if (userId.isEmpty()) {

                    System.out.println(
                            "User ID cannot be empty."
                    );

                    continue;
                }

                if (bank.userIdExists(userId)) {

                    System.out.println(
                            "User ID already exists. Try another."
                    );

                    continue;
                }

                break;
            }

            String pin;

            while (true) {

                System.out.print("Create 4-digit PIN: ");
                pin = scanner.nextLine().trim();

                if (pin.matches("\\d{4}")) {
                    break;
                }

                System.out.println(
                        "PIN must contain exactly 4 digits."
                );
            }

            double initialBalance;

            while (true) {

                System.out.print(
                        "Enter Initial Deposit: ₹"
                );

                try {

                    initialBalance =
                            Double.parseDouble(
                                    scanner.nextLine()
                            );

                    if (initialBalance < 0) {

                        System.out.println(
                                "Initial balance cannot be negative."
                        );

                        continue;
                    }

                    break;

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Please enter a valid amount."
                    );
                }
            }

            Account account =
                    bank.createAccount(
                            userId,
                            pin,
                            initialBalance
                    );

            System.out.println("\nAccount created successfully!");
            System.out.println(
                    "Your Account ID: "
                            + account.getAccountId()
            );

            System.out.print(
                    "\nCreate another account? (Y/N): "
            );

            String choice =
                    scanner.nextLine().trim();

            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private boolean login() {

        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        System.out.println("\n========================================");
        System.out.println("                LOGIN");
        System.out.println("========================================");

        while (attempts < MAX_ATTEMPTS) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            Account account =
                    bank.authenticate(userId, pin);

            if (account != null) {

                currentAccount = account;

                System.out.println("\nLogin Successful!");
                System.out.println(
                        "Welcome, "
                                + currentAccount.getUserId()
                                + "!"
                );

                return true;
            }

            attempts++;

            System.out.println(
                    "\nIncorrect User ID or PIN."
            );

            if (attempts < MAX_ATTEMPTS) {

                System.out.println(
                        "Attempts remaining: "
                                + (MAX_ATTEMPTS - attempts)
                );

            } else {

                System.out.println(
                        "Maximum login attempts exceeded."
                );
            }
        }

        return false;
    }

    private void showMenu() {

        while (true) {

            System.out.println("\n========================================");
            System.out.println("               ATM MENU");
            System.out.println("========================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {

                choice =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid choice. Please enter 1-5."
                );

                continue;
            }

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:

                    System.out.println(
                            "\n========================================"
                    );

                    System.out.println(
                            "      Thank you for using Java ATM!"
                    );

                    System.out.println(
                            "========================================"
                    );

                    return;

                default:

                    System.out.println(
                            "Invalid choice. Please select 1-5."
                    );
            }
        }
    }

    private void withdraw() {

        System.out.print(
                "\nEnter withdrawal amount: ₹"
        );

        double amount;

        try {

            amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Amount must be greater than zero."
            );

            return;
        }

        if (amount > currentAccount.getBalance()) {

            System.out.println("Insufficient Funds");

            return;
        }

        currentAccount.withdraw(amount);

        currentAccount.addTransaction(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawal"
                )
        );

        System.out.println(
                "Withdrawal successful!"
        );

        System.out.printf(
                "Remaining Balance: ₹%.2f%n",
                currentAccount.getBalance()
        );
    }

    private void deposit() {

        System.out.print(
                "\nEnter deposit amount: ₹"
        );

        double amount;

        try {

            amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Amount must be greater than zero."
            );

            return;
        }

        currentAccount.deposit(amount);

        currentAccount.addTransaction(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposit"
                )
        );

        System.out.println(
                "Deposit successful!"
        );

        System.out.printf(
                "Updated Balance: ₹%.2f%n",
                currentAccount.getBalance()
        );
    }

    private void transfer() {

        System.out.print(
                "\nEnter recipient Account ID: "
        );

        String recipientId =
                scanner.nextLine().trim();

        if (recipientId.equals(
                currentAccount.getAccountId())) {

            System.out.println(
                    "You cannot transfer money to your own account."
            );

            return;
        }

        Account recipient =
                bank.findAccount(recipientId);

        if (recipient == null) {

            System.out.println(
                    "Recipient account not found."
            );

            return;
        }

        System.out.print(
                "Enter transfer amount: ₹"
        );

        double amount;

        try {

            amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Amount must be greater than zero."
            );

            return;
        }

        if (amount > currentAccount.getBalance()) {

            System.out.println("Insufficient Funds");

            return;
        }

        currentAccount.withdraw(amount);
        recipient.deposit(amount);

        currentAccount.addTransaction(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transferred to Account "
                                + recipientId
                )
        );

        recipient.addTransaction(
                new Transaction(
                        "RECEIVED",
                        amount,
                        "Received from Account "
                                + currentAccount.getAccountId()
                )
        );

        System.out.println(
                "\nTransfer successful!"
        );

        System.out.printf(
                "₹%.2f transferred to Account %s%n",
                amount,
                recipientId
        );

        System.out.printf(
                "Remaining Balance: ₹%.2f%n",
                currentAccount.getBalance()
        );
    }

    private void showTransactionHistory() {

        System.out.println(
                "\n========================================"
        );

        System.out.println(
                "          TRANSACTION HISTORY"
        );

        System.out.println(
                "========================================"
        );

        if (currentAccount.getTransactions().isEmpty()) {

            System.out.println(
                    "No transactions found in this session."
            );

            return;
        }

        for (int i = 0;
             i < currentAccount.getTransactions().size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + currentAccount
                            .getTransactions()
                            .get(i)
            );
        }

        System.out.println(
                "========================================"
        );
    }
}

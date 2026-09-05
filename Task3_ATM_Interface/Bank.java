import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1001;
    }

    public Account createAccount(
            String userId,
            String pin,
            double initialBalance) {

        String accountId = String.valueOf(nextAccountNumber);

        nextAccountNumber++;

        Account account =
                new Account(
                        accountId,
                        userId,
                        pin,
                        initialBalance
                );

        accounts.add(account);

        return account;
    }

    public Account authenticate(
            String userId,
            String pin) {

        for (Account account : accounts) {

            if (account.getUserId().equals(userId)
                    && account.verifyPin(pin)) {

                return account;
            }
        }

        return null;
    }

    public Account findAccount(String accountId) {

        for (Account account : accounts) {

            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }

    public boolean userIdExists(String userId) {

        for (Account account : accounts) {

            if (account.getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }
}

package Accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountsService {

    public AccountsService() {
    }

    public Accounts checkAccounts(Accounts accounts) {

        final Logger logger = LoggerFactory.getLogger(AccountsService.class);
        Accounts result = new Accounts();
        int n = 0;
        for (Account acc : accounts.getAccountList()) {
            if (acc.checkCurrency("PLN") && acc.hasPositiveBalance() && !acc.hasExpired() && acc.hasCorrectIban()) {
                result.add(acc);
            } else {
                n++;
            }
        }
        logger.info("Skipped " + n + " accounts during correctness test.");
        return result;
    }

}

import Accounts.Account;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private Account correctAccount = new Account("PL12345678901234567890123456", "CorrectAccount", "PLN", "100.41", "2030-12-11");
    private Account incorrectAccount = new Account("PLA1234567890123456789012345", "incorrectIban", "123", "-15", "12-2015-12");
    private Account emptyAccount = new Account();
    private String correctCurrency = "PLN";

    @Test
    public void checkCurrencyCorrect() {
        assertTrue(correctAccount.checkCurrency(correctCurrency));
    }

    @Test
    public void checkCurrencyIncorrect() {
        assertFalse(incorrectAccount.checkCurrency(correctCurrency));
    }

    @Test
    public void checkCurrencyTestIgnoreCase() {
        String currency = "pln";
        assertTrue(correctAccount.checkCurrency(currency));
    }

    @Test
    public void checkCurrencyEmpty() {
        assertFalse(emptyAccount.checkCurrency(correctCurrency));
    }

    @Test
    public void hasPositiveBalanceCorrect() {
        assertTrue(correctAccount.hasPositiveBalance());
    }

    @Test
    public void hasPositiveBalanceEmpty() {
        assertFalse(emptyAccount.hasPositiveBalance());
    }

    @Test
    public void hasPositiveBalanceIncorrect() {
        assertFalse(incorrectAccount.hasPositiveBalance());
    }

    @Test
    public void hasExpiredCorrect() {
        assertFalse(correctAccount.hasExpired());
    }

    @Test
    public void hasExpiredIncorrect() {
        assertTrue(incorrectAccount.hasExpired());
    }

    @Test
    public void hasExpiredEmpty() {
        assertFalse(emptyAccount.hasExpired());
    }

    @Test
    public void hasCorrectIbanCorrect() {
        assertTrue(correctAccount.hasCorrectIban());
    }

    @Test
    public void hasCorrectIbanEmpty() {
        assertFalse(emptyAccount.hasCorrectIban());
    }

    @Test
    public void hasCorrectIbanIncorrect() {
        assertFalse(incorrectAccount.hasCorrectIban());
    }


}

import Accounts.Accounts;
import Accounts.Account;
import Accounts.AccountsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountsServiceTest {

    AccountsService service = new AccountsService();

    private Account correctAccount = new Account("PL12345678901234567890123456", "allCorrect", "PLN", "100.41", "2030-12-11");
    private Account incorrectAccount = new Account("PLA1234567890123456789012345", "incorrectIban", "123", "-15", "12-2015-12");
    private Account emptyAccount = new Account();
    private List<Account> list = new ArrayList<>();

    {
        list.add(correctAccount);
        list.add(incorrectAccount);
        list.add(emptyAccount);
    }

    private Accounts notEmpty = new Accounts(list);
    private Accounts empty = new Accounts();

    @Test
    public void checkAccountsCorrect() {
        List<Account> correctResultList = new ArrayList<>();
        correctResultList.add(correctAccount);
        Accounts correctResult = new Accounts(correctResultList);
        assertEquals(correctResult.getAccountList(), service.checkAccounts(notEmpty).getAccountList());
    }

    @Test
    public void checkAccountsEmpty() {
        List<Account> correctResultList = new ArrayList<>();
        assertEquals(correctResultList, service.checkAccounts(empty).getAccountList());
    }

    @Test
    public void checkAccountsIncorrect() {
        List<Account> correctResultList = new ArrayList<>();
        List<Account> incorrectResultList = new ArrayList<>();
        incorrectResultList.add(incorrectAccount);
        Accounts onlyIncorrectAccounts = new Accounts(incorrectResultList);
        assertEquals(correctResultList, service.checkAccounts(onlyIncorrectAccounts).getAccountList());
    }
}

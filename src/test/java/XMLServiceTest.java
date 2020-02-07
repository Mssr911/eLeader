import Accounts.Accounts;
import Accounts.Account;
import XMLService.XmlService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class XMLServiceTest {


    private String testInputPath = "src/main/resources/testInput.xml";
    XmlService service = new XmlService();

    @Test
    public void XMLToObjectTest() {

        //given
        List<Account> correctList = new ArrayList<>();
        Account a1 = new Account("PL61109010140000071219812870", "correctAccount", "PLN", "0", "2029-10-11");
        Account a2 = new Account("PLA1109010140000071219812875", "incorrectIban", "USD", "123.45", "06-2014-10");
        Account a3 = new Account();
        Account a4 = new Account();
        a3.setIban("PL11090101071219812875");
        correctList.add(a1);
        correctList.add(a2);
        correctList.add(a3);
        correctList.add(a4);
        Accounts correctAccounts = new Accounts(correctList);

        //when
        service.objectToXML("testOutput.xml", correctAccounts);
        List<Account> resultList = new ArrayList<>(service.XMLToObject(testInputPath).getAccountList());
        Accounts resultAccount = new Accounts(resultList);

        //then
        assertTrue(correctAccounts.equals(resultAccount));
    }
}

import Accounts.*;
import XMLService.XmlService;

public class Main {

    public static void main(String[] args) {

        XmlService serviceXML = new XmlService();
        AccountsService serviceAccount = new AccountsService();
        String filePath = "src/main/resources/input.xml";

        Accounts accounts = serviceXML.XMLToObject(filePath);

        Accounts finalList = serviceAccount.checkAccounts(accounts);

        finalList.sortByName();

        serviceXML.objectToXML("output.xml", finalList);


    }
}

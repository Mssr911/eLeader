package Accounts;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AccountsHandler extends DefaultHandler {

    private static final String ACCOUNTS = "accounts";
    private static final String ACCOUNT = "account";
    private static final String IBAN = "iban";
    private static final String NAME = "name";
    private static final String CURRENCY = "currency";
    private static final String BALANCE = "balance";
    private static final String CLOSING_DATE = "closingDate";

    private Accounts list;
    private String elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        list = new Accounts();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case ACCOUNTS:
                list.setAccountList(new ArrayList<>());
                break;
            case ACCOUNT:
                list.add(new Account());
                latestAccount().setIban(attr.getValue(IBAN));
        }
    }

    private Account latestAccount() {
        List<Account> accountList = list.getAccountList();
        int latestAccountIndex = accountList.size() - 1;
        return accountList.get(latestAccountIndex);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case NAME:
                latestAccount().setName(elementValue);
                break;
            case CURRENCY:
                latestAccount().setCurrency(elementValue);
                break;
            case BALANCE:
                latestAccount().setBalance(elementValue);
                break;
            case CLOSING_DATE:
                latestAccount().setClosingDate(elementValue);
                break;
        }
    }

    public Accounts getList() {
        return list;
    }

}
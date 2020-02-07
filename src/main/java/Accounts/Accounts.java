package Accounts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@XmlRootElement
public class Accounts {

    private List<Account> accountList = new ArrayList<>();

    public Accounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Accounts() {
    }

    @XmlElement(name = "account")
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void add(Account account) {
        this.accountList.add(account);
    }

    public void sortByName() {
        Collections.sort(accountList, Comparator.comparing(acc -> acc.getName()));
    }

    public boolean equals(Accounts accounts) {
        accounts.sortByName();
        (this).sortByName();

        List<Account> theList = accounts.getAccountList();
        if (accountList.size() != theList.size()) {
            return false;
        } else {
            for (int i = 0; i < accountList.size(); i++) {
                if (!theList.get(i).equals(accountList.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}

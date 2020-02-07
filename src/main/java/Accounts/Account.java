package Accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement
@XmlType(propOrder = {"name", "currency", "balance", "closingDate"})
public class Account {

    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    private String iban = "";
    private String name = "";
    private String currency = "";
    private String balance = "";
    private String closingDate = "";

    public Account(String iban, String name, String currency, String balance, String closingDate) {
        this.iban = iban;
        this.name = name;
        this.balance = balance;
        this.closingDate = closingDate;
        this.currency = currency;
    }

    public Account() {
    }

    @XmlElement
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency.trim();
    }

    @XmlAttribute
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban.trim();
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    @XmlElement
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance.trim();
    }

    @XmlElement
    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate.trim();
    }

    @Override
    public String toString() {
        String result = "\nIBAN: " + iban +
                "\nName: " + name +
                "\nBalance: " + balance +
                "\nCurrency: " + currency +
                "\nClosing date: " + closingDate;
        return result;
    }

    public boolean checkCurrency(String currency) {
        return this.currency.equalsIgnoreCase(currency);
    }

    public boolean hasPositiveBalance() {
        try {
            return Double.valueOf(this.balance) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean hasExpired() {
        try {
            Date actual = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            Date cd = sdf.parse(this.closingDate);
            return cd.before(actual);
        } catch (ParseException e) {
            if (this.closingDate.isEmpty()) {
                return false;
            } else {
                logger.error("Invalid date format \"" + closingDate + "\" for: " + iban);
                return true;
                // Uznałem, że jeśli w danych nie ma daty zamknięcia, to konto nie wygasło,
                // natomiast jeśli została podana data w złym formacie - konto jest odrzucane.
            }
        }
    }

    public boolean hasCorrectIban() {
        return (iban.length() == 28 && iban.substring(0, 2).equals("PL") && (isNumeric(iban.substring(2))));
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean equals(Account account) {
        if (!account.getName().equals(name) ||
                !account.getIban().equals(iban) ||
                !account.getCurrency().equals(currency) ||
                !account.getBalance().equals(balance) ||
                !account.getClosingDate().equals(closingDate)) {
            return false;
        } else {
            return true;
        }
    }
}

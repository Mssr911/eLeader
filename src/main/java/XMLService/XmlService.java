package XMLService;

import Accounts.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlService {

    private static final Logger logger = LoggerFactory.getLogger(XmlService.class);

    static final Accounts FALLBACK_ACCOUNTS = new Accounts();

    public XmlService() {
    }

    public Accounts XMLToObject(String uri) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            AccountsHandler handler = new AccountsHandler();
            saxParser.parse(uri, handler);
            logger.info("File found.");

            Accounts accountList = handler.getList();
            logger.info("File successfully parsed.");

            return accountList;

        } catch (SAXException | IOException e) {
            logger.error("File not found");
        } catch (ParserConfigurationException e) {
            logger.error("Wrong file format.");
        }
        return FALLBACK_ACCOUNTS;
    }

    public void objectToXML(String fileName, Accounts accounts) {
        JAXBContext contextObj;
        if (!accounts.equals(FALLBACK_ACCOUNTS)) {
            try {
                contextObj = JAXBContext.newInstance(Accounts.class);
                Marshaller marshaller = contextObj.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                FileOutputStream outputStream = new FileOutputStream(fileName);
                marshaller.marshal(accounts, outputStream);
                logger.info("Accounts have been saved to XML file: " + fileName);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

package file.csv;

import file.csv.model.Contact;
import file.csv.model.Header;
import file.csv.model.Trailer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class CsvUtils extends CommonCsvUtils {

    private static final String XML_PATH = "csv/Contacts.xml";
    private static final String FILE_PATH = "csv/Contacts.csv";
    private static final String OUT_CONTACT_PATH = "csv/output.csv";
    private static final String NAME = "Contacts";
    // create a BeanIO StreamFactory

    private void addFilePath(){
        this.setXmlPath(XML_PATH);
        this.setFilePath(FILE_PATH);
        this.setName(NAME);
        this.setOutFilePath(OUT_CONTACT_PATH);
    }

    /**
     * @Param header , contact, trailer (map value from file)
     *
     * */
    public void readContactFile(Header header, List<Contact> contact, Trailer trailer) {
        this.addFilePath();
        Map<String, Object> valueMap = this.readFile();
        if(valueMap.get("header") != null){
            this.headerMapper((Header)valueMap.get("header"), header);
        }
        if(valueMap.get("contact") != null){
            Object record = valueMap.get("contact");
            if(record instanceof List){
                List<Object> record1 = (List<Object>) record;
                for (Object o : record1) {
                    contact.add((Contact) o);
                }
            }
        }
        if(valueMap.get("trailer") != null){
            this.trailerMapper((Trailer)valueMap.get("trailer"), trailer);
        }
    }

    public void writeContactFile(Header header, List<Contact> contact, Trailer trailer){
        this.addFilePath();
        this.writeFile(header, contact, trailer);
    }

    private void headerMapper(Header headerFile, Header header) {
        if (Objects.isNull(headerFile)) return;
        header.setFileDate(headerFile.getFileDate());
    }

    private void contactMapper(Contact contactFile, Contact contact) {
        if (Objects.isNull(contactFile)) return;
        contact.setFirstName(contactFile.getFirstName());
        contact.setLastName(contactFile.getLastName());
        contact.setCity(contactFile.getCity());
        contact.setZip(contactFile.getZip());
        contact.setState(contactFile.getState());
        contact.setStreet(contactFile.getStreet());
    }

    private void trailerMapper(Trailer trailerFile, Trailer trailer) {
        if (Objects.isNull(trailer)) return;
        trailer.setRecordCount(trailerFile.getRecordCount());
    }
}

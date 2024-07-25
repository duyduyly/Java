package file;

import file.csv.CsvUtils;
import file.csv.model.Contact;
import file.csv.model.Header;
import file.csv.model.Trailer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CsvUtilsTest {

    @Test
    public void test() {
        CsvUtils csvUtils = new CsvUtils();

        Header header = new Header();
        List<Contact> contacts = new ArrayList<>();
        Trailer trailer = new Trailer();
        csvUtils.readContactFile(header, contacts, trailer);

        System.out.println(header.toString());
        System.out.println("--------------------------");
        contacts.forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println(trailer.toString());

        csvUtils.writeContactFile(header, contacts, trailer);
    }
}

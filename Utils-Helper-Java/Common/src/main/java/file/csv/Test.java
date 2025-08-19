package file.csv;

import file.csv.model.Contact;
import file.csv.model.Header;
import file.csv.model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
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

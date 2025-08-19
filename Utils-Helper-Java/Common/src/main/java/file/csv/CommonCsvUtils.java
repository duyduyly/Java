package file.csv;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCsvUtils {
    private final String ROOT_PATH = "Common/src/main/resources/";
    private String xmlPath;
    private String filePath;
    private String outFilePath;
    private String name;

    protected Map<String, Object> readFile() {
        // create a BeanIO StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file from the working directory
        factory.load(ROOT_PATH + this.xmlPath);
        // create a BeanReader to read from "input.csv"
        BeanReader in = factory.createReader(this.name, new File(ROOT_PATH + this.filePath));

        Object record;
        Map<String, Object> map = new HashMap<>();
        List<Object> bodies = new ArrayList<>();

        // read records from "input.csv"
        while ((record = in.read()) != null) {
            // process each record
            switch (in.getRecordName()) {
                case "header":
                    map.put("header", record);
                    break;
                case "contact":
                    bodies.add(record);
                    break;
                case "trailer":
                    map.put("trailer", record);
                    break;
                default:
                    log.error(in.getRecordName() + " record does not exists");
                    break;
            }
        }
        if (!bodies.isEmpty()) map.put("contact", bodies);
        in.close();
        return map;
    }

    protected void writeFile(Object header, Object bodies, Object trailer) {
        // create a BeanIO StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file from the working directory
        factory.load(getROOT_PATH() + this.xmlPath);
        // create a BeanWriter to write to "output.csv"
        BeanWriter out = factory.createWriter(this.name, new File(ROOT_PATH + this.outFilePath));

        out.write(header);
        for (Object c : (List) bodies) out.write(c);
        out.write(trailer);
        out.flush();
        out.close();
        log.info("Generate the file success!");
    }
}

package file;

import java.io.File;

public class FileUtils {
    public String setPath(String ...path){
        return String.join(File.separator, path);
    }

}

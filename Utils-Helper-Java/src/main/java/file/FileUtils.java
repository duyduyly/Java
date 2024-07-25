package file;

import java.io.File;

public class FileUtils {
//          move file
//          Files.move(Paths.get("PathFile"), "Path want move", StandardCopyOption.REPLACE_EXISTING);

    public String setPath(String ...path){
        return String.join(File.separator, path);
    }

}

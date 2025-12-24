package io_nio2.nio;

import io_nio2.Constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class NIOUtils {

    public static void main(String[] args) throws IOException {
        writeFileWithFiles(Constant.COMMON_OUTPUT_PATH, "sadadadasdasd");
        String read = readFileWithFiles(Constant.COMMON_INPUT_PATH);
        System.out.println(read);

        boolean fileWithFiles = createFileWithFiles("src/resource/file/Output23231.txt");
        System.out.println("Is Created File: "+fileWithFiles);

        boolean isDeletedFile = deleteFileWithFiles("src/resource/file/Output23231.txt");
        System.out.println("Is Deleted File: "+isDeletedFile);

    }

    public static String readFileWithFiles(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return String.join("\n", Files.readAllLines(path));
    }

    public static void writeFileWithFiles(String filePath, String content) throws IOException {
        Path path = Path.of(filePath);
        Files.write(path, content.getBytes());
    }

    public static boolean createFileWithFiles(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        return Files.exists(path);
    }

    public static boolean deleteFileWithFiles(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }

        return !Files.exists(path);
    }
}

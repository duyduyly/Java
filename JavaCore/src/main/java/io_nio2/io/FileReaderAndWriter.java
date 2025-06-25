package io_nio2.io;

import io_nio2.Constant;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderAndWriter {

    public static void main(String[] args) {
        String read = read(Constant.COMMON_INPUT_PATH);
        System.out.println(read);
        write(Constant.COMMON_OUTPUT_PATH, read + "\n \n \"Edited\"");
    }

    public static String read(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int ch;
            while ((ch = reader.read()) != -1) sb.append((char) ch);  // print each character
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }
        return sb.toString();
    }

    public static void write(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + filePath, e);
        }
    }
}

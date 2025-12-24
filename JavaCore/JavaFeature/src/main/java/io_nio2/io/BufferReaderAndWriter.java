package io_nio2.io;

import io_nio2.Constant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BufferReaderAndWriter {

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        bufferedWriter.write("Hello, world!");
        bufferedWriter.newLine(); // like println
        bufferedWriter.flush();   // Print out value into console
        bufferedWriter.close();


        toUpperCaseFromConsole();


        String read = read(Constant.COMMON_INPUT_PATH);
        System.out.println(read);
        write(Constant.COMMON_OUTPUT_PATH, read + " \n \n \"Edited\" ");

        List<String> generateString = Stream.generate(()->read).limit(10).toList();
        write("src/resource/file/Output_List.txt", generateString + "\n \"Generate List String\"");

    }

    public static void toUpperCaseFromConsole(){
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("Exist")) {
                    break; // stop reading
                }
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error Writing file: " , e);
        }
    }

    public static String read(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    public static void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + filePath, e);
        }
    }

    public static void write(String filePath, List<String> contentList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            contentList.forEach(con -> {
                try {
                    writer.write(con);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + filePath, e);
        }
    }
}

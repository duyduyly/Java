package io_nio2.io;

import io_nio2.Constant;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriter_ {

    public static void main(String[] args) {
        write(Constant.COMMON_OUTPUT_PATH);
    }

    public static void write(String filePath) {
        try (PrintWriter pw = new PrintWriter(filePath)) {
            pw.println("Line 1: Hello");
            pw.println("Line 2: World");
            pw.printf("Number: %.2f", 3.14159);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}

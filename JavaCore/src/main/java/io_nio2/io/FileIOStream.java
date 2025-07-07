package io_nio2.io;

import io_nio2.Constant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileIOStream {

    public static void main(String[] args) {
        String stringVal = read(Constant.COMMON_INPUT_PATH);
        System.out.println(stringVal);
        write(Constant.COMMON_OUTPUT_PATH, stringVal.concat("\n \n \"Edited\""));

        byte[] copy = copy("src/resource/file/Image.png");
        paste("src/resource/file/Image_copy.png", copy);

        byte[] textCopy = copy(Constant.COMMON_INPUT_PATH);
        String read = read(textCopy);
        System.out.println(read);
    }


    //read String File
    public static String read(String filePath){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int data;
            while ((data = fis.read()) != -1) {
                stringBuilder.append((char) data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + filePath, e);
        }
        return stringBuilder.toString();
    }

    //Convert From Byte[] to String
    public static String read(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    //Get Byte[] from file
    public static byte[] copy(String filePath){
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error copy file " + filePath, e);
        }
    }

    //create a new File From Byte[] Content
    public static void paste(String filePath, byte[] bytes){
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error paste file " + filePath, e);
        }
    }

    //Write String File
    public static void write(String filePath, String bodyFile){
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] bytes = bodyFile.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + filePath, e);
        }
    }
}

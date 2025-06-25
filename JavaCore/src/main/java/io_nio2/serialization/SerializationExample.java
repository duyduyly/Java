package io_nio2.serialization;

import io_nio2.io.FileIOStream;
import io_nio2.io.ObjectIOStream;
import org.apache.commons.lang3.SerializationUtils;

public class SerializationExample {
    public static void main(String[] args) {

        Student alan = new Student("Alan", 25);
        System.out.println("Initialize Student: " + alan);

        serialize(alan, "src/resource/file/SerializationExample.dat");
        Student deserialize = (Student) deserialize("src/resource/file/SerializationExample.dat");
        System.out.println("Deserialize Student File: "+deserialize.toString());

        System.out.println("-----------------------------");

        byte[] serialize = SerializationUtils.serialize(alan);
        System.out.println("Byte Array to String: "+readBytesToString(serialize));
        System.out.println("Byte Array to String with SerializationUtils: "+ SerializationUtils.deserialize(serialize));
        serialize(serialize, "src/resource/file/SerializationExample_Bytes2.dat");
        Student deserialize2 = (Student) deserialize("src/resource/file/SerializationExample_Bytes2.dat");
        System.out.println("Deserialize Student File: "+deserialize2.toString());
    }

    public static void serialize(Object ob, String fileName){
        ObjectIOStream.write(fileName,ob);
    }

    public static void serialize(byte[] bytesContent, String fileName){
        FileIOStream.paste(fileName, bytesContent);
    }

    public static String readBytesToString(byte[] bytes){
       return FileIOStream.read(bytes);
    }


    //because  private transient String id; ID mark transient, so when deserialize ID will be null
    public static Object deserialize(String fileName){
       return ObjectIOStream.readObject(fileName);
    }
}

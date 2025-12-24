package concurrency;

import com.github.javafaker.Faker;
import concurrency.entity.Package;
import concurrency.entity.PackageEnums;
import concurrency.entity.Snack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {

    public static final File files = new File("src/resource/file/concurrency");
    public static final String OUTPUT_FILE_SOURCE = "src/resource/file/concurrency";
    public static final Faker faker = new Faker();

    public static void main(String[] args) {

        Package aPackage = createPackage();
        int totalPackage = 50;
        aPackage.setTotalSnack(totalPackage);

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 1; i <= totalPackage; i++) {
            Thread thread = new Thread(() -> {
                Snack snack = createSnack();
                snack.setStatus(true);
                aPackage.getSnack().add(snack);

                write("src/resource/file/concurrency" + "/" + aPackage.getName() + "/" + snack.getSnackName() + ".txt", snack.toString());
                for (int i1 = 0; i1 < 10000; i1++) {
                    System.out.println("Thread " + snack.getSnackName() + ":" + "Processing " + i1);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                setDone(aPackage);
            });
            executorService.execute(thread);
        }

        executorService.shutdown();
    }

    public static void setDone(Package aPackage) {
        boolean isTrue = aPackage.getSnack().stream().allMatch(Snack::isStatus);
        if (!files.exists()) {
            return;
        }
        System.out.println("File Length: "+ countFileSize(aPackage.getName()));
        if (
                isTrue
                        && countFileSize(aPackage.getName()) == aPackage.getTotalSnack()
        ) {
            aPackage.setStatus(PackageEnums.DONE.getStatusName());
            System.out.println(aPackage.toStringList());
        }
    }

    public static int countFileSize(String folder){
        File file = new File(OUTPUT_FILE_SOURCE + "/" + folder);
        return file.listFiles().length;
    }

    public static void write(String filePath, String bodyFile) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] bytes = bodyFile.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + filePath, e);
        }
    }


    public static Package createPackage(Snack snack) {
        return Package.builder()
                .name("Package 1")
                .snack(new ArrayList<>())
                .build();
    }

    public static Package createPackage() {
        return Package.builder()
                .name(createPackageName())
                .snack(new ArrayList<>())
                .build();
    }

    public static String createPackageName() {
        StringBuilder packageName = new StringBuilder();
        int index = 1;
        while (true) {
            packageName.append("Package").append("_").append(index);
            if (!doFolderExist(packageName.toString())) {
                createFolder(packageName.toString());
                break;
            } else {
                packageName.delete(0, packageName.length());
                index++;
            }
        }
        return packageName.toString();
    }

    public static boolean doFolderExist(String folder) {
        File file = new File(OUTPUT_FILE_SOURCE + "/" + folder);
        return file.exists();
    }

    public static boolean createFolder(String folder) {
        File file = new File(OUTPUT_FILE_SOURCE + "/" + folder);
        return file.mkdir();
    }

    public static Snack createSnack() {
        return Snack.builder()
                .price(faker.book().hashCode())
                .quantity(faker.address().hashCode())
                .snackName(faker.book().author())
                .status(false)
                .build();
    }
}

package exception;

public class TryCatchResource {
    public static void main(String[] args) throws Exception {
        try(MyFileClass myClass = new MyFileClass(1)) {
            System.out.println("Try block");
        }
        try(MyFileClass myClass2 = new MyFileClass(2)) {
            System.out.println("Try Block");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("Finally Block");
        }


        System.out.println();
        System.out.println("-----------Error While Close in Try-Catch Resource--------------------");
        try (MyResource res = new MyResource()) {
            res.use();
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t);
            }
        }
    }
}

class MyFileClass implements AutoCloseable {
    private final int num;

    public MyFileClass(int num) {
        this.num = num;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing myFileClass #" + num);
    }
}

class MyResource implements AutoCloseable {
    public void close() throws Exception {
        System.out.println("Closing resource");
        throw new Exception("Exception in close");
    }

    public void use() throws Exception {
        System.out.println("Using resource");
        throw new Exception("Exception in use");
    }
}



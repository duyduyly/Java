package java9.inteface;

public class App {

    public static void main(String[] args) {
        PrivateMethodInterface privateMethodInterface = new PrivateMethodInterfaceImpl();
        System.out.println(privateMethodInterface.getCardID());
    }
}

package old_package.java9.inteface;

public interface PrivateMethodInterface {
    private Long createCardID(){
        // Method implementation goes here.
        System.out.println("Create Card ID");
        return 0L;
    }

    default Long getCardID(){
        return createCardID();
    }

    private static void displayCardDetails(){
        // Method implementation goes here.
    }
}

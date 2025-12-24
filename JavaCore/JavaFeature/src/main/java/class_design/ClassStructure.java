package class_design;

public class ClassStructure {

    static {
        System.out.println("Static block");
    }

    {
        System.out.println("Instance block");
    }

    public ClassStructure() {
        System.out.println("Constructor block");
    }

    public void method(){
        System.out.println("method block");
    }

    public static void main(String[] args) {
        ClassStructure classStructure = new ClassStructure();

        System.out.println("Call Method: ");
        classStructure.method();
    }
}

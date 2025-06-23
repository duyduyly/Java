package class_design.records;

public class App {

    public static void main(String[] args) {
        setStudentRecord();
        System.out.println();
        setStudentClass();
        System.out.println();
        equalsInRecord();

    }

    //all this can be replaced by only ONE line:
    public static record Student (String firstName, String lastName, int id){}

    public static record Student2 (String firstName, String lastName, int old, int id){

        // we can override auto-generated constructor
        // this is called "canonical constructor"
//        public Student2 (String firstName, String lastName, int id) {
//            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.id = id;
//        }

        //simpler way => "compact constructor"
        // notice the syntax: no ()
        // instance fields don't need to be explicitly initialized
        // compact constructor could contain any business logic, e.g.
        public Student2 {
            if (id < 10 || id > 1_000_000) throw new IllegalArgumentException();
        }

        public boolean isMature(){
            return this.old > 18;
        }
    }


    //the getter is not like getFirstName(), but firstName() !!
    //toString() is nicely implemented
    public static void setStudentRecord(){
        System.out.println("Student Record");
        var theStudent = new Student("Alan", "Ly", 1);
        System.out.println(theStudent.firstName());
        System.out.println(theStudent.lastName());
        System.out.println(theStudent.id());
        System.out.println("ToString: "+ theStudent.toString());

        var anotherStudent = new Student("Alan", "Ly", 1);
        System.out.println("== In records : " + (theStudent == anotherStudent));
        System.out.println("Equal In records: " + (theStudent.equals(anotherStudent)));

    }

    public static void equalsInRecord(){
        var theStudent = new Student("Alan", "Ly", 1);
        var anotherStudent = new Student("Alan", "Ly", 1);
        System.out.println("== In Records: "+ (theStudent == anotherStudent));
        System.out.println("Equals In Records: "+ (theStudent.equals(anotherStudent)));
    }

    //set Student Class
    public static void setStudentClass(){
        System.out.println("Student Class");
        var theStudent = new class_design.records.Student("Alan", "Ly", 1);
        System.out.println(theStudent.getFirstName());
        System.out.println(theStudent.getLastName());
        System.out.println(theStudent.getId());
        System.out.println("ToString: "+ theStudent.toString());
    }
}

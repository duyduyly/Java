package concurrency.thread;

public class MyClass extends Thread { // the first Extend Thread Class

    //Second override run() method to write your implement
    //you call start() method to run the run method
    @Override
    public void run() {
        System.out.println("Thread :"+ getName() + " is being executed.");
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.start();

        MyClass myClass2 = new MyClass();
        myClass2.start();

        MyClass myClass3 = new MyClass();
        myClass3.start();

        MyClass myClass4 = new MyClass();
        myClass4.start();

        MyClass myClass5 = new MyClass();
        myClass5.start();
    }
}

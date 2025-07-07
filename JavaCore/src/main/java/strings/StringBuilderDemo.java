package strings;

public class StringBuilderDemo {



    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(1)
                .append(2L)
                .append(true)
                .append("And");

        System.out.println(stringBuilder);
        System.out.println(stringBuilder.substring(2,5));

        //replace with large index, replace goes through the end (no exception)
        stringBuilder.replace(2,200,"Alan");
        System.out.println(stringBuilder);

        StringBuilder stringBuilder1 = new StringBuilder("Alan");
        stringBuilder1.reverse();
        System.out.println(stringBuilder1);

        String string = stringBuilder1.toString();

//        stringBuilder.insert(2,"C");
//        stringBuilder.insert(3,"D");
//        System.out.println(stringBuilder);
    }
}

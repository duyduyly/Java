package old_package.utils;

import java.util.List;

public class LogUtils {
    public static void log(String title, Object val) {
        System.out.println();
        System.out.println("------------Start " + title + "-----------");
        if(val instanceof List<?>) {
            for (Object ob : (List<?>) val) {
                System.out.println(ob.toString());
            }
        }else {
            System.out.println(val.toString());
        }
        System.out.println("------------End " + title + "-----------");
        System.out.println();
    }
}

package old_package.config;

import com.github.javafaker.Faker;

public class JavaConfig {
    public static Faker faker;

    static {
        faker = new Faker();
    }
}

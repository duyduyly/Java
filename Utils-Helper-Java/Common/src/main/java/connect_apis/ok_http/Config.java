package connect_apis.ok_http;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

//use @Configuration when use spring boot
public class Config {

    private int connectTimeout = 150;
    private int writeTimeout = 150;
    private int readTimeout = 150;

    //use @Bean when use spring boot
    //create a bean for OkHttpClient when use @Autowired will call this bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }
}

package old_package.java9;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Api {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("https://api.restful-api.dev/objects");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("request was: " + response.request());
        System.out.println("headers was: " + response.headers());
        System.out.println("statusCode: " + response.statusCode());
        System.out.println("Response was: " + response.body());
        System.out.println("previousResponse: " + response.previousResponse());

        String decimal = "123123.12313141241241";
        DecimalFormat df = new DecimalFormat("##.##");
        String format = df.format(new BigDecimal(decimal));
        Function<String, BigDecimal> function = x -> Objects.isNull(x) ?
                BigDecimal.ZERO : new BigDecimal(format);
        System.out.println("function: " + function.apply(decimal));

        String api = getApi("https://api.restful-api.dev/objects", null, null);
        Object ob = mapApi(api, Product.class);
        System.out.println(ob);

    }

    private static String getApi(String url, Map<String, String> headers, Map<String, String> params) {
        String responseReturn = "";
        try{
            URI uri = new URI(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseReturn = response.body();
        }catch (Exception e){
            System.err.println(e.getMessage());
            return "";
        }

        return responseReturn;

    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Object mapApi(String json, Class<?> ob){
        try{
            return objectMapper.readValue(json, ob);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    //"data":{"color":"Cloudy White","capacity":"128 GB"}

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Product{
        private long id;
        private String name;
        private Map<String, String> data;
    }
}

package connect_apis.ok_http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

import static connect_apis.ok_http.Constant.*;

@Slf4j
public class OkHttpUtils {

    //can use @Autowired when use spring boot
    private OkHttpClient client;

    public OkHttpUtils() {
        client = new Config().okHttpClient();
    }

    public Response request(String url, String jsonRequest, String method) {
        MediaType mediaType = MediaType.parse(MEDIA_TYPE);
        RequestBody requestBody = RequestBody.create(mediaType, jsonRequest);

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .header(CONTENT_TYPE, MEDIA_TYPE)
                    .method(method, requestBody)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            this.logCode(response);

            return response;
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public Response request(String url, String jsonRequest, String method, Map<String, String> headers) {
        MediaType mediaType = MediaType.parse(MEDIA_TYPE);
        RequestBody requestBody = RequestBody.create(mediaType, jsonRequest);

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .method(method, requestBody)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            this.logCode(response);

            return response;
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public Response get(String baseUrl, Map<String, String> params) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl).newBuilder();

        if (params.size() > 0) {
            params.forEach(urlBuilder::addQueryParameter);
        }

        String url = urlBuilder.build().toString();
        try {

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            this.logCode(response);

            return response;
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public void logCode(Response response) throws IOException {
        if (!checkCode(response)) {
            log.error(response.peekBody(2048).string());
            log.error(response.message());
            throw new BadRequestException(response.message());
        }
    }

    public boolean checkCode(Response response) {
        return response.code() >= 200 && response.code() < 300;
    }


}

package connect_apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import connect_apis.ok_http.OkHttpUtils;
import okhttp3.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static connect_apis.ok_http.Constant.PATCH_METHOD;
import static connect_apis.ok_http.Constant.POST_METHOD;

public class OkHttpUtilsTest {

    private final OkHttpUtils okHttpUtils;
    private final ObjectMapper objectMapper;
    private final Map<String, Object> bodyMap;
    private final Map<String, String> headerMap;

    public OkHttpUtilsTest() {
        okHttpUtils = new OkHttpUtils();
        objectMapper = new ObjectMapper();

        bodyMap = new HashMap<>();
        bodyMap.put("key1", "value1");
        bodyMap.put("key2", "value2");

        headerMap = new HashMap<>();
        headerMap.put("header1", "value1");
        headerMap.put("header2", "value2");
    }

    @Test
    public void postAndPatchMethod() throws JsonProcessingException {
        String url = "";
        String request = objectMapper.writeValueAsString(bodyMap);
        Response response = okHttpUtils.request(url, request, POST_METHOD);
        Response response2 = okHttpUtils.request(url, request, PATCH_METHOD);
        Response response3 = okHttpUtils.request(url, request, POST_METHOD, headerMap);
        Response response4 = okHttpUtils.request(url, request, PATCH_METHOD, headerMap);

        System.out.println(response);
        System.out.println(response2);
        System.out.println(response3);
        System.out.println(response4);

    }

    @Test
    public void getMethod() {
        String url = "";
        Map<String, String> params = new HashMap<>();
        params.put("param1", "value1");
        params.put("param2", "value2");
        Response response = okHttpUtils.get(url, params);

        System.out.println(response);
    }
}

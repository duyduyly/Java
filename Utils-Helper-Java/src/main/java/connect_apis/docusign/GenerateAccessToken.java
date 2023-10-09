package connect_apis.docusign;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static connect_apis.docusign.Constant.*;

public class GenerateAccessToken {

    private final ApiClient apiClient;

    public GenerateAccessToken() {
        apiClient = new ApiClient(BASE_PATH);

    }

    //generate and write Access token in to file OAuthToken.json
    public void generateAccessToken() throws IOException, ApiException {
        ArrayList<String> requestScopes = new ArrayList<>();
        requestScopes.add(OAuth.Scope_SIGNATURE);
        requestScopes.add(OAuth.Scope_IMPERSONATION);
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(RSA_KEY_PATH));
        OAuth.OAuthToken oAuthToken = apiClient.requestJWTUserToken(
                CLIENT_ID,
                USER_ID,
                requestScopes,
                privateKeyBytes,
                EXPIRES_IN);
        write(oAuthToken);
    }

    private void write(OAuth.OAuthToken oAuthToken) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(OAUTH_TOKEN_PATH), oAuthToken);
    }
}

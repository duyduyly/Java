package connect_apis;

import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth;
import connect_apis.docusign.GenerateAccessToken;
import connect_apis.docusign.MyDocusign;
import org.junit.Test;

import java.io.IOException;

public class DocusignTest {

    private MyDocusign myDocusign;
    private GenerateAccessToken generateAccessToken;

    public DocusignTest() {
        generateAccessToken = new GenerateAccessToken();
        myDocusign = new MyDocusign();
    }

    @Test
    public void runDocusign() throws IOException, ApiException {
        String signerEmail = "Alanly@gmail.com";
        String signerName = "Alan";

        //the first //we will generate and write accessToken into File OAuthToken.json
        generateAccessToken.generateAccessToken();

        //next
        //read AccessToken From File OAuthToken.json
        OAuth.OAuthToken oAuthToken = myDocusign.read();

//      and then we will transmit in CreateEnvelope Method to create Docusign and Finally, email will retrieve file and sign
//      can custom for document of docusign
//      https://developers.docusign.com/docs/esign-rest-api/how-to/list-envelope-documents/
        myDocusign.createEnvelope(signerEmail, signerName, oAuthToken);
    }
}

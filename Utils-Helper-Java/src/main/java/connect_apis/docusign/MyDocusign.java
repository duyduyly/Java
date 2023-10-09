package connect_apis.docusign;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth;
import com.docusign.esign.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

import static connect_apis.docusign.Constant.*;

public class MyDocusign {

    private final ApiClient apiClient;

    public MyDocusign() {
        apiClient = new ApiClient(BASE_PATH);

    }

    public String createEnvelope(String signerEmail, String signerName, OAuth.OAuthToken oAuthToken) throws ApiException {
        apiClient.setAccessToken(oAuthToken.getAccessToken(), oAuthToken.getExpiresIn());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

        // Create envelopeDefinition object
        EnvelopeDefinition envelope = new EnvelopeDefinition();
        envelope.setEmailSubject("Please sign this document set");

        // Create tabs object
        SignHere signHere = new SignHere();
        signHere.setDocumentId("1");
        signHere.setPageNumber("1");
        signHere.setXPosition("191");
        signHere.setYPosition("148");

        SignHere signHere2 = new SignHere();
        signHere.setDocumentId("2");
        signHere2.setPageNumber("2");
        signHere2.setXPosition("191");
        signHere2.setYPosition("148");
//        signHere2.setHeight("");
        signHere2.setWidth("816");


        Tabs tabs = new Tabs();
        tabs.setSignHereTabs(Arrays.asList(signHere));
        // Set recipients
        Signer signer = new Signer();
        signer.setEmail(signerEmail);
        signer.setName(signerName + " executed: " + timestamp.toString());
        signer.recipientId("1");
        signer.setTabs(tabs);

        Recipients recipients = new Recipients();
        recipients.setSigners(Arrays.asList(signer));
        envelope.setRecipients(recipients);

        // Add document
        Document document = new Document();
        document.setDocumentBase64("VGhhbmtzIGZvciByZXZpZXdpbmcgdGhpcyEKCldlJ2xsIG1vdmUgZm9yd2FyZCBhcyBzb29uIGFzIHdlIGhlYXIgYmFjay4=");
        document.setName("doc1.txt");
        document.setFileExtension("txt");
        document.setDocumentId("1");

        // Add document
        Document document2 = new Document();
        document2.setDocumentBase64(BASE64_EXCEL);
        document2.setName("Template_Submission.xls");
        document2.setFileExtension("xls");
        document2.setDocumentId("2");
        envelope.setDocuments(Arrays.asList(document, document2));

        envelope.setStatus("sent");

        EnvelopeSummary results = envelopesApi.createEnvelope(ACCOUNT_ID, envelope);
        return results.getEnvelopeId();

    }

    public OAuth.OAuthToken read() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(OAUTH_TOKEN_PATH), OAuth.OAuthToken.class);
    }


}

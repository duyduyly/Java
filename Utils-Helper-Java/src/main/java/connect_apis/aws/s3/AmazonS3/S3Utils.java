package connect_apis.aws.s3.AmazonS3;

import java.io.IOException;
import java.io.InputStream;

public interface S3Utils {

    //From is bucketName in AmazonS3Utils
    void moveFileToNewS3Location(String sourceObjectKey, String targetObjectKey, String bucketNameTo);

    String readFileS3ToString(String fileName) throws Exception;

    InputStream downloadFileFromS3(String fileName) throws Exception;

    void upload(String path, byte[] content) throws IOException;
}

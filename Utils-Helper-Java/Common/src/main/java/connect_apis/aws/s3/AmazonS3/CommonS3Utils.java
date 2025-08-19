package connect_apis.aws.s3.AmazonS3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CommonS3Utils {

    public void moveFileToNewS3Location(AmazonS3 amazonS3, String sourceObjectKey, String targetObjectKey, String bucketNameFrom, String bucketNameTo) {
        try {
            amazonS3.copyObject(bucketNameFrom, sourceObjectKey, bucketNameTo, targetObjectKey);
            amazonS3.deleteObject(bucketNameFrom, sourceObjectKey);
        } catch (AmazonServiceException asx) {
            log.error("Error while moving file - '{0}' to '{1}' in S3 bucket '{2}'- {3}",
                    sourceObjectKey, targetObjectKey, bucketNameFrom, asx);
            throw asx;
        }
    }


    public String readFileS3ToString(AmazonS3 amazonS3, String fileName, String bucketName) throws Exception {
        InputStream inputStream = this.downloadFileFromS3(amazonS3, fileName, bucketName);
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining());
    }

    public InputStream downloadFileFromS3(AmazonS3 amazonS3, String fileName, String bucketName) throws Exception {
        try {
            S3Object s3Object = amazonS3.getObject(bucketName, fileName);
            return s3Object.getObjectContent();
        } catch (Exception asx) {
            log.error("Error while downloading file - '{0}' to S3 bucket '{1}' - {2}", bucketName, fileName, asx);
            throw asx;
        }
    }

    public void upload(AmazonS3 amazonS3, String path, byte[] contentFile, String bucketName) throws IOException {
        try {
            File file = new File(String.valueOf(Files.write(Paths.get(path), contentFile)));
            amazonS3.putObject(bucketName, path, file);
            file.delete();
        } catch (Exception asx) {
            log.error("Upload File Error");
            throw asx;
        }
    }

}

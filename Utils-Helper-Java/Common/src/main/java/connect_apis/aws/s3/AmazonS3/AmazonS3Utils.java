package connect_apis.aws.s3.AmazonS3;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;

//use for AmazonS3 because AmazonS3 Just Config for a BucketName
public class AmazonS3Utils implements S3Utils {
    @Autowired
//    @Qualifier(BEAN_NAME)
    private AmazonS3 amazonS3;

    @Value("${}")
    private String bucketName;

    @Autowired
    private CommonS3Utils commonS3Utils;

    @Override
    public void moveFileToNewS3Location(String sourceObjectKey,
                                        String targetObjectKey,
                                        String bucketNameTo
    ) {
        commonS3Utils.moveFileToNewS3Location(
                amazonS3,
                sourceObjectKey,
                targetObjectKey,
                bucketName,
                bucketNameTo
        );
    }

    @Override
    public String readFileS3ToString(String fileName) throws Exception {
        return commonS3Utils.readFileS3ToString(
                amazonS3,
                fileName,
                bucketName
        );
    }

    @Override
    public InputStream downloadFileFromS3(String fileName) throws Exception {
        return commonS3Utils.downloadFileFromS3(
                amazonS3,
                fileName,
                bucketName
        );
    }

    @Override
    public void upload(String path, byte[] content) throws IOException {
        commonS3Utils.upload(amazonS3, path, content, bucketName);
    }
}

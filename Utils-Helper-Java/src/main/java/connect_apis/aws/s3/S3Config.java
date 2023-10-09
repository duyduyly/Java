package connect_apis.aws.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3Config {
    @Value("${}")
    private String bucketName;

    @Value("${}")
    private String accessKey;

    @Value("${}")
    private String secretKey;

    @Value("${}")
    private String region;

    public static String AWS_S3_DOMAIN_PATH = "";
    public static String AWS_S3_BUCKET_NAME = "";

    private static final String aws_service_name = "s3";


    // config Amazons3 Just can access a BucketName and can Upload File, Download, Move or delete
    public AmazonS3 createAmazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        AWS_S3_BUCKET_NAME = bucketName;
        AWS_S3_DOMAIN_PATH = "https://" + AWS_S3_BUCKET_NAME + "." + aws_service_name + "." + region + ".amazonaws.com";

        return s3;
    }

    //config S3Client
    //S3Client will Support a various Function to access with s3
    //Include: Create BucketName, Rename and can Delete, or Get List BucketName in that s3
    // it also have method to access With file in a BucketName Upload, remove, Download, Move
    public S3Client getS3DataProcessing() {
        final S3Client s3 = S3Client.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(getAwsCredentials(accessKey, secretKey)).build();
        return s3;
    }

    private AwsCredentialsProvider getAwsCredentials(String accessKeyID, String secretAccessKey) {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyID, secretAccessKey);
        AwsCredentialsProvider awsCredentialsProvider = () -> awsBasicCredentials;
        return awsCredentialsProvider;
    }
}

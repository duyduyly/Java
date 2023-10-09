package connect_apis.aws.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//Document From https://www.baeldung.com/java-aws-s3

/**
 * key == fileName
 * message == content
 */
@Slf4j
public class S3ClientUtils {

    @Autowired
//    @Qualifier(BEAN_NAME)
    private S3Client s3Client;

    /**
     * @param key is path of File Or FileName
     * @Param Message is content of File
     * @Param bucketName is BucketName you want Upload File
     */
    public void upload(String message, String key, String bucketName) {
        log.info("file uploaded " + key);
        PutObjectRequest request = PutObjectRequest.builder()
                .key(key)
                .bucket(bucketName)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(message.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @MethodName getListBucks
     * return ListBucketsResponse
     * in ListBucketsResponse have Owner and List<Bucket>
     * --> in List Bucket we can get All BucketName and create date
     */
    public ListBucketsResponse getListBucks() {
        return s3Client.listBuckets();
    }

    /**
     * @param key is Path of file
     */
    public byte[] downloadFile(String key, String buckName) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(buckName)
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> responseResponseBytes = s3Client.getObjectAsBytes(objectRequest);
        return responseResponseBytes.asByteArray();
    }

    /**
     * @param sourceBucketName      and sourceKey is bucketName and file Path we want coppy
     * @param destinationBucketName and destination key is The place store file bucketName and fileName
     */
    public CopyObjectResponse copy(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) {
        CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                .sourceBucket(sourceBucketName)
                .sourceKey(sourceKey)
                .destinationBucket(destinationBucketName)
                .destinationKey(destinationKey)
                .build();

        return s3Client.copyObject(copyObjectRequest);
    }

    public DeleteObjectResponse delete(String key, String bucketName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.deleteObject(deleteObjectRequest);
    }

    public DeleteObjectsResponse deleteMultipleFile(String bucketName, List<String> keys) {
        ArrayList<ObjectIdentifier> toDelete = new ArrayList<>();
        for (String objKey : keys) {
            toDelete.add(ObjectIdentifier.builder()
                    .key(objKey)
                    .build());
        }

        DeleteObjectsRequest deleteObjectRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(Delete.builder()
                        .objects(toDelete).build())
                .build();

        return s3Client.deleteObjects(deleteObjectRequest);
    }

    public void createBucketName(String bucketName) {
        if (this.existBucketName(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }

        CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.createBucket(bucketRequest);
    }

    public boolean existBucketName(String newBucketName) {
        List<Bucket> buckets = this.getListBucks().buckets();
        return buckets.stream().anyMatch(oldBucketName -> oldBucketName.name().equals(newBucketName));
    }

}

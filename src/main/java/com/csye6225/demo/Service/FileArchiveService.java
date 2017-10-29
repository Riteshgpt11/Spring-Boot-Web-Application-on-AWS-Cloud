package com.csye6225.demo.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.csye6225.demo.entity.MediaFile;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;

@Service
public class FileArchiveService {

    private static final String S3_BUCKET_NAME = "code-deploy.csye6225-fall2017-guptarite.me";
    @Autowired
    private AmazonS3 s3Client;
    @Value("${jsa.aws.access_key_id}")
    private String awsId;

    @Value("${jsa.aws.secret_access_key}")
    private String awsKey;

    @Value("${jsa.s3.region}")
    private String region;

    /**
     * Save image to S3 and return CustomerImage containing key and public URL
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public MediaFile saveFileToS3(MultipartFile multipartFile) throws FileArchiveServiceException, IOException {

//        try{
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

//        s3client.putObject(new PutObjectRequest(bucketName, keyName,is,new ObjectMetadata()));
//        File convFile = new File(multipartFile.getOriginalFilename());
//        multipartFile.transferTo(convFile);
//            return convFile;
//        File fileToUpload = convFile;
        InputStream is = multipartFile.getInputStream();
        String key = Instant.now().getEpochSecond() + "_" + multipartFile.getName();
        String fileName = multipartFile.getOriginalFilename();

//        s3Client.
            /* save file */
        s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, is, new ObjectMetadata()));

            /* get signed URL (valid for one year) */
//        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
//        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
//        generatePresignedUrlRequest.setExpiration(DateTime.now().plusYears(1).toDate());

        URL signedUrl = s3Client.getUrl(S3_BUCKET_NAME, key);
//                .generatePresignedUrl(generatePresignedUrlRequest);

        return new MediaFile(key, signedUrl.toString(), fileName.toString());
//        }
//        catch(Exception ex){
//            throw new FileArchiveServiceException("An error occurred saving file to S3", ex);
//        }
    }


    /**
     * Delete image from S3 using specified key
     *
     * @param mediaFile
     */
    public void deleteFileFromS3(MediaFile mediaFile) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, mediaFile.getKey()));
    }
}

/**
 * rohan magare, 001231457, magare.r@husky.neu.edu
 * ritesh gupta, 001280361, gupta.rite@husky.neu.edu
 * pratiksha shetty, 00121643697, shetty.pr@husky.neu.edu
 * yogita jain, 001643815, jain.yo@husky.neu.edu
 **/
package com.csye6225.demo.Service;

import com.amazonaws.auth.*;
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.csye6225.demo.entity.MediaFile;
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
  
    private static final String S3_BUCKET_NAME = "ec2.csye6225-fall2017-magarer.me.csye6225.com";


    private AmazonS3 s3Client;

    @Value("${aws.AWS_ACCESS_KEY_ID}")
    private String awsId;

    @Value("${aws.AWS_SECRET_ACCESS_KEY}")
    private String awsKey;

    //@Value("$spring.datasource.region")
    private String region="us-east-1";


    /**
     * Save image to S3 and return MediaFile containing key and public URL
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public MediaFile saveFileToS3(MultipartFile multipartFile) throws FileArchiveServiceException, IOException {
        //s3Client = AmazonS3ClientBuilder.defaultClient();
        //s3Client = new AmazonS3Client(EnvironmentVariableCredentialsProvider().getCredentials());
        try {

            //s3Client = new AmazonS3Client(new ProfileCredentialsProvider().getCredentials());
            //s3Client = AmazonS3ClientBuilder.standard()
             //       .withCredentials(new (ProfileCredentialsProvider()))
               //     .build();

            BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
            s3Client = AmazonS3ClientBuilder.standard()               //.withRegion(region)
                    .withRegion(Regions.fromName(region))
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                   .build();

            InputStream is = multipartFile.getInputStream();
            String key = Instant.now().getEpochSecond() + "_" + multipartFile.getName();
            String fileName = multipartFile.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, is, new ObjectMetadata()));


                    //.standard()
                    //.withCredentials(new InstanceProfileCredentialsProvider(false))
                    //.build();
           // s3Client = new AmazonS3Client(DefaultAWSCredentialsProviderChain.getInstance());
            //s3Client = new AmazonS3Client(new ProfileCredentialsProvider(DefaultAWSCredentialsProviderChain.getInstance()));

            /* save file */
            //s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, is, new ObjectMetadata()));
            URL signedUrl = s3Client.getUrl(S3_BUCKET_NAME, key);
            return new MediaFile(key, signedUrl.toString(), fileName.toString());
        } catch (FileArchiveServiceException ex) {
            throw new FileArchiveServiceException("An error occurred saving file to S3", ex);
        }
    }


    /**
     * Delete image from S3 using specified key
     *
     * @param mediaFile
     */
    public void deleteFileFromS3(MediaFile mediaFile) {

       /* BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsId, awsKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();*/

       //s3Client = new AmazonS3Client(new ProfileCredentialsProvider());

        s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, mediaFile.getKey()));
    }
}

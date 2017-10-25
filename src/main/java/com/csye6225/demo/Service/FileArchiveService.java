package com.csye6225.demo.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.csye6225.demo.entity.MediaFile;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

@Service
public class FileArchiveService {

    @Autowired
    private AmazonS3Client s3Client;

    private static final String S3_BUCKET_NAME = "code-deploy.csye6225-fall2017-guptarite.me";


    /**
     * Save image to S3 and return CustomerImage containing key and public URL
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public MediaFile saveFileToS3(MultipartFile multipartFile) throws FileArchiveServiceException {

        try{

            File convFile = new File( multipartFile.getOriginalFilename());
            multipartFile.transferTo(convFile);
//            return convFile;
            File fileToUpload = convFile;
            String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();

            /* save file */
            s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));

            /* get signed URL (valid for one year) */
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(DateTime.now().plusYears(1).toDate());

            URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            return new MediaFile(key, signedUrl.toString());
        }
        catch(Exception ex){
            throw new FileArchiveServiceException("An error occurred saving file to S3", ex);
        }
    }


    /**
     * Delete image from S3 using specified key
     *
     * @param mediaFile
     */
    public void deleteFileFromS3(MediaFile mediaFile){
        s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, mediaFile.getKey()));
    }
}

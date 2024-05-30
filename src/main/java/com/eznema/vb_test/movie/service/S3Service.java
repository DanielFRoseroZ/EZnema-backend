package com.eznema.vb_test.movie.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
@Log4j2
public class S3Service {
    private final AmazonS3 s3Client;

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        String key = Paths.get(System.currentTimeMillis() + "_" + file.getOriginalFilename()).toString();

        s3Client.putObject(bucketName, key, file.getInputStream(), null);

        return s3Client.getUrl(bucketName, key).toString();
    }


}

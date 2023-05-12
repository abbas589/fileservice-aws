package com.bazztech.fileservice.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.bazztech.fileservice.AwsAppProperties;
import com.bazztech.fileservice.domain.entity.enumeration.StatusEnum;

import com.bazztech.fileservice.domain.dto.UploadFileResponse;
import com.bazztech.fileservice.domain.entity.File;
import com.bazztech.fileservice.integration.exception.ErrorResponse;
import com.bazztech.fileservice.repository.FileRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author bazz
 * May 07 2023
 * 13:16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    private final AmazonS3 amazonS3;
    private final AwsAppProperties awsAppProperties;
    private final Gson gson;

    public UploadFileResponse uploadFile(MultipartFile newFile) throws IOException {
        File file = new File();
        file.setCreatedAt(LocalDateTime.now());
        file.setStatus(StatusEnum.ACTIVE);
        file.setData(newFile.getBytes());
        file.setContentType(newFile.getContentType());
        file.setFileName(newFile.getOriginalFilename());
        file.setFileKey(getKey(newFile.getOriginalFilename()));
        fileRepository.save(file);
        saveToAwsBucket(newFile,file.getFileKey());
        return new UploadFileResponse(file.getFileName(), file.getContentType(), file.getId());

    }

    String getKey(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }

    private void saveToAwsBucket(MultipartFile file,String key) throws IOException {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());
        PutObjectRequest putObjectRequest = new PutObjectRequest(awsAppProperties.getBucketName(), key, file.getInputStream(), data);
        amazonS3.putObject(putObjectRequest);

    }

    private S3Object getAwsFile(String objectKey){
       return amazonS3.getObject(awsAppProperties.getBucketName(),objectKey);
    }

    public UploadFileResponse getFile(Long id) throws IOException {

        File file = fileRepository.findById(id).orElseThrow(()->new ErrorResponse(400,"File not found"));

        S3Object awsFile = getAwsFile(file.getFileKey());
        if(Objects.isNull(awsFile)){
            throw new ErrorResponse(400,
                    "File is missing in Storage.");
        }
        log.info(gson.toJson(awsFile));
        return new UploadFileResponse(file.getFileName(), file.getContentType(), file.getId(),awsFile.getObjectContent().readAllBytes());

    }

    public RedirectView openFile(long id, HttpServletResponse response) throws IOException {
        UploadFileResponse uploadFileResponse = getFile(id);
        response.setContentType(uploadFileResponse.getContentType());
        response.setHeader("Content-Disposition", String.format("%s; filename=%s;", "inline", uploadFileResponse.getFileName()));
        IOUtils.write(uploadFileResponse.getData(), response.getOutputStream());
        response.flushBuffer();
        return null;
    }
}

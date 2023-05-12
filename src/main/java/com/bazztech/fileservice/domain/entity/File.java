package com.bazztech.fileservice.domain.entity;

import com.bazztech.fileservice.domain.entity.enumeration.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author bazz
 * May 07 2023
 * 12:41
 */
@Document
public class File {
    @Id
    private Long id;

    private LocalDateTime createdAt;
    private StatusEnum status;

    private String url;
    private String fileKey;
    private byte[] data;
    private LocalDateTime urlUpdatedAt;
    private String contentType;
    private String fileName;

    @Transient
    public static final String SEQUENCE_NAME = "file_sequence";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LocalDateTime getUrlUpdatedAt() {
        return urlUpdatedAt;
    }

    public void setUrlUpdatedAt(LocalDateTime urlUpdatedAt) {
        this.urlUpdatedAt = urlUpdatedAt;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }
}

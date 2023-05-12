package com.bazztech.fileservice.domain.dto;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String contentType;
    private Long fileId;
    private long size;
    private byte[] data;

    public UploadFileResponse(String fileName, String contentType, Long fileId) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileId = fileId;
    }
    public UploadFileResponse(String fileName, String contentType, Long fileId, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileId = fileId;
        this.data = data;
    }

}

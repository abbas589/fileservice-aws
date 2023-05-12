package com.bazztech.fileservice.controller;

import com.bazztech.fileservice.domain.dto.UploadFileResponse;
import com.bazztech.fileservice.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

/**
 * @author bazz
 * May 07 2023
 * 13:02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        UploadFileResponse response = fileService.uploadFile(file);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UploadFileResponse> getFile(@PathVariable Long  id) throws IOException {
        UploadFileResponse response = fileService.getFile(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id:[0-9]+}/open")
    public RedirectView openFile(@PathVariable("id")
                                 long id, HttpServletResponse response) throws IOException {
        return fileService.openFile(id, response);
    }


}

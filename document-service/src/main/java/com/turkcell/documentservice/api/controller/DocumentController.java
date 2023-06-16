package com.turkcell.documentservice.api.controller;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import com.turkcell.documentservice.constant.GlobalConstant;
import com.turkcell.documentservice.exception.MediaNotFoundException;
import com.turkcell.documentservice.service.DocumentService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> create(@RequestParam("files") MultipartFile[] files,
                                                   @RequestParam("productId") String productId){
        return ResponseEntity.ok(documentService.create(files,UUID.fromString(productId)));
    }

    @GetMapping
    public ResponseEntity<DocumentResponse> getDocumentsByProductId(@RequestParam String productId){
        return ResponseEntity.ok(documentService.getDocumentsByProductId(UUID.fromString(productId)));
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        InputStream in = null;

        try{
            in = findFileAndCreateInputStream(fileName);
        }catch(FileNotFoundException ex){
            throw new MediaNotFoundException(ex.getMessage());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(new InputStreamResource(in));
    }

    private InputStream findFileAndCreateInputStream(String fileName) throws FileNotFoundException {
        final File file = new File(GlobalConstant.File.IMAGES_PATH + fileName);

        if (!file.exists()) {
            throw new FileNotFoundException(GlobalConstant.File.FILE_NOT_FOUND);
        }

        return new FileInputStream(file);
    }
}

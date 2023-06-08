package com.turkcell.documentservice.service.impl;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import com.turkcell.documentservice.constant.GlobalConstant;
import com.turkcell.documentservice.exception.InvalidMediaTypeException;
import com.turkcell.documentservice.exception.MediaTransferException;
import com.turkcell.documentservice.model.Document;
import com.turkcell.documentservice.repository.DocumentRepository;
import com.turkcell.documentservice.service.DocumentService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentResponse create(MultipartFile[] files, UUID productId) {
        checkFilesType(files);
        List<String> documents = moveDocumentToFolder(files,productId);

        return new DocumentResponse(documents);
    }

    @Override
    public DocumentResponse getDocumentsByProductId(UUID productId) {
        return new DocumentResponse(repository.findAllByProductId(productId)
                .stream()
                .map(document -> document.getUrl())
                .toList());
    }

    private List<String> moveDocumentToFolder(MultipartFile[] files, UUID productId){
        List<String> documents = new ArrayList<>();

        for(MultipartFile file : files){
            String multipartFile = file.getOriginalFilename();
            String fileType = multipartFile
                    .substring(multipartFile.lastIndexOf(GlobalConstant.File.FILE_SPLIT_CHARACTER) + 1);

            String filename = UUID.randomUUID() + GlobalConstant.File.FILE_SPLIT_CHARACTER + fileType;
            File newFile = new File(GlobalConstant.File.IMAGES_PATH + filename);

            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
            } catch (IOException e) {
                throw new MediaTransferException(e.getMessage());
            }

            saveDocument(productId, filename);
            documents.add(filename);
        }

        return documents;
    }

    private void saveDocument(UUID productId, String filename) {
        final Document document = new Document();

        document.setProductId(productId);
        document.setUrl(filename);

        repository.save(document);
    }

    private void checkFilesType(MultipartFile[] files){
            for(int i = 0; i < files.length; i++){
                String fileName = files[i].getOriginalFilename();

                if(!Objects.nonNull(fileName) || !fileName.contains(GlobalConstant.File.FILE_SPLIT_CHARACTER))
                    throw new InvalidMediaTypeException(GlobalConstant.File.INVALID_FILE_TYPE);

                String fileType = fileName
                        .substring(fileName.lastIndexOf(GlobalConstant.File.FILE_SPLIT_CHARACTER) + 1);

                if(!GlobalConstant.File.FILE_TYPES.contains(fileType)){
                    throw new InvalidMediaTypeException(GlobalConstant.File.INVALID_FILE_TYPE);
                }
            }
    }
}

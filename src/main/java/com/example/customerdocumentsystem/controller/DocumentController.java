package com.example.customerdocumentsystem.controller;


import com.example.customerdocumentsystem.dto.request.SaveDocumentRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetDocumentResponseDTO;
import com.example.customerdocumentsystem.service.DocumentService;
import com.example.customerdocumentsystem.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final Validator<String> customerIdNumberValidator;


    @PostMapping("/{idNumber}/upload")
    public ResponseEntity<?> uploadFile(@PathVariable String idNumber,
                                        @RequestParam("file") MultipartFile file) {

        customerIdNumberValidator.validate(idNumber);

        try {

            String fileContentType = file.getContentType();
            String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            String fileName = file.getOriginalFilename();


            documentService.saveDocument(idNumber, new SaveDocumentRequestDTO(fileName, sourceFileContent, fileContentType));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<?> deleteFile(@PathVariable String documentNumber,
                                        @RequestParam(name = "hardDelete") boolean hardDelete) {

        documentService.deleteDocument(documentNumber, hardDelete);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{documentNumber}")
    public ResponseEntity<?> updateFile(@PathVariable String documentNumber,
                                        @RequestParam("file") MultipartFile file) {

        try {

            String fileContentType = file.getContentType();
            String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            String fileName = file.getOriginalFilename();


            documentService.updateDocument(documentNumber,
                    new SaveDocumentRequestDTO(fileName, sourceFileContent, fileContentType));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/all")
    public ResponseEntity<Collection<GetDocumentResponseDTO>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }


}

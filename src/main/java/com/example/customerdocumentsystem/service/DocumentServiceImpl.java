package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.SaveDocumentRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetDocumentResponseDTO;
import com.example.customerdocumentsystem.exception.CustomerServiceOperationException;
import com.example.customerdocumentsystem.exception.DocumentServiceOperationException;
import com.example.customerdocumentsystem.generator.DocumentNumberGenerator;
import com.example.customerdocumentsystem.model.Document;
import com.example.customerdocumentsystem.repository.CustomerRepository;
import com.example.customerdocumentsystem.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    private final CustomerRepository customerRepository;
    private final DocumentRepository documentRepository;


    @Override
    @Transactional
    public void saveDocument(String idNumber, SaveDocumentRequestDTO saveDocumentRequestDTO) {

        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CustomerServiceOperationException.CustomerExistException("There is customer in database by customer id number! " + idNumber);
        }

        Document document = new Document();

        document.setDocumentNumber(DocumentNumberGenerator.documentNumberGenerator(8));
        document.setName(saveDocumentRequestDTO.getName());
        document.setContent(saveDocumentRequestDTO.getContent());
        document.setType(saveDocumentRequestDTO.getType());
        document.setCustomer(customerRepository.findByIdNumber(idNumber));
        document.setUploadAt(new Date());
        document.setUploadBy("Yunus Emre Şenoğlu");


        documentRepository.save(document);

        log.info("Document is saved!");


    }

    @Override
    @Transactional
    public void deleteDocument(String documentNumber, boolean hardDelete) {

        if (Objects.isNull(documentRepository.findByDocumentNumberByJPQL(documentNumber))) {
            throw new DocumentServiceOperationException.FolderNotFoundException("There is no found! " + documentNumber);
        }

        if (hardDelete == true) {
            Document document = documentRepository.findByDocumentNumberByJPQL(documentNumber);

            documentRepository.delete(document);
            log.info("Document is hard delete! " + documentNumber);
        }

    }

    @Override
    @Transactional
    public void updateDocument(String documentNumber, SaveDocumentRequestDTO saveDocumentRequestDTO) {

        if (Objects.isNull(documentRepository.findByDocumentNumberByJPQL(documentNumber))) {
            throw new DocumentServiceOperationException.FolderNotFoundException("There is no found! " + documentNumber);
        }

        Document document = documentRepository.findByDocumentNumberByJPQL(documentNumber);

        document.setName(saveDocumentRequestDTO.getName());
        document.setContent(saveDocumentRequestDTO.getContent());
        document.setType(saveDocumentRequestDTO.getType());
        document.setUploadAt(new Date());

        documentRepository.save(document);

        log.info(documentNumber + "Document is updated!");
    }

    @Override
    public Collection<GetDocumentResponseDTO> getAllDocuments() {
        return documentRepository
                .findAll()
                .stream()
                .map(document -> new GetDocumentResponseDTO(document.getDocumentNumber(), document.getName(), document.getContent(), document.getType(), document.getCustomerFullName(), document.getUploadAt(), document.getUploadBy()))
                .toList();
    }


}

package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.SaveDocumentRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetDocumentResponseDTO;

import java.util.Collection;

public interface DocumentService {

    void saveDocument(String idNumber, SaveDocumentRequestDTO saveDocumentRequestDTO);

    void deleteDocument(String documentNumber, boolean hardDelete);

    void updateDocument(String documentNumber, SaveDocumentRequestDTO saveDocumentRequestDTO);

    Collection<GetDocumentResponseDTO> getAllDocuments();
}

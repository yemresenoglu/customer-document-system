package com.example.customerdocumentsystem.repository;

import com.example.customerdocumentsystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT * FROM Document WHERE Document.document_number=?1", nativeQuery = true)
    Document findByDocumentNumberByJPQL(String input);
}

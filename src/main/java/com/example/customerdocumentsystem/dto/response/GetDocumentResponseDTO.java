package com.example.customerdocumentsystem.dto.response;

import java.util.Date;

public record GetDocumentResponseDTO(String documentNumber,
                                     String name,
                                     String content,
                                     String type,
                                     String customerFullName,
                                     Date uploadAt,
                                     String uploadBy) {
}

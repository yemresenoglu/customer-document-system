package com.example.customerdocumentsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveDocumentRequestDTO {

    private String name;
    private String content;
    private String type;


}

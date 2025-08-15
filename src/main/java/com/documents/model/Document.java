package com.documents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Document {
    private Long id;
    private String documentType;
    private String requestedBy;
    private String contact;
    private LocalDateTime requestedDate;
    private LocalDateTime releaseDate;



}

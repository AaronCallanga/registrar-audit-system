package com.documents.model;

import java.time.LocalDateTime;

public class Document {
    private Long id;
    private String documentType;
    private String requestedBy;
    private LocalDateTime requestedDate;
    private LocalDateTime releaseDate;
}

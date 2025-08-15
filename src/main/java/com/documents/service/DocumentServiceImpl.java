package com.documents.service;

import com.documents.dao.DocumentDAO;
import com.documents.dao.DocumentImplDAO;
import com.documents.model.Document;
import com.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentDAO documentDAO;

    public DocumentServiceImpl(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public List<Document> getOngoingRequests() {
        return documentDAO.findOngoingRequests();
    }

    @Override
    public List<Document> getCompletedRequests() {
        return documentDAO.findCompletedRequests();
    }

    @Override
    public void releaseDocument(Long id) {
        Document document = documentDAO.findById(id)
                                      .orElseThrow(() -> new ResourceNotFoundException("Document with id " + id + " not found"));
        document.setReleaseDate(LocalDateTime.now());
        documentDAO.updateById(id, document);
    }

    @Override
    public Document getRequestById(Long id) {
        return documentDAO.findById(id)
                          .orElseThrow(() -> new ResourceNotFoundException("Document with id " + id + " not found"));
    }

    @Override
    public Document createRequest(String documentType, String requestedBy, String contact) {
        Document newDocument = Document.builder().
                                       documentType(documentType)
                                       .requestedBy(requestedBy)
                                       .contact(contact)
                                       .requestedDate(LocalDateTime.now())
                                       .releaseDate(null)
                                       .build();
        return documentDAO.save(newDocument)
                          .orElseThrow(() -> new IllegalStateException("Failed to save the document request."));
    }

    @Override
    public Document updateRequest(Long documentId, String documentType, String requestedBy, String contact) {
        Document document = documentDAO.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document with id " + documentId + " not found"));
        document.setDocumentType(documentType);
        document.setRequestedBy(requestedBy);
        document.setContact(contact);

        return documentDAO.updateById(documentId, document)
                .orElseThrow(() -> new IllegalStateException("Failed to update the document request."));
    }


    @Override
    public void deleteRequestById(Long id) {
        documentDAO.deleteById(id);
    }

    @Override
    public List<Document> getAllRequest() {
        return documentDAO.findAll();
    }
}

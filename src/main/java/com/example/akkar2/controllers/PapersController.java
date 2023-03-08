package com.example.akkar2.controllers;

import com.example.akkar2.entities.Papers;
import com.example.akkar2.entities.PapersType;
import com.example.akkar2.repository.PapersRepository;
import com.example.akkar2.services.IPapersService;
import com.example.akkar2.services.PapersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Papers")
public class PapersController {
    @Autowired
    private PapersService documentService;

    @GetMapping("/documents")
    public List<Papers> getAllDocuments() {
        return documentService.getAllPapers();
    }

    @GetMapping("/documents/{documentId}")
    public ResponseEntity<ByteArrayResource> downloadDocument(@PathVariable Long documentId) {
        Papers document = documentService.getPapersById(documentId);

        ByteArrayResource resource = new ByteArrayResource(document.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getPaperType() + "-" + document.getFilename() + "\"")
                .contentType(MediaType.ALL.parseMediaType((document.getContentType())))
                .body(resource);
    }

    @GetMapping("/real-estate/{realEstateId}/documents")
    public List<Papers> getDocumentsByRealEstateId(@PathVariable Long realEstateId) {
        return documentService.getDocumentsByRealEstateId(realEstateId);
    }

    @PostMapping("/real-estate/{realEstateId}/documents")
    public ResponseEntity<Papers> uploadDocument(
            @PathVariable Long realEstateId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("paperType") PapersType paperType) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

            Papers document = documentService.uploadDocument(realEstateId, filename, contentType, data, paperType);

        return ResponseEntity.created(URI.create("/documents/" + document.getId()))
                .body(document);
    }

    @DeleteMapping("/documents/{documentId}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

}

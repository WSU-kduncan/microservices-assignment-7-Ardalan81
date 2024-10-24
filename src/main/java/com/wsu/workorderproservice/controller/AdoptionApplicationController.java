package com.wsu.workorderproservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsu.workorderproservice.dto.AdoptionApplicationDTO;
import com.wsu.workorderproservice.service.AdoptionApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/adoption-applications")
public class AdoptionApplicationController {

    private final AdoptionApplicationService service;

    public AdoptionApplicationController(AdoptionApplicationService service) {
        this.service = service;
    }

    
    @GetMapping
    public ResponseEntity<List<AdoptionApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    
    @PostMapping
    public ResponseEntity<AdoptionApplicationDTO> createApplication(@RequestBody @Valid AdoptionApplicationDTO applicationDTO) {
        try {
            
            AdoptionApplicationDTO createdApplication = service.create(applicationDTO);
            
            
            return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionApplicationDTO> updateApplication(@PathVariable Long id, @RequestBody @Valid AdoptionApplicationDTO applicationDTO) {
        
        return ResponseEntity.ok(service.update(id, applicationDTO));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

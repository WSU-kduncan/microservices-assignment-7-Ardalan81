package com.wsu.workorderproservice.service;

import com.wsu.workorderproservice.dto.AdoptionApplicationDTO;
import com.wsu.workorderproservice.model.AdoptionApplication;
import com.wsu.workorderproservice.repository.AdoptionApplicationRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;

    /**
     * Retrieves all adoption applications or active applications.
     * @param isActive - if true, retrieve only active applications.
     * @return - list of AdoptionApplicationDTOs.
     */
    public List<AdoptionApplicationDTO> get(boolean isActive) {
        try {
            // if (isActive) {
            //     return mapToDTO(adoptionApplicationRepository.findByStatus("Pending"));
            // }
            return mapToDTO(adoptionApplicationRepository.findAll());
        } catch (Exception e) {
            log.error("Failed to retrieve adoption applications. Exception:", e);
            // throw new DatabaseErrorException("Failed to retrieve adoption applications.", e);
            return Collections.emptyList();
        }
    }

    public List<AdoptionApplicationDTO> getAllApplications() {
        try {
            return mapToDTO(adoptionApplicationRepository.findAll());
        } catch (Exception e) {
            log.error("Failed to retrieve adoption applications. Exception:", e);
            return Collections.emptyList();  // Return an empty list on failure
        }
    }

    /**
     * Maps the list of AdoptionApplication entities to AdoptionApplicationDTOs.
     * @param applications - list of AdoptionApplication entities.
     * @return - list of AdoptionApplicationDTOs.
     */
    public List<AdoptionApplicationDTO> mapToDTO(List<AdoptionApplication> applications) {
        if (CollectionUtils.isEmpty(applications)) {
            return Collections.emptyList();
        }
        // return applications.stream().map(application -> AdoptionApplicationDTO.builder()
        //         .adopterId(application.getAdopter().getId())
        //         .petId(application.getPet().getId())
        //         .staffId(application.getStaff().getId())
        //         .status(application.getStatus())
        //         .build()).collect(Collectors.toList());

        return applications.stream().map(application -> AdoptionApplicationDTO.builder()
                //.adopterId(application.getAdopter().getId())
                .status(application.getStatus())
                .build()).collect(Collectors.toList());
    }

    /**
     * Create a new adoption application.
     * @param adoptionApplicationDTO - the DTO containing the data for the new application.
     * @return - the created AdoptionApplicationDTO.
     */
    // public AdoptionApplicationDTO create(AdoptionApplicationDTO adoptionApplicationDTO) {
    //     try {
    //         AdoptionApplication application = new AdoptionApplication();
    //         // Map DTO to entity and save
    //         application.setAdopter(adoptionApplicationDTO.getAdopterId()); // Assume setAdopter takes an ID for now
    //         application.setPet(adoptionApplicationDTO.getPetId());
    //         application.setStaff(adoptionApplicationDTO.getStaffId());
    //         application.setStatus(adoptionApplicationDTO.getStatus());

    //         adoptionApplicationRepository.save(application);

    //         return mapToDTO(Collections.singletonList(application)).get(0);
    //     } catch (Exception e) {
    //         log.error("Failed to create adoption application. Exception:", e);
    //         // throw new DatabaseErrorException("Failed to create adoption application.", e);
    //     }
    // }

    public AdoptionApplicationDTO create(AdoptionApplicationDTO adoptionApplicationDTO) {
        try {
            // Create a new AdoptionApplication entity
            AdoptionApplication application = new AdoptionApplication();
    
            // Fetch related entities (Adopter, Pet, Staff) based on their IDs from the DTO
            // Adopter adopter = adopterRepository.findById(adoptionApplicationDTO.getAdopterId())
                    // .orElseThrow(() -> new IllegalArgumentException("Adopter not found"));
            // Pet pet = petRepository.findById(adoptionApplicationDTO.getPetId())
                    // .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
            // Staff staff = staffRepository.findById(adoptionApplicationDTO.getStaffId())
                    // .orElseThrow(() -> new IllegalArgumentException("Staff not found"));
    
            // Set the fields for the AdoptionApplication entity
            //application.setAdopter(null);
            //application.setPet(null);
            //application.setStaff(null);
            //application.setStatus(adoptionApplicationDTO.getStatus());
    
            // Save the AdoptionApplication entity to the database
            adoptionApplicationRepository.save(application);
    
            // Map the saved entity back to DTO and return it
            return mapToDTO(Collections.singletonList(application)).get(0);
        } catch (Exception e) {
            log.error("Failed to create adoption application. Exception:", e);
            throw new RuntimeException("Error creating adoption application", e);
        }
    }
    

    /**
     * Update an existing adoption application.
     * @param id - ID of the application to update.
     * @param adoptionApplicationDTO - the new data for the application.
     * @return - the updated AdoptionApplicationDTO.
     */
    public AdoptionApplicationDTO update(Long id, AdoptionApplicationDTO adoptionApplicationDTO) {
        try {
            AdoptionApplication application = adoptionApplicationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Application not found"));

            
            application.setStatus(adoptionApplicationDTO.getStatus());
            adoptionApplicationRepository.save(application);

            return mapToDTO(Collections.singletonList(application)).get(0);
        } catch (Exception e) {
            log.error("Failed to update adoption application. Exception:", e);
            // throw new DatabaseErrorException("Failed to update adoption application.", e);
            return null;
        }
    }

    /**
     * Delete an adoption application.
     * @param id - ID of the application to delete.
     */
    public void delete(Long id) {
        try {
            adoptionApplicationRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete adoption application. Exception:", e);
            // throw new DatabaseErrorException("Failed to delete adoption application.", e);
        }
    }
}

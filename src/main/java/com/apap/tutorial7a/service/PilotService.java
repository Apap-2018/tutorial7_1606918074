package com.apap.tutorial7a.service;

import java.util.Optional;

import com.apap.tutorial7a.model.PilotModel;

/**
 * PilotService
 */
public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);

    PilotModel addPilot(PilotModel pilot);

    void deletePilotByLicenseNumber(String licenseNumber);

    Optional<PilotModel> getPilotDetailById(long id);
    
    void updatePilot(PilotModel pilot, String licenseNumber);
}
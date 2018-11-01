package com.apap.tutorial7a.repository;

import java.util.Optional;

import com.apap.tutorial7a.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PilotDb
 */
@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long> {
    PilotModel findByLicenseNumber(String licenseNumber);

    void deleteByLicenseNumber(String licenseNumber);
}

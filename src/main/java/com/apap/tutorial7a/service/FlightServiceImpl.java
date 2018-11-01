package com.apap.tutorial7a.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7a.model.FlightModel;
import com.apap.tutorial7a.repository.FlightDb;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }
    
    @Override
    public List<FlightModel> getAllFlight() {
    	return flightDb.findAll();
    }
    
    @Override
    public void updateFlight(long flightId, FlightModel flight) {
    	flightDb.save(flight);
    }
    
    @Override
    public void deleteFlight(FlightModel flight) {
    	flightDb.delete(flight);
    }
    
    @Override
    public Optional<FlightModel> getFlightDetailByFlightId(long flightId) {
    	return flightDb.findById(flightId);
    }
}
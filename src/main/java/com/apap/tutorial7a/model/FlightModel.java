package com.apap.tutorial7a.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * FlightModel
 */
@Entity
@Table(name = "flight")
public class FlightModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull
    @Size(max = 100)
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull
    @Column(name = "time", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pilot_licenseNumber", referencedColumnName = "license_number")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PilotModel pilot;

    public void setId(long id) {
        this.id = id;
    }
 
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTime(Date time2) {
        this.time = time2;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }

    public long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getTime() {
        return time;
    }

    public PilotModel getPilot() {
        return pilot;
    }
}

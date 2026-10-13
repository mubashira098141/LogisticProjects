package com.logistics.logistics.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;

    private String origin;

    private String destination;

    private String warehouse;

    // Existing field - keep it for now
    private String driver;

    // New field for driver assignment
    @Column(name = "driver_id")
    private Long driverId;

    private String status;

    private LocalDate dispatchDate;

    private LocalDate deliveryDate;

    private Double weight;

    private Integer packages;
}
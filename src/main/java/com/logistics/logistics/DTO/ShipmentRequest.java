package com.logistics.logistics.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShipmentRequest {

    private String customer;

    private String origin;

    private String destination;

    private String warehouse;

    private String driver;
    private Long driverId;

    private String status;

    private LocalDate dispatchDate;

    private LocalDate deliveryDate;

    private Double weight;

    private Integer packages;

}
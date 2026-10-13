package com.logistics.logistics.DTO;

import lombok.Data;

@Data
public class AssignDriverResponse {

    private Long shipmentId;
    private Long driverId;
    private String driverName;
    private String status;
    private String message;

    public AssignDriverResponse(
            Long shipmentId,
            Long driverId,
            String driverName,
            String status,
            String message) {

        this.shipmentId = shipmentId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.status = status;
        this.message = message;
    }
}

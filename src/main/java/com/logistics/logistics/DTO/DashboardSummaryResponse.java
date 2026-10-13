package com.logistics.logistics.DTO;

import lombok.Data;

@Data
public class DashboardSummaryResponse {

    private long totalShipments;

    private long inTransit;

    private long delivered;

    private long pending;

    private long cancelled;

}

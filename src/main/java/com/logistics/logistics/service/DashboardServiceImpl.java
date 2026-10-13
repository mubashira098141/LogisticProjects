package com.logistics.logistics.service;

import com.logistics.logistics.DTO.DashboardSummaryResponse;
import com.logistics.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public DashboardSummaryResponse getSummary() {

        DashboardSummaryResponse response = new DashboardSummaryResponse();

        response.setTotalShipments(shipmentRepository.count());

        response.setInTransit(
                shipmentRepository.countByStatus("In Transit"));

        response.setDelivered(
                shipmentRepository.countByStatus("Delivered"));

        response.setPending(
                shipmentRepository.countByStatus("Pending"));

        response.setCancelled(
                shipmentRepository.countByStatus("Cancelled"));

        return response;
    }
}
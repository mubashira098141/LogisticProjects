package com.logistics.logistics.service;

import com.logistics.logistics.entity.Shipment;
import com.logistics.logistics.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ShipmentRepository shipmentRepository;

    public ReportService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Map<String, Object> getReport() {

        List<Shipment> shipments = shipmentRepository.findAll();

        long total = shipments.size();

        long pending = shipments.stream()
                .filter(s -> "Pending".equalsIgnoreCase(s.getStatus()))
                .count();

        long inTransit = shipments.stream()
                .filter(s -> "In Transit".equalsIgnoreCase(s.getStatus()))
                .count();

        long delivered = shipments.stream()
                .filter(s -> "Delivered".equalsIgnoreCase(s.getStatus()))
                .count();

        long cancelled = shipments.stream()
                .filter(s -> "Cancelled".equalsIgnoreCase(s.getStatus()))
                .count();

        Map<String, Object> report = new HashMap<>();

        report.put("totalShipments", total);
        report.put("pending", pending);
        report.put("inTransit", inTransit);
        report.put("delivered", delivered);
        report.put("cancelled", cancelled);
        report.put("shipments", shipments);

        return report;
    }
}
package com.logistics.logistics.controller;

import com.logistics.logistics.DTO.AssignDriverResponse;
import com.logistics.logistics.DTO.DashboardSummaryResponse;
import com.logistics.logistics.DTO.ShipmentRequest;
import com.logistics.logistics.DTO.ShipmentResponse;
import com.logistics.logistics.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping
    public Page<ShipmentResponse> getAllShipments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return shipmentService.getAllShipments(
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/{id}")
    public ShipmentResponse getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(
            @RequestBody ShipmentRequest request) {

        return ResponseEntity.ok(
                shipmentService.createShipment(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(
            @PathVariable Long id,
            @RequestBody ShipmentRequest request) {

        return ResponseEntity.ok(
                shipmentService.updateShipment(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(
            @PathVariable Long id) {

        shipmentService.deleteShipment(id);

        return ResponseEntity.ok(
                "Shipment deleted successfully"
        );
    }

    // NEW API
    @PostMapping("/{shipmentId}/assign-driver/{driverId}")
    public ResponseEntity<AssignDriverResponse> assignDriver(
            @PathVariable Long shipmentId,
            @PathVariable Long driverId) {

        AssignDriverResponse response =
                shipmentService.assignDriver(
                        shipmentId,
                        driverId
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    public DashboardSummaryResponse getDashboardData() {
        return shipmentService.getDashboardData();
    }
}
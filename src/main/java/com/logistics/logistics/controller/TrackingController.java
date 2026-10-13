package com.logistics.logistics.controller;
import com.logistics.logistics.entity.Tracking;
import com.logistics.logistics.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "http://localhost:5173")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    // GET ALL TRACKING
    @GetMapping
    public ResponseEntity<List<Tracking>> getAllTracking() {

        return ResponseEntity.ok(
                trackingService.getAllTracking()
        );
    }

    // GET TRACKING BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Tracking> getTrackingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                trackingService.getTrackingById(id)
        );
    }

    // GET TRACKING BY SHIPMENT ID
    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<Tracking>> getTrackingByShipmentId(
            @PathVariable Long shipmentId) {

        return ResponseEntity.ok(
                trackingService.getTrackingByShipmentId(shipmentId)
        );
    }

    // CREATE TRACKING
    @PostMapping
    public ResponseEntity<Tracking> createTracking(
            @RequestBody Tracking tracking) {

        return ResponseEntity.ok(
                trackingService.createTracking(tracking)
        );
    }

    // UPDATE TRACKING
    @PutMapping("/{id}")
    public ResponseEntity<Tracking> updateTracking(
            @PathVariable Long id,
            @RequestBody Tracking tracking) {

        return ResponseEntity.ok(
                trackingService.updateTracking(id, tracking)
        );
    }

    // DELETE TRACKING
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracking(
            @PathVariable Long id) {

        trackingService.deleteTracking(id);

        return ResponseEntity.noContent().build();
    }
}

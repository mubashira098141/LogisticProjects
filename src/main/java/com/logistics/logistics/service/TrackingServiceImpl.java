package com.logistics.logistics.service;
import com.logistics.logistics.entity.Tracking;
import com.logistics.logistics.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    @Override
    public List<Tracking> getAllTracking() {

        return trackingRepository.findAll();
    }

    @Override
    public Tracking getTrackingById(Long id) {

        return trackingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tracking record not found"));
    }

    @Override
    public List<Tracking> getTrackingByShipmentId(Long shipmentId) {

        return trackingRepository.findByShipmentId(shipmentId);
    }

    @Override
    public Tracking createTracking(Tracking tracking) {

        return trackingRepository.save(tracking);
    }

    @Override
    public Tracking updateTracking(Long id, Tracking tracking) {

        Tracking existing = getTrackingById(id);

        existing.setShipmentId(tracking.getShipmentId());
        existing.setLocation(tracking.getLocation());
        existing.setStatus(tracking.getStatus());
        existing.setDescription(tracking.getDescription());
        existing.setUpdatedAt(tracking.getUpdatedAt());

        return trackingRepository.save(existing);
    }

    @Override
    public void deleteTracking(Long id) {

        trackingRepository.deleteById(id);
    }
}
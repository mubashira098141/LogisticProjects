package com.logistics.logistics.service;
import com.logistics.logistics.entity.Tracking;

import java.util.List;

public interface TrackingService {

    List<Tracking> getAllTracking();

    Tracking getTrackingById(Long id);

    List<Tracking> getTrackingByShipmentId(Long shipmentId);

    Tracking createTracking(Tracking tracking);

    Tracking updateTracking(Long id, Tracking tracking);

    void deleteTracking(Long id);
}

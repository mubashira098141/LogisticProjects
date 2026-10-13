package com.logistics.logistics.repository;
import com.logistics.logistics.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    List<Tracking> findByShipmentId(Long shipmentId);
}

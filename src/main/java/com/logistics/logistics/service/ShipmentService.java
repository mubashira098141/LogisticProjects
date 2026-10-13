package com.logistics.logistics.service;
import com.logistics.logistics.DTO.AssignDriverResponse;
import com.logistics.logistics.DTO.DashboardSummaryResponse;
import com.logistics.logistics.DTO.ShipmentRequest;
import com.logistics.logistics.DTO.ShipmentResponse;
import com.logistics.logistics.entity.Driver;
import com.logistics.logistics.entity.Shipment;
import com.logistics.logistics.exception.DriverNotAvailableException;
import com.logistics.logistics.exception.DriverNotFoundException;
import com.logistics.logistics.exception.ShipmentAlreadyAssignedException;
import com.logistics.logistics.exception.ShipmentNotFoundException;
import com.logistics.logistics.repository.DriverRepository;
import com.logistics.logistics.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final DriverRepository driverRepository;


    // =========================================================
    // GET ALL SHIPMENTS
    // =========================================================

    public Page<ShipmentResponse> getAllShipments(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if ("desc".equalsIgnoreCase(direction)) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return shipmentRepository.findAll(pageable)
                .map(this::mapToResponse);
    }


    // =========================================================
    // GET SHIPMENT BY ID
    // =========================================================

    public ShipmentResponse getShipmentById(Long id) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new ShipmentNotFoundException(
                                "Shipment not found with id: " + id
                        )
                );

        return mapToResponse(shipment);
    }


    // =========================================================
    // CREATE SHIPMENT
    // =========================================================

    public ShipmentResponse createShipment(ShipmentRequest request) {

        Shipment shipment = new Shipment();

        shipment.setCustomer(request.getCustomer());
        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setWarehouse(request.getWarehouse());
        shipment.setDriver(request.getDriver());
        shipment.setDriverId(request.getDriverId());
        shipment.setStatus(request.getStatus());
        shipment.setDispatchDate(request.getDispatchDate());
        shipment.setDeliveryDate(request.getDeliveryDate());
        shipment.setWeight(request.getWeight());
        shipment.setPackages(request.getPackages());

        Shipment savedShipment = shipmentRepository.save(shipment);

        return mapToResponse(savedShipment);
    }


    // =========================================================
    // UPDATE SHIPMENT
    // =========================================================

    public ShipmentResponse updateShipment(
            Long id,
            ShipmentRequest request) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new ShipmentNotFoundException(
                                "Shipment not found with id: " + id
                        )
                );

        shipment.setCustomer(request.getCustomer());
        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setWarehouse(request.getWarehouse());
        shipment.setDriver(request.getDriver());
        shipment.setDriverId(request.getDriverId());
        shipment.setStatus(request.getStatus());
        shipment.setDispatchDate(request.getDispatchDate());
        shipment.setDeliveryDate(request.getDeliveryDate());
        shipment.setWeight(request.getWeight());
        shipment.setPackages(request.getPackages());

        Shipment updatedShipment =
                shipmentRepository.save(shipment);

        return mapToResponse(updatedShipment);
    }


    // =========================================================
    // DELETE SHIPMENT
    // =========================================================

    public void deleteShipment(Long id) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new ShipmentNotFoundException(
                                "Shipment not found with id: " + id
                        )
                );

        shipmentRepository.delete(shipment);
    }


    // =========================================================
    // ASSIGN DRIVER
    // =========================================================

    @Transactional
    public AssignDriverResponse assignDriver(
            Long shipmentId,
            Long driverId) {

        // 1. Find shipment
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() ->
                        new ShipmentNotFoundException(
                                "Shipment not found with id: "
                                        + shipmentId
                        )
                );


        // 2. Check shipment already assigned
        if (shipment.getDriverId() != null) {

            throw new ShipmentAlreadyAssignedException(
                    "Shipment is already assigned to driver: "
                            + shipment.getDriverId()
            );
        }


        // 3. Find driver
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() ->
                        new DriverNotFoundException(
                                "Driver not found with id: "
                                        + driverId
                        )
                );


        // 4. Check driver availability
        if (!"AVAILABLE".equalsIgnoreCase(driver.getStatus())) {

            throw new DriverNotAvailableException(
                    "Driver is not available. Current status: "
                            + driver.getStatus()
            );
        }


        // 5. Assign driver to shipment
        shipment.setDriverId(driver.getId());

        // Keep old driver field updated
        shipment.setDriver(driver.getName());

        // Update shipment status
        shipment.setStatus("ASSIGNED");


        // 6. Save shipment
        shipmentRepository.save(shipment);


        // 7. Update driver status
        driver.setStatus("ASSIGNED");


        // 8. Save driver
        driverRepository.save(driver);


        // 9. Return response
        return new AssignDriverResponse(
                shipment.getId(),
                driver.getId(),
                driver.getName(),
                shipment.getStatus(),
                "Driver assigned successfully"
        );
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    public DashboardSummaryResponse getDashboardData() {

        long totalShipments =
                shipmentRepository.count();

        long pendingShipments =
                shipmentRepository.countByStatus("Pending");

        long inTransitShipments =
                shipmentRepository.countByStatus("In Transit");

        long deliveredShipments =
                shipmentRepository.countByStatus("Delivered");

        long cancelledShipments =
                shipmentRepository.countByStatus("Cancelled");


        DashboardSummaryResponse response =
                new DashboardSummaryResponse();

        response.setTotalShipments(totalShipments);
        response.setPending(pendingShipments);
        response.setInTransit(inTransitShipments);
        response.setDelivered(deliveredShipments);
        response.setCancelled(cancelledShipments);

        return response;
    }


    // =========================================================
    // ENTITY -> RESPONSE DTO
    // =========================================================

    private ShipmentResponse mapToResponse(
            Shipment shipment) {

        ShipmentResponse response =
                new ShipmentResponse();

        response.setId(shipment.getId());
        response.setCustomer(shipment.getCustomer());
        response.setOrigin(shipment.getOrigin());
        response.setDestination(shipment.getDestination());
        response.setWarehouse(shipment.getWarehouse());
        response.setDriver(shipment.getDriver());
        response.setDriverId(shipment.getDriverId());
        response.setStatus(shipment.getStatus());
        response.setDispatchDate(shipment.getDispatchDate());
        response.setDeliveryDate(shipment.getDeliveryDate());
        response.setWeight(shipment.getWeight());
        response.setPackages(shipment.getPackages());

        return response;
    }
}


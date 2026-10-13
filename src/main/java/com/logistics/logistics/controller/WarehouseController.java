package com.logistics.logistics.controller;
import com.logistics.logistics.entity.Warehouse;
import com.logistics.logistics.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin(origins = "http://localhost:5173")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;


    // GET ALL WAREHOUSES
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {

        return ResponseEntity.ok(
                warehouseService.getAllWarehouses()
        );
    }


    // GET WAREHOUSE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                warehouseService.getWarehouseById(id)
        );
    }


    // CREATE WAREHOUSE
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(
            @RequestBody Warehouse warehouse) {

        return ResponseEntity.ok(
                warehouseService.createWarehouse(warehouse)
        );
    }


    // UPDATE WAREHOUSE
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(
            @PathVariable Long id,
            @RequestBody Warehouse warehouse) {

        return ResponseEntity.ok(
                warehouseService.updateWarehouse(
                        id,
                        warehouse
                )
        );
    }


    // DELETE WAREHOUSE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(
            @PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.noContent().build();
    }
}

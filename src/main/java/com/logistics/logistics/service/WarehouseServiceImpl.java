package com.logistics.logistics.service;


import com.logistics.logistics.entity.Warehouse;
import com.logistics.logistics.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;


    @Override
    public List<Warehouse> getAllWarehouses() {

        return warehouseRepository.findAll();
    }


    @Override
    public Warehouse getWarehouseById(Long id) {

        return warehouseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Warehouse not found"));
    }


    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }


    @Override
    public Warehouse updateWarehouse(
            Long id,
            Warehouse warehouse) {

        Warehouse existing =
                getWarehouseById(id);


        existing.setName(
                warehouse.getName());

        existing.setLocation(
                warehouse.getLocation());

        existing.setManager(
                warehouse.getManager());

        existing.setPhone(
                warehouse.getPhone());

        existing.setCapacity(
                warehouse.getCapacity());

        existing.setStatus(
                warehouse.getStatus());


        return warehouseRepository.save(existing);
    }


    @Override
    public void deleteWarehouse(Long id) {

        warehouseRepository.deleteById(id);
    }
}

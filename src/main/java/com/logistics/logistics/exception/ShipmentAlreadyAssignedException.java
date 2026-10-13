package com.logistics.logistics.exception;

public class ShipmentAlreadyAssignedException extends RuntimeException {

    public ShipmentAlreadyAssignedException(String message) {
        super(message);
    }
}
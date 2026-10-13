package com.logistics.logistics.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    private Long id;

    private String userName;

    private String email;

    private boolean emailNotifications;

    private boolean shipmentAlerts;

    private boolean smsNotifications;

    public Settings() {
    }

    public Settings(Long id, String userName, String email,
                    boolean emailNotifications,
                    boolean shipmentAlerts,
                    boolean smsNotifications) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.emailNotifications = emailNotifications;
        this.shipmentAlerts = shipmentAlerts;
        this.smsNotifications = smsNotifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public boolean isShipmentAlerts() {
        return shipmentAlerts;
    }

    public void setShipmentAlerts(boolean shipmentAlerts) {
        this.shipmentAlerts = shipmentAlerts;
    }

    public boolean isSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(boolean smsNotifications) {
        this.smsNotifications = smsNotifications;
    }
}
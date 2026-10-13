package com.logistics.logistics.service;
import com.logistics.logistics.entity.Settings;
import com.logistics.logistics.repository.SettingsRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings getSettings() {

        return settingsRepository.findById(1L)
                .orElseGet(() -> {

                    Settings settings = new Settings(
                            1L,
                            "Mubashira",
                            "mubashira@example.com",
                            true,
                            true,
                            false
                    );

                    return settingsRepository.save(settings);
                });
    }

    public Settings updateSettings(Settings settings) {

        Settings existing = settingsRepository.findById(1L)
                .orElse(new Settings());

        existing.setId(1L);
        existing.setUserName(settings.getUserName());
        existing.setEmail(settings.getEmail());
        existing.setEmailNotifications(
                settings.isEmailNotifications()
        );
        existing.setShipmentAlerts(
                settings.isShipmentAlerts()
        );
        existing.setSmsNotifications(
                settings.isSmsNotifications()
        );

        return settingsRepository.save(existing);
    }
}

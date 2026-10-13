package com.logistics.logistics.controller;
import com.logistics.logistics.entity.Settings;
import com.logistics.logistics.service.SettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "http://localhost:5173")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public Settings getSettings() {
        return settingsService.getSettings();
    }

    @PutMapping
    public Settings updateSettings(@RequestBody Settings settings) {
        return settingsService.updateSettings(settings);
    }
}
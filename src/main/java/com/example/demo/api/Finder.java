package com.example.demo.api;

import com.example.demo.model.Location;
import com.example.demo.model.Motel;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/finder")
public class Finder {
    private final LocationService locationService;

    @Autowired
    public Finder(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/location")
    public Location getCurrentLocation() {
        return locationService.getCurrentLocation();
    }

    @GetMapping("/findAll/{range}")
    public List<Motel> getAllMotelsInZone(@PathVariable("range") double range) {
        return locationService.findAllMotelsInZone(range);
    }

    @GetMapping("findFirst")
    public Motel getFirstMotel() {
        return locationService.findFirstMotel();
    }
}

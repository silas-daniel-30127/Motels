package com.example.demo.api;

import com.example.demo.model.Motel;
import com.example.demo.service.MotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/motels")
@RestController
public class MotelController {
    private final MotelService motelService;

    @Autowired
    public MotelController(MotelService motelService) {
        this.motelService = motelService;
    }

    @PostMapping
    public void addMotel(@RequestBody Motel motel) {
        motelService.addMotel(motel);
    }

    @PostMapping("/addMore")
    public void addMultipleMotels(@RequestBody List<Motel> motels) {
        motelService.addMotels(motels);
    }

    @GetMapping
    public List<Motel> getAllMotels() {
        return motelService.getAllMotels();
    }

    @GetMapping(path = "{id}")
    public Motel selectMotelById(@PathVariable("id") int id) {
        return motelService.selectMotelById(id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteMotelById(@PathVariable("id") int id) {
        return motelService.deleteMotelById(id);
    }

    @PutMapping(path = "update")
    public Motel updateMotelById(@RequestBody Motel motel) {
        return motelService.updateMotelById(motel);
    }
}

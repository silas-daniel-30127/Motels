package com.example.demo.service;

import com.example.demo.model.Motel;
import com.example.demo.repositories.MotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotelService {

    private final MotelRepo motelRepo;

    @Autowired
    public MotelService(MotelRepo motelRepo) {
        this.motelRepo = motelRepo;
    }

    public void addMotel(Motel motel) {
        motelRepo.save(motel);
    }

    public void addMotels(List<Motel> motels) {
        motelRepo.saveAll(motels);
    }

    public List<Motel> getAllMotels() {
        return (List<Motel>) motelRepo.findAll();
    }

    public Motel selectMotelById(int id) {
        return motelRepo.findById(id).orElse(null);
    }

    public String deleteMotelById(int id) {
        motelRepo.deleteById(id);
        return "Motel " + id + " removed!!";
    }

    public Motel updateMotelById(Motel motel) {
        Motel existingMotel = motelRepo.findById(motel.getId()).orElse(null);
        assert existingMotel != null;
        existingMotel.setName(motel.getName());
        existingMotel.setLatitude(motel.getLatitude());
        existingMotel.setLongitude(motel.getLongitude());
        return motelRepo.save(existingMotel);
    }
}

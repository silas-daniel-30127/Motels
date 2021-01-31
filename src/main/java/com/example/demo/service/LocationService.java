package com.example.demo.service;

import com.example.demo.model.Location;
import com.example.demo.model.Motel;
import com.example.demo.repositories.MotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final MotelRepo motelRepo;

    @Autowired
    public LocationService(MotelRepo motelRepo) {
        this.motelRepo = motelRepo;
    }


    private static String readFromUrl(String url) throws Exception {
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        StringBuilder output = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            output.append(inputLine);
        }
        in.close();
        return output.toString();
    }

    private static String getIp() throws Exception {
        URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatIsMyIp.openStream()));
            return in.readLine();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Location getCurrentLocation() {
        String myIp;
        String url = "";
        try {
            myIp = getIp();
            url = readFromUrl("http://ip-api.com/csv/" + myIp + "?fields=lat,lon");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] split = url.split(",");
        double latitude = Double.parseDouble(split[0]);
        double longitude = Double.parseDouble(split[1]);
        return new Location(latitude, longitude);
    }

    private List<Motel> getAllMotels() {
        return (List<Motel>) motelRepo.findAll();
    }

    public List<Motel> findAllMotelsInZone(double range) {
        List<Motel> motelList = getAllMotels();
        Location currentLocation = getCurrentLocation();
        return motelList.stream()
                .filter(motel -> currentLocation.distance(new Location(motel.getLatitude(), motel.getLongitude())) <= range)
                .collect(Collectors.toList());
    }

    public Motel findFirstMotel() {
        List<Motel> motels = getAllMotels();
        Location currentLocation = getCurrentLocation();
        return motels.stream().min(Comparator.comparing(motel -> currentLocation.distance(new Location(motel.getLatitude(), motel.getLongitude())))).orElse(null);
    }
}

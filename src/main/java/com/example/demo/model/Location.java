package com.example.demo.model;

public class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(Location loc) {
        double lat1 = this.latitude;
        double lat2 = loc.latitude;
        lat1 = lat1 * 0.017453292500000002D;
        lat2 = (lat2 * 0.017453292500000002D);
        double delta_lat = lat2 - lat1;
        double delta_lon = (loc.longitude - this.longitude) * 0.017453292500000002D;
        double temp = Math.pow(Math.sin(delta_lat / 2.0D), 2.0D) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(delta_lon / 2.0D), 2.0D);
        return 12756.4D * Math.atan2(Math.sqrt(temp), Math.sqrt(1.0D - temp));
    }
}
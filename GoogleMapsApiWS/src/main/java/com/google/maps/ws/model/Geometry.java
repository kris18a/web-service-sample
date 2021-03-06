package com.google.maps.ws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private Location location;

    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public String toString() {
        return (null == location) ? "" : location.toString();
    }
    
}
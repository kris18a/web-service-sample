package com.google.maps.ws.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHours {

    private boolean open_now;

    
    public boolean isOpen_now() {
        return open_now;
    }

    
    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }
    
    public String toString() {
        return "Open Now: " + open_now;
    }
}

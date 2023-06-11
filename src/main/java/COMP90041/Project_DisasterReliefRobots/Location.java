package COMP90041.Project_DisasterReliefRobots;

import java.util.ArrayList;

public class Location {

    private double latitude;
    private double longitude;
    private char latitudeDirection;
    private char longitudeDirection;
    private String status;
    private ArrayList<Resident> residents;

    public Location(double latitude, double longitude, char latitudeDirection, char longitudeDirection, String status) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitudeDirection = latitudeDirection;
        this.longitudeDirection = longitudeDirection;
        this.status = status;
        residents = new ArrayList<>();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public char getLatitudeDirection() {
        return latitudeDirection;
    }

    public void setLatitudeDirection(char latitudeDirection) {
        this.latitudeDirection = latitudeDirection;
    }

    public char getLongitudeDirection() {
        return longitudeDirection;
    }

    public void setLongitudeDirection(char longitudeDirection) {
        this.longitudeDirection = longitudeDirection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Resident> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<Resident> residents) {
        this.residents = residents;
    }
}

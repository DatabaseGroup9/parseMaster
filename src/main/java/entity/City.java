package entity;


public class City {
    private String cityID;
    private String name;
    private double lon;
    private double lat;

    

    public City(String cityID, String name, double lon, double lat) {
        this.cityID = cityID;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }
    public City(){
        
    }
    
    public City(String name, double lat, double lon){
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "City{" + "name=" + name + ", lon=" + lon + ", lat=" + lat + '}';
    }
    
}

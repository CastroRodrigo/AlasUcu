
package alasucu;

/**
 * @author Rodrigo Castro
 */
public class Aeropuerto {
    
    private final String name;
    private final String city;
    private final String country;
    private final Comparable iata;
    private final String icao;
    private final String longitude;
    private final String latitude;
    private final String altitude;
    private final String timeZone;
    private final String dst;
    private final String databaseTimeZone;

    public Aeropuerto(String name, String city, String country, Comparable iata, String icao, String longitude, String latitude, String altitude, String timeZone, String dst, String databaseTimeZone) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata = iata;
        this.icao = icao;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.timeZone = timeZone;
        this.dst = dst;
        this.databaseTimeZone = databaseTimeZone;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Comparable getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getDst() {
        return dst;
    }

    public String getDatabaseTimeZone() {
        return databaseTimeZone;
    }
    
    
    
    
}

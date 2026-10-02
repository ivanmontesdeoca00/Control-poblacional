// Archivo: Modelo/Ciudad.java
package Modelo;

public class Ciudad {
    private String nombre;
    private int poblacion;
    private String region;
    private boolean esCapital;

    public Ciudad(String nombre, int poblacion, String region, boolean esCapital) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.region = region;
        this.esCapital = esCapital;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getPoblacion() { return poblacion; }
    public void setPoblacion(int poblacion) { this.poblacion = poblacion; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public boolean isEsCapital() { return esCapital; }
    public void setEsCapital(boolean esCapital) { this.esCapital = esCapital; }
}

package Modelo;

public class Ciudad {
    private String nombre;
    private String pais;
    private String distrito;
    private long poblacion;

    public Ciudad(String nombre, String pais, String distrito, long poblacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.distrito = distrito;
        this.poblacion = poblacion;
    }

    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public String getDistrito() { return distrito; }
    public long getPoblacion() { return poblacion; }
}
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private String codigo;
    private String nombre;
    private String continente;
    private long poblacion;
    private Integer anioIndependencia;
    private List<IdiomaPais> idiomas;

    public Pais() {
        this.idiomas = new ArrayList<>();
    }

    public Pais(String codigo, String nombre, String continente, long poblacion, Integer anioIndependencia, String nombreIdioma) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.continente = continente;
        this.poblacion = poblacion;
        this.anioIndependencia = anioIndependencia;
        this.idiomas = new ArrayList<>();
        if (nombreIdioma != null && !nombreIdioma.trim().isEmpty()) {
            this.idiomas.add(new IdiomaPais(nombreIdioma.trim()));
        }
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContinente() { return continente; }
    public void setContinente(String continente) { this.continente = continente; }

    public long getPoblacion() { return poblacion; }
    public void setPoblacion(long poblacion) { this.poblacion = poblacion; }

    public Integer getAnioIndependencia() { return anioIndependencia; }
    public void setAnioIndependencia(Integer anioIndependencia) { this.anioIndependencia = anioIndependencia; }

    public List<IdiomaPais> getIdiomas() { return idiomas; }
    public void setIdiomas(List<IdiomaPais> idiomas) { this.idiomas = idiomas; }

    public String getIdiomasTexto() {
        if (idiomas == null || idiomas.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idiomas.size(); i++) {
            sb.append(idiomas.get(i).getNombre());
            if (i < idiomas.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public void setIdiomaDesdeTexto(String textoIdioma) {
        this.idiomas = new ArrayList<>();
        if (textoIdioma != null && !textoIdioma.trim().isEmpty()) {
            String[] partes = textoIdioma.split(",");
            for (String part : partes) {
                if (!part.trim().isEmpty()) {
                    this.idiomas.add(new IdiomaPais(part.trim()));
                }
            }
        }
    }
}
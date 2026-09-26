package Controlador;

import Modelo.Pais;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPoblacion {
    private List<Pais> listaPaises;

    public GestorPoblacion() {
        this.listaPaises = new ArrayList<>();
    }

    public void agregarPais(Pais pais) {
        if (pais != null) {
            this.listaPaises.add(pais);
        }
    }

    public List<Pais> obtenerTodos() {
        return this.listaPaises;
    }

    public Pais buscarPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (Pais p : listaPaises) {
            if (p.getCodigo() != null && p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public List<Pais> buscarPorNombre(String nombre) {
        List<Pais> resultado = new ArrayList<>();
        if (nombre == null) return resultado;
        for (Pais p : listaPaises) {
            if (p.getNombre() != null && p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // 1. Validar si un país habla un idioma específico
    public boolean hablaIdioma(String codigoPais, String idiomaBuscado) {
        Pais p = buscarPorCodigo(codigoPais);
        if (p == null || p.getIdiomas() == null || idiomaBuscado == null) {
            return false;
        }
        return p.getIdiomas().stream()
                .anyMatch(i -> i.getNombre() != null && i.getNombre().equalsIgnoreCase(idiomaBuscado));
    }

    // 2. Obtener países independizados en un año determinado
    public List<Pais> buscarPorAnioIndependencia(int anio) {
        return listaPaises.stream()
                .filter(p -> p.getAnioIndependencia() != null && p.getAnioIndependencia() == anio)
                .collect(Collectors.toList());
    }

    // 3. Obtener países cuya población esté en un rango [min, max]
    public List<Pais> buscarPorRangoPoblacion(long minPoblacion, long maxPoblacion) {
        return listaPaises.stream()
                .filter(p -> p.getPoblacion() >= minPoblacion && p.getPoblacion() <= maxPoblacion)
                .collect(Collectors.toList());
    }
}
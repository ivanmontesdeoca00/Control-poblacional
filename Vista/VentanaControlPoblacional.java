package Vista;

import Controlador.GestorPoblacion;
import Modelo.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaControlPoblacional extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtContinente;
    private JTextField txtPoblacion;
    private JTextField txtAnio;
    private JTextField txtIdioma;

    private JButton btnAgregar;
    private JButton btnConsultar;
    private JButton btnModificar;
    
    private JButton btnValidarIdioma;
    private JButton btnBuscarAnio;
    private JButton btnRangoPob;
    
    // Nuevos botones requeridos
    private JButton btnContarPaises;
    private JButton btnEliminarTodos;
    private JButton btnGestionCiudades;

    private JTable tblResultados;
    private DefaultTableModel modeloTabla;

    private GestorPoblacion gestor;

    public VentanaControlPoblacional(GestorPoblacion gestor) {
        this.gestor = gestor;

        setTitle("Sistema de Control Poblacional");
        setSize(860, 420); // Se aumentó el alto para acomodar los nuevos botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        inicializarComponentes();
        activarEventos();
        actualizarTabla(gestor.obtenerTodos());
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("Registro Poblacional Internacional");
        lblTitulo.setBounds(80, 10, 300, 20);
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 45, 80, 20);
        add(lblCodigo);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 45, 140, 20);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 75, 80, 20);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 75, 140, 20);
        add(txtNombre);

        JLabel lblContinente = new JLabel("Continente:");
        lblContinente.setBounds(30, 105, 80, 20);
        add(lblContinente);
        txtContinente = new JTextField();
        txtContinente.setBounds(120, 105, 140, 20);
        add(txtContinente);

        JLabel lblPoblacion = new JLabel("Población:");
        lblPoblacion.setBounds(30, 135, 80, 20);
        add(lblPoblacion);
        txtPoblacion = new JTextField();
        txtPoblacion.setBounds(120, 135, 140, 20);
        add(txtPoblacion);

        JLabel lblAnio = new JLabel("Año Indep.:");
        lblAnio.setBounds(30, 165, 80, 20);
        add(lblAnio);
        txtAnio = new JTextField();
        txtAnio.setBounds(120, 165, 140, 20);
        add(txtAnio);

        JLabel lblIdioma = new JLabel("Idioma:");
        lblIdioma.setBounds(30, 195, 80, 20);
        add(lblIdioma);
        txtIdioma = new JTextField();
        txtIdioma.setBounds(120, 195, 140, 20);
        add(txtIdioma);

        // --- Botones Operativos Principales ---
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(30, 235, 85, 25);
        add(btnAgregar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(120, 235, 95, 25);
        add(btnConsultar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(220, 235, 90, 25);
        add(btnModificar);

        // --- Botones de Consultas Específicas ---
        btnValidarIdioma = new JButton("Validar Idioma");
        btnValidarIdioma.setBounds(30, 275, 125, 25);
        add(btnValidarIdioma);

        btnBuscarAnio = new JButton("Buscar p/Año");
        btnBuscarAnio.setBounds(160, 275, 115, 25);
        add(btnBuscarAnio);

        btnRangoPob = new JButton("Rango Población");
        btnRangoPob.setBounds(280, 275, 140, 25);
        add(btnRangoPob);

        // --- Nuevos Botones de Requerimientos ---
        btnContarPaises = new JButton("Contar Países");
        btnContarPaises.setBounds(30, 315, 125, 25);
        add(btnContarPaises);

        btnEliminarTodos = new JButton("Eliminar Todos");
        btnEliminarTodos.setBounds(160, 315, 125, 25);
        add(btnEliminarTodos);

        btnGestionCiudades = new JButton("Gestión Ciudades");
        btnGestionCiudades.setBounds(290, 315, 140, 25);
        add(btnGestionCiudades);

        // --- Tabla de Resultados ---
        String[] columnas = {"Código", "Nombre", "Continente", "Población", "Año Indep.", "Idioma"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tblResultados = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tblResultados);
        scrollPane.setBounds(430, 45, 390, 295); // Se aumentó un poco el alto de la tabla para ajustarse a los nuevos botones
        add(scrollPane);
    }

    private void activarEventos() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cod = txtCodigo.getText().trim();
                    String nom = txtNombre.getText().trim();
                    String cont = txtContinente.getText().trim();
                    String idioma = txtIdioma.getText().trim();

                    if (cod.isEmpty() || nom.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar al menos Código y Nombre.");
                        return;
                    }

                    if (gestor.buscarPorCodigo(cod) != null) {
                        JOptionPane.showMessageDialog(null, "Ya existe un país registrado con ese código.");
                        return;
                    }

                    long pob = Long.parseLong(txtPoblacion.getText().trim());
                    Integer anio = txtAnio.getText().trim().isEmpty() ? null : Integer.parseInt(txtAnio.getText().trim());

                    Pais nuevoPais = new Pais(cod, nom, cont, pob, anio, idioma);
                    gestor.agregarPais(nuevoPais);
                    actualizarTabla(gestor.obtenerTodos());
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "País registrado correctamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Población y Año deben ser valores numéricos.");
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterio = txtNombre.getText().trim();
                String cod = txtCodigo.getText().trim();

                if (!cod.isEmpty()) {
                    Pais encontrado = gestor.buscarPorCodigo(cod);
                    if (encontrado != null) {
                        modeloTabla.setRowCount(0);
                        modeloTabla.addRow(new Object[]{
                            encontrado.getCodigo(),
                            encontrado.getNombre(),
                            encontrado.getContinente(),
                            encontrado.getPoblacion(),
                            encontrado.getAnioIndependencia(),
                            encontrado.getIdiomasTexto()
                        });
                        txtNombre.setText(encontrado.getNombre());
                        txtContinente.setText(encontrado.getContinente());
                        txtPoblacion.setText(String.valueOf(encontrado.getPoblacion()));
                        txtAnio.setText(encontrado.getAnioIndependencia() != null ? String.valueOf(encontrado.getAnioIndependencia()) : "");
                        txtIdioma.setText(encontrado.getIdiomasTexto());
                        return;
                    }
                }

                if (!criterio.isEmpty()) {
                    List<Pais> coincidencias = gestor.buscarPorNombre(criterio);
                    if (!coincidencias.isEmpty()) {
                        actualizarTabla(coincidencias);
                        return;
                    }
                }

                if (cod.isEmpty() && criterio.isEmpty()) {
                    actualizarTabla(gestor.obtenerTodos());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados para la búsqueda.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cod = txtCodigo.getText().trim();
                Pais p = gestor.buscarPorCodigo(cod);

                if (p != null) {
                    try {
                        p.setNombre(txtNombre.getText().trim());
                        p.setContinente(txtContinente.getText().trim());
                        p.setPoblacion(Long.parseLong(txtPoblacion.getText().trim()));
                        p.setAnioIndependencia(txtAnio.getText().trim().isEmpty() ? null : Integer.parseInt(txtAnio.getText().trim()));
                        p.setIdiomaDesdeTexto(txtIdioma.getText().trim());

                        actualizarTabla(gestor.obtenerTodos());
                        JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La población y año deben ser valores numéricos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese o consulte el código del país a modificar.");
                }
            }
        });

        btnValidarIdioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cod = txtCodigo.getText().trim();
                String idioma = txtIdioma.getText().trim();

                if (cod.isEmpty() || idioma.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el Código del país y el Idioma a consultar.");
                    return;
                }

                boolean habla = gestor.hablaIdioma(cod, idioma);
                Pais p = gestor.buscarPorCodigo(cod);
                String nombrePais = (p != null) ? p.getNombre() : cod;

                if (habla) {
                    JOptionPane.showMessageDialog(null, "SÍ: En " + nombrePais + " se habla el idioma " + idioma + ".");
                } else {
                    JOptionPane.showMessageDialog(null, "NO: En " + nombrePais + " NO figura registrado el idioma " + idioma + ".");
                }
            }
        });

        btnBuscarAnio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String anioTxt = txtAnio.getText().trim();
                if (anioTxt.isEmpty()) {
                    anioTxt = JOptionPane.showInputDialog(null, "Ingrese el año de independencia a consultar:");
                }

                if (anioTxt != null && !anioTxt.trim().isEmpty()) {
                    try {
                        int anio = Integer.parseInt(anioTxt.trim());
                        List<Pais> resultado = gestor.buscarPorAnioIndependencia(anio);
                        actualizarTabla(resultado);

                        if (resultado.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron países independizados en el año " + anio + ".");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El año debe ser un número entero.");
                    }
                }
            }
        });

        btnRangoPob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String minStr = JOptionPane.showInputDialog(null, "Ingrese la población MÍNIMA:");
                if (minStr == null) return;

                String maxStr = JOptionPane.showInputDialog(null, "Ingrese la población MÁXIMA:");
                if (maxStr == null) return;

                try {
                    long min = Long.parseLong(minStr.trim());
                    long max = Long.parseLong(maxStr.trim());

                    if (min > max) {
                        JOptionPane.showMessageDialog(null, "La población mínima no puede ser mayor que la máxima.");
                        return;
                    }

                    List<Pais> resultado = gestor.buscarPorRangoPoblacion(min, max);
                    actualizarTabla(resultado);

                    if (resultado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron países dentro del rango de población especificado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los valores de población deben ser números enteros.");
                }
            }
        });

        // --- Lógica de los Nuevos Botones ---

        // 1. Contar Países
        btnContarPaises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = gestor.contarPaisesIngresados();
                JOptionPane.showMessageDialog(null, "Total de países ingresados en el sistema: " + cantidad, "Conteo de Países", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 2. Eliminar Todos los Países
        btnEliminarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, 
                    "¿Está seguro de que desea eliminar TODOS los países registrados?", 
                    "Confirmar Eliminación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    gestor.eliminarTodosLosPaises();
                    actualizarTabla(gestor.obtenerTodos()); // Refresca la tabla dejándola vacía
                    JOptionPane.showMessageDialog(null, "Todos los registros de países han sido eliminados.");
                }
            }
        });

        // 3. Abrir Ventana de Ciudades
        btnGestionCiudades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instancia la nueva ventana pasándole el gestor para mantener los datos centralizados
                VentanaCiudad ventanaCiudad = new VentanaCiudad(gestor);
                ventanaCiudad.setVisible(true);
            }
        });
    }

    private void actualizarTabla(List<Pais> lista) {
        modeloTabla.setRowCount(0);
        for (Pais p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getCodigo(),
                p.getNombre(),
                p.getContinente(),
                p.getPoblacion(),
                p.getAnioIndependencia() != null ? p.getAnioIndependencia() : "N/A",
                p.getIdiomasTexto()
            });
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtContinente.setText("");
        txtPoblacion.setText("");
        txtAnio.setText("");
        txtIdioma.setText("");
    }
}

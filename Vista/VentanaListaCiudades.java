package Vista;

import Controlador.GestorPoblacion;
import Modelo.Ciudad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaCiudades extends JFrame {

    private JTable tblCiudades;
    private DefaultTableModel modeloTabla;
    private GestorPoblacion gestor;

    public VentanaListaCiudades(GestorPoblacion gestor) {
        this.gestor = gestor;

        setTitle("Listado de Ciudades Registradas");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
        cargarDatosTabla();
    }

    private void inicializarComponentes() {
        // Título superior
        JLabel lblTitulo = new JLabel("Ciudades Ingresadas en el Sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(lblTitulo, BorderLayout.NORTH);

        // Configurar las 4 columnas correspondientes a los atributos de Ciudad
        String[] columnas = {"Nombre", "Población", "Región / Provincia", "¿Es Capital?"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que el usuario edite las celdas directamente
            }
        };

        tblCiudades = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tblCiudades);
        add(scrollPane, BorderLayout.CENTER);

        // Botón inferior para cerrar la ventana
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnCerrar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0); // Limpia la tabla antes de cargar
        List<Ciudad> lista = gestor.obtenerTodasLasCiudades();

        for (Ciudad c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getNombre(),
                c.getPoblacion(),
                c.getRegion(),
                c.isEsCapital() ? "Sí" : "No" // Transforma el booleano en texto legible
            });
        }
    }
}

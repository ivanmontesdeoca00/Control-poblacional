package Vista;

import Controlador.GestorPoblacion;
import Modelo.Ciudad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCiudad extends JFrame {
    
    private JTextField txtNombre;
    private JTextField txtPoblacion;
    private JTextField txtRegion;
    private JCheckBox chkEsCapital;
    private JButton btnGuardar;
    
    // Referencia al controlador principal
    private GestorPoblacion gestor;

    // Modificamos el constructor para recibir el GestorPoblacion
    public VentanaCiudad(GestorPoblacion gestorPrincipal) {
        this.gestor = gestorPrincipal;
        
        setTitle("Gestión de Ciudades");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new GridLayout(5, 2, 10, 10));

        // 1er Atributo
        add(new JLabel(" Nombre de la Ciudad:"));
        txtNombre = new JTextField();
        add(txtNombre);

        // 2do Atributo
        add(new JLabel(" Población:"));
        txtPoblacion = new JTextField();
        add(txtPoblacion);

        // 3er Atributo
        add(new JLabel(" Región/Provincia:"));
        txtRegion = new JTextField();
        add(txtRegion);

        // 4to Atributo
        add(new JLabel(" ¿Es Capital?"));
        chkEsCapital = new JCheckBox();
        add(chkEsCapital);

        // Botón
        btnGuardar = new JButton("Guardar Ciudad");
        add(new JLabel("")); // Espacio vacío para alinear el botón a la derecha
        add(btnGuardar);

        // Lógica del botón de guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatosCiudad();
            }
        });
    }

    private void guardarDatosCiudad() {
        try {
            // Recoger los datos de la interfaz
            String nombre = txtNombre.getText();
            // Validar que el campo no esté vacío
            if(nombre.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "El nombre de la ciudad es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int poblacion = Integer.parseInt(txtPoblacion.getText());
            String region = txtRegion.getText();
            boolean esCapital = chkEsCapital.isSelected();
            
            // Instanciar el modelo con los 4 atributos
            Ciudad nuevaCiudad = new Ciudad(nombre, poblacion, region, esCapital);
            
            // Usar el controlador para guardar la ciudad
            gestor.agregarCiudad(nuevaCiudad);
            
            JOptionPane.showMessageDialog(this, "Ciudad '" + nombre + "' registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar los campos para un nuevo ingreso
            txtNombre.setText("");
            txtPoblacion.setText("");
            txtRegion.setText("");
            chkEsCapital.setSelected(false);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: La población debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}

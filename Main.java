import Controlador.GestorPoblacion;
import Vista.VentanaControlPoblacional;
import javax.swing.SwingUtilities;

// Archivo principal para iniciar el programa
public class Main {

    public static void main(String[] args) {
        // Ejecuta la ventana de forma fluida para que no se pegue
        SwingUtilities.invokeLater(() -> {
            
            // Creamos el administrador que guardará los países que vayamos agregando
            GestorPoblacion gestor = new GestorPoblacion();

            // Creamos la ventana de la aplicación y le pasamos el administrador de datos
            VentanaControlPoblacional ventana = new VentanaControlPoblacional(gestor);

            // Mostramos la ventana en la pantalla
            ventana.setVisible(true);
        });
    }
}
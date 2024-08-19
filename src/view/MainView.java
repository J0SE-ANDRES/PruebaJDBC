package view;

import connection.ConexionConnection;
import controller.PersonaController;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import model.PersonaModel;

/**
 *
 * @author Jose Torres
 */
public class MainView {

    public static void main(String[] args) {

        PersonaController p = new PersonaController();

        ConexionConnection con = new ConexionConnection();

        PersonaModel persona1 = new PersonaModel();

        while (true) {

            try {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                          PRUEBA JDBC
                                                                          1. Crear Persona
                                                                          2. Actualizar Persona
                                                                          3. Consultar Persona
                                                                          4. Eliminar Persona
                                                                          5. Salir
                                                                          """));

                switch (opcion) {
                    case 1 -> {
                        p.crear(persona1);
                    }

                    case 2 -> {
                        int id;

                        id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la Persona a Actualizar"));

                        p.actualizar(persona1, id);
                    }

                    case 3 -> {
                        List<PersonaModel> personas = p.consultar();

                        JOptionPane.showMessageDialog(null, "Los usuarios en la base de datos son:");
                        
                        for (PersonaModel persona : personas) {
                            JOptionPane.showMessageDialog(null, persona);
                        }
                    }

                    case 4 -> {
                        int id;

                        id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la Persona a Eliminar"));

                        p.eliminar(id);
                    }

                    case 5 -> {
                        con.cerrarConexion();
                        System.exit(0);
                    }

                    default ->
                        JOptionPane.showMessageDialog(null, "La opcion ingresada no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }

}

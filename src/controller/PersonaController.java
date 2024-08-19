package controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import connection.ConexionConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.PersonaModel;

/**
 *
 * @author Jose Torres
 */
public class PersonaController {

    private ConexionConnection conexion;

    public PersonaController() {
        conexion = new ConexionConnection();
    }

    @SuppressWarnings("empty-statement")
    public void crear(PersonaModel persona) {
        Connection con = conexion.getConexion();

        String nombre;
        String apellido;

        try {

            nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Persona");

            apellido = JOptionPane.showInputDialog("Ingrese el apellido de la Persona");

            Statement statement = con.createStatement();
            statement.execute("INSERT INTO `persona` (`nombre`, `apellido`) "
                    + "VALUES ('" + nombre + "', '" + apellido + "')", statement.RETURN_GENERATED_KEYS
            );

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                persona.setId(idGenerado);
            }

            JOptionPane.showMessageDialog(null, "Persona creada exitosamente");

//            System.out.println(persona);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el metodo crear de la clase PersonaController " + ex, "ERROR", JOptionPane.ERROR_MESSAGE);;
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("empty-statement")
    public void actualizar(PersonaModel persona, int id) {
        Connection con = conexion.getConexion();

        String nombre;
        String apellido;

        try {

            //Verificar si el id existe
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM `persona` WHERE `id` = " + id);

            rs.next();
            int contador = rs.getInt(1);

            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "El ID ingresado no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {

                nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Persona");

                apellido = JOptionPane.showInputDialog("Ingrese el apellido de la Persona");

                Statement statement = con.createStatement();
                statement.execute("UPDATE `persona` SET "
                        + "`nombre` = '" + nombre + "', `apellido` = '" + apellido + "' "
                        + "WHERE `persona`.`id` = " + id);

                JOptionPane.showMessageDialog(null, "La persona con el id " + id + " se ha actualizado correctamente");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el metodo actualizar de la clase PersonaController " + ex, "ERROR", JOptionPane.ERROR_MESSAGE);;
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("empty-statement")
    public List<PersonaModel> consultar() {
        Connection con = conexion.getConexion();
        List<PersonaModel> personas = new ArrayList<>();

        try {

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM persona");

            while (rs.next()) {
                PersonaModel persona = new PersonaModel(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
                personas.add(persona);
//                System.out.println(persona);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el metodo consultar de la clase PersonaController " + ex, "ERROR", JOptionPane.ERROR_MESSAGE);;
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }

    @SuppressWarnings("empty-statement")
    public void eliminar(int id) {
        Connection con = conexion.getConexion();

        try {

            //Verificar si el id existe
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM `persona` WHERE `id` = " + id);

            rs.next();
            int contador = rs.getInt(1);

            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "El ID ingresado no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {

                Statement statement = con.createStatement();
                statement.execute("DELETE FROM persona WHERE `persona`.`id` = " + id);

                JOptionPane.showMessageDialog(null, "Persona eliminada exitosamente");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el metodo eliminar de la clase PersonaController " + ex, "ERROR", JOptionPane.ERROR_MESSAGE);;
            Logger.getLogger(ConexionConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

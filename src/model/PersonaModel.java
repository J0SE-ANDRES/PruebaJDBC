package model;

/**
 *
 * @author Jose Torres
 */
public class PersonaModel {

    private int id;
    private String nombre;
    private String apellido;

    public PersonaModel() {
    }

    public PersonaModel(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public PersonaModel(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    @Override
    public String toString() {
        return "Id: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido;
    }

}

package es.gestocar.beans;

import java.util.List;

/**
 *
 * @author javier
 */
public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String dni;
    private Boolean campoBaja;
    private List<Vehiculo> Vehiculos;
    
    
        // Constructor por defecto
    public Usuario() {}

    // Constructor que acepta un Short
    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getCampoBaja() {
        return campoBaja;
    }

    public void setCampoBaja(Boolean campoBaja) {
        this.campoBaja = campoBaja;
    }

    public List<Vehiculo> getVehiculos() {
        return Vehiculos;
    }

    public void setVehiculos(List<Vehiculo> Vehiculos) {
        this.Vehiculos = Vehiculos;
    }

  
}

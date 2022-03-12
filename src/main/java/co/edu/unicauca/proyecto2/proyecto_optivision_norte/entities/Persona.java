package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "Persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String tipoIdentificacion;
	private Long identificacion;
	private String nombres;
	private String apellidos;
	private String direccion;
    private String telefono;
	private String email;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public Long getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Persona() {
    }
    
}

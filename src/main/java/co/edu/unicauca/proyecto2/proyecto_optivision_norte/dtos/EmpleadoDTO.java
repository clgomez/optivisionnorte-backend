package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;

import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;

public class EmpleadoDTO implements Serializable{

    private long id;
	private String tipoIdentificacion;
	private Long identificacion;
	private String nombres;
	private String apellidos;
	private String direccion;
    private String telefono;
	private String email;
    private String password;
	private String rol;
	private String estado;


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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EmpleadoDTO() {
    }

    public void convertirEmpleado_a_DTO (Empleado objEmpleado)
    {
        this.id = objEmpleado.getId();
	    this.tipoIdentificacion = objEmpleado.getTipoIdentificacion();
	    this.identificacion = objEmpleado.getIdentificacion();
	    this.nombres = objEmpleado.getNombres();
        this.apellidos =objEmpleado.getApellidos();
	    this.direccion = objEmpleado.getDireccion();
        this.telefono = objEmpleado.getTelefono();
	    this.email = objEmpleado.getEmail();
        this.password = objEmpleado.getPassword();
	    this.rol = objEmpleado.getRol();
	    this.estado = objEmpleado.getEstado();
    }
  
    
}

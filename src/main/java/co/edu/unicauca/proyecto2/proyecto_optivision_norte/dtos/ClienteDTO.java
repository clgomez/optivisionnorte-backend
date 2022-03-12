package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;

import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;

public class ClienteDTO implements Serializable{

    private long id;
	private String tipoIdentificacion;
	private Long identificacion;
	private String nombres;
	private String apellidos;
	private String direccion;
    private String telefono;
	private String email;
    private Integer edad;
	private String sexo;
	private String fechaNacimiento;
	private String ocupacion;
 

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public ClienteDTO() {
    }
   
    public void convertirCliente_a_DTO (Cliente objCliente)
    {
        this.id = objCliente.getId();
	    this.tipoIdentificacion = objCliente.getTipoIdentificacion();
	    this.identificacion = objCliente.getIdentificacion();
	    this.nombres = objCliente.getNombres();
        this.apellidos =objCliente.getApellidos();
	    this.direccion = objCliente.getDireccion();
        this.telefono = objCliente.getTelefono();
	    this.email = objCliente.getEmail();
        this.edad = objCliente.getEdad();
	    this.sexo = objCliente.getSexo();
	    this.fechaNacimiento = objCliente.getFechaNacimiento();
	    this.ocupacion = objCliente.getOcupacion();

    }
    
    
}

package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{

    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERSONAS")
	@SequenceGenerator(name="S_PERSONAS", sequenceName="S_PERSONAS", allocationSize=1)
	private long id;
	
	@NotNull(message = "Please provider a tipoIdentificacion")
	String tipoIdentificacion;
	
	@NotNull(message = "Please provider a identificacion")
	Long identificacion;
	
	@NotNull(message = "Please provider a nombres")
	@Size(min=2, max=30)
	String nombres;
	
	@NotNull(message = "Please provider a apellidos")
	@Size(min=2, max=30)
	String apellidos;
				
	@Size(min=2, max=60)
	String direccion;

    String telefono;
	
	@NotNull(message = "Please provider a email")
	@Email(message = "Please provider a valid email address")
	@NotBlank(message = "The field cannot is empty string")
	String email;

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

    public Persona(long id, String tipoIdentificacion, Long identificacion, String nombres, String apellidos,
            String direccion, String telefono, String email) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Persona() {
    }
   
    
}

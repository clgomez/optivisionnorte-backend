package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Empleado")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Empleado extends Persona{
    
	@Column(name = "emp_password")
	private String password;
	@Column(name = "emp_rol")
	private String rol;
	@Column(name = "emp_estado")
	private String estado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Cita> citas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Consulta> consultas; 
	
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

	public Empleado(long id, String tipoIdentificacion, Long identificacion, String nombres, String apellidos,
			String direccion, String telefono, String email, String password, String rol, String estado) {
		super(id, tipoIdentificacion, identificacion, nombres, apellidos, direccion, telefono, email);
		this.password = password;
		this.rol = rol;
		this.estado = estado;

	}

	public Empleado() {
		this.citas = new ArrayList<Cita>();
		this.consultas = new ArrayList<Consulta>();
	}

	public List<Cita> getCitas() {
		return this.citas;
	}
	    
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public void agregarCita(Cita objCita)
	{
		this.citas.add(objCita);
	}

	public List <Consulta> getConsultas()
	{
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	public void agregarConsulta(Consulta objConsulta)
	{
		this.consultas.add(objConsulta);
	}
}

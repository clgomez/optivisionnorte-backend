package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Cliente extends Persona{

	@Column(name = "cli_edad")
	private Integer edad;
	@Column(name = "cli_sexo")
	private String sexo;
	@Column(name = "cli_fecha_nacimiento")
	private String fechaNacimiento;
	@Column(name = "cli_ocupacion")
	private String ocupacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Cita> citas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Consulta> consultas; 
	

	/* @OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Factura> facturas;  */

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
	
	public Cliente(long id, String tipoIdentificacion, Long identificacion, String nombres, String apellidos,
			String direccion, String telefono, String email, Integer edad, String sexo, String fechaNacimiento,
			String ocupacion) {
		super(id, tipoIdentificacion, identificacion, nombres, apellidos, direccion, telefono, email);
		this.edad = edad;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.ocupacion = ocupacion;
	}
	public Cliente() {
		this.citas = new ArrayList<Cita>();
		this.consultas = new ArrayList<Consulta>();
		//this.facturas = new ArrayList<Factura>();

	}

	public List <Cita> getCitas()
	{
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

	/* public List <Factura> getFacturas()
	{
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	public void agregarFactura(Factura objFactura)
	{
		this.facturas.add(objFactura);
	} */


}


package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.*;
import javax.persistence.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.ClienteDTO;

@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Cliente extends Persona{

	private Integer edad;
	private String sexo;
	private String fechaNacimiento;
	private String ocupacion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Cita> citas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Consulta> consultas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objCliente")
	private List <Factura> facturas;

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
	
	public Cliente() {
		this.citas = new ArrayList<Cita>();
		this.consultas = new ArrayList<Consulta>();
		this.facturas = new ArrayList<Factura>();

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

	public List <Factura> getFacturas()
	{
		return this.facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	public void agregarFactura(Factura objFactura)
	{
		this.facturas.add(objFactura);
	}

	public void convertirDTO_a_Cliente(ClienteDTO objClienteDTO)
    {
        //this.setId(objClienteDTO.getId());
	    this.setTipoIdentificacion(objClienteDTO.getTipoIdentificacion()); 
	    this.setIdentificacion(objClienteDTO.getIdentificacion());
	    this.setNombres(objClienteDTO.getNombres());
        this.setApellidos(objClienteDTO.getApellidos());
	    this.setDireccion(objClienteDTO.getDireccion());
        this.setTelefono(objClienteDTO.getTelefono());
	    this.setEmail(objClienteDTO.getEmail());
		
        this.edad = objClienteDTO.getEdad();
	    this.sexo = objClienteDTO.getSexo();
	    this.fechaNacimiento = objClienteDTO.getFechaNacimiento();
	    this.ocupacion = objClienteDTO.getOcupacion();

    }

}

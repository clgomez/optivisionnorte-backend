package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.*;
import javax.persistence.*;
@Entity
@Table(name = "Empleado")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Empleado extends Persona{
    
	private String password;
	private String rol;
	private String estado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Consulta> consultas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Factura> facturas; 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Pedido> pedidos; 
	
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

	public Empleado() {
		this.consultas = new ArrayList<Consulta>();
		this.facturas = new ArrayList<Factura>();
		this.pedidos = new ArrayList<Pedido>();
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

	public List <Pedido> getPedidos()
	{
		return this.pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public void agregarPedido(Pedido objPedido)
	{
		this.pedidos.add(objPedido);
	}
	
}

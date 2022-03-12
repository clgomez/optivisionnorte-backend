package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	String tipoIdentificacion;
	String identificacion;
	String razonSocial;
	String direccion;
    String telefono;
	String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objEmpleado")
	private List <Pedido> pedidos;

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
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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
    public Proveedor() {
        this.pedidos = new ArrayList<Pedido>();
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    } 
    public void agregarPedido(Pedido objPedido)
	{
		this.pedidos.add(objPedido);
	}
    
}

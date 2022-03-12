package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Producto")
public class Producto {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private String referencia;
    private String material;
    private String precio; 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objProducto")
	private List <DetalleFactura> detallesFactura; 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objProducto")
	private List <DetallePedido> detallesPedido; 
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public Producto() {
        this.detallesFactura = new ArrayList<DetalleFactura>();
        this.detallesPedido = new ArrayList<DetallePedido>();
    }
    public List<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }
    public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
        this.detallesFactura = detallesFactura;
    }
    public void agregarDetalleFactura(DetalleFactura objDetalleFactura)
	{
		this.detallesFactura.add(objDetalleFactura);
	}
    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
    public void agregarDetallePedido(DetallePedido objDetallePedido)
	{
		this.detallesPedido.add(objDetallePedido);
	}

}

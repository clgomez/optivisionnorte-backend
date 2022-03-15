package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;

import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Producto;

public class ProductoDTO  implements Serializable{

    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private String referencia;
    private String material;
    private String precio;

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

    public ProductoDTO() {
    } 

    public void convertirProducto_a_DTO (Producto objProducto)
    {
        this.id = objProducto.getId();
	    this.nombre = objProducto.getNombre();
	    this.descripcion = objProducto.getDescripcion();
	    this.marca = objProducto.getMarca();
        this.referencia =objProducto.getReferencia();
	    this.material = objProducto.getMaterial();
        this.precio = objProducto.getPrecio();
	 
    }

    
    
}

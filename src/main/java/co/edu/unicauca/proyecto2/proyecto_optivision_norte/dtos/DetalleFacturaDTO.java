package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;


import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetalleFactura;

public class DetalleFacturaDTO implements Serializable{

    private Long id;
    private Long cantidad;
    private Float precioUnitario;
    private Float subtotal;
    private Long idFactura;
    private Long idProducto;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Float getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public Float getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
    public Long getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    
    public DetalleFacturaDTO() {
    }

    public void convertirDetalleFactura_a_DTO (DetalleFactura objDetalleFactura)
    {
        this.id = objDetalleFactura.getId();
        this.cantidad = objDetalleFactura.getCantidad();
        this.precioUnitario = objDetalleFactura.getPrecioUnitario();
        this.subtotal = objDetalleFactura.getSubtotal();
        this.idFactura = objDetalleFactura.getObjFactura().getId();
        this.idProducto = objDetalleFactura.getObjProducto().getId();
    }


    
    
    
}

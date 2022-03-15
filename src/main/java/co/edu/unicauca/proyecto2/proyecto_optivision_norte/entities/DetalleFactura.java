package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.DetalleFacturaDTO;

@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cantidad;
    private Float precioUnitario;
    private Float subtotal;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura objFactura;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto objProducto;

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
    public Factura getObjFactura() {
        return objFactura;
    }
    public void setObjFactura(Factura objFactura) {
        this.objFactura = objFactura;
    }
    public Producto getObjProducto() {
        return objProducto;
    }
    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }
    public DetalleFactura() {
    }

    public void convertirDTO_a_DetalleFactura (DetalleFacturaDTO objDetalleFacturaDTO)
    {
        //this.id = objDetalleFacturaDTO.getId();
        this.cantidad = objDetalleFacturaDTO.getCantidad();
        this.precioUnitario = objDetalleFacturaDTO.getPrecioUnitario();
        this.subtotal = objDetalleFacturaDTO.getSubtotal();
        this.objFactura = new Factura();
        this.objFactura.setId(objDetalleFacturaDTO.getIdFactura()); 
        this.objProducto = new Producto();
        this.objProducto.setId(objDetalleFacturaDTO.getIdProducto());
    }
        
}

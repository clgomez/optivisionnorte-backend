package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

@Entity
@Table(name = "DetallePedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cantidad;
    private Float precioUnitario;
    private Float subtotal;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido objPedido;

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
    public Pedido getObjPedido() {
        return objPedido;
    }
    public void setObjPedido(Pedido objPedido) {
        this.objPedido = objPedido;
    }
    public Producto getObjProducto() {
        return objProducto;
    }
    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }
    public DetallePedido() {
    }
    
}

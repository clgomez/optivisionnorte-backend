package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private Float total;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado objEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor objProveedor;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Float getTotal() {
        return total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }
    public Empleado getObjEmpleado() {
        return objEmpleado;
    }
    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }
    public Proveedor getObjProveedor() {
        return objProveedor;
    }
    public void setObjProveedor(Proveedor objProveedor) {
        this.objProveedor = objProveedor;
    }
    public Pedido() {
    }
    
}

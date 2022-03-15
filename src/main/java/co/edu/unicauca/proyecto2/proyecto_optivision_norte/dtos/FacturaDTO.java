package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;


import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Factura;

public class FacturaDTO implements Serializable{
    
    private Long id;
    private String fecha;
    private Float total;
    private Float abono;
    private Float saldo;
    private Long idCliente;
    private Long idEmpleado;

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
    public Float getAbono() {
        return abono;
    }
    public void setAbono(Float abono) {
        this.abono = abono;
    }
    public Float getSaldo() {
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public Long getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public FacturaDTO() {
    }

    public void convertirFactura_a_DTO (Factura objFactura)
    {
        this.id = objFactura.getId();
        this.fecha = objFactura.getFecha();
        this.total = objFactura.getTotal();
        this.abono = objFactura.getAbono();
        this.saldo = objFactura.getSaldo();
        this.idCliente = objFactura.getObjCliente().getId();
        this.idEmpleado = objFactura.getObjEmpleado().getId();
    }
    

    

    

}

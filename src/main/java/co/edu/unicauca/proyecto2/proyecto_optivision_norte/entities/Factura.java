package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.*;
import javax.persistence.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.FacturaDTO;

@Entity
@Table(name = "Factura")
public class Factura {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private Float total;
    private Float abono;
    private Float saldo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente objCliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado objEmpleado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objFactura")
	private List <DetalleFactura> detallesFactura; 

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
    public Cliente getObjCliente() {
        return objCliente;
    }
    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    } 
    public Empleado getObjEmpleado() {
        return objEmpleado;
    }
    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }
    public Factura() {
        this.detallesFactura = new ArrayList<DetalleFactura>();
    }
    public List <DetalleFactura> getDetallesFactura()
	{
		return this.detallesFactura;
	}
	public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
		this.detallesFactura = detallesFactura;
	}
	public void agregarDetalleFactura(DetalleFactura objDetalleFactura)
	{
		this.detallesFactura.add(objDetalleFactura);
	}

    public void convertirDTO_a_Factura(FacturaDTO objFacturaDTO)
    {
        //this.id = objFacturaDTO.getId();
        this.fecha = objFacturaDTO.getFecha();
        this.total = objFacturaDTO.getTotal();
        this.abono = objFacturaDTO.getAbono();
        this.saldo = objFacturaDTO.getSaldo();
        this.objCliente = new Cliente();
        this.objCliente.setId(objFacturaDTO.getIdCliente());
        this.objEmpleado = new Empleado();
        this.objEmpleado.setId(objFacturaDTO.getIdEmpleado());
        
    }

}

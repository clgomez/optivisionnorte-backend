package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

@Entity
@Table(name = "Cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String hora;
    private boolean estado;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente objCliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado objEmpleado;
            
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
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
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
    
    public Cita() {
    }
}
 
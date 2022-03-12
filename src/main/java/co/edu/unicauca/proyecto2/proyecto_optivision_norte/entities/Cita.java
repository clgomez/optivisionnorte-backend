package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.CitaDTO;
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
   
    public Cita() {
    }
    
    public void convertirDTO_a_Cita (CitaDTO objCitaDTO)
    {
        //this.id = objCitaDTO.getId();
        this.fecha = objCitaDTO.getFecha();
        this.hora = objCitaDTO.getHora();
        this.estado = objCitaDTO.isEstado();
        this.objCliente = new Cliente();
        this.objCliente.setId(objCitaDTO.getIdCliente());
    }
}
 
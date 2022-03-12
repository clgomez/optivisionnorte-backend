package co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos;

import java.io.Serializable;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cita;

public class CitaDTO implements Serializable{

    private Long id;
    private String fecha;
    private String hora;
    private boolean estado;
    private Long idCliente;
 
   
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
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public CitaDTO() {
    }

    public void convertirCita_a_DTO (Cita objCita)
    {
        this.id = objCita.getId();
        this.fecha = objCita.getFecha();
        this.hora = objCita.getHora();
        this.estado = objCita.isEstado();
        this.idCliente = objCita.getObjCliente().getId();
    }
    
}

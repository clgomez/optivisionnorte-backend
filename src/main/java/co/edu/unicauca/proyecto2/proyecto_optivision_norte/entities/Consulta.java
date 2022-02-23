package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import java.util.List;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;



@Entity
@Table(name = "Consulta")
@TypeDef(name = "JSON", typeClass = JsonStringType.class)
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private String antecedentes;

    @Type(type = "JSON")
    @Column(columnDefinition = "JSON")
    private List <Object> examenes;
    private String fecha;
    private String hirschberg;
    private String ducciones;
    private String versiones;
    private String disposicion;
    private String remision;
    private Long codigo;
    private String diagnostico;
    private String control;
    private String tipoLente;
    private String distanciaPupilar;
    private String tratamiento;
    private String observaciones;

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
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getAntecedentes() {
        return antecedentes;
    }
    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }
  
    public List<Object> getExamenes() {
        return examenes;
    }
    public void setExamenes(List<Object> examenes) {
        this.examenes = examenes;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHirschberg() {
        return hirschberg;
    }
    public void setHirschberg(String hirschberg) {
        this.hirschberg = hirschberg;
    }
    public String getDucciones() {
        return ducciones;
    }
    public void setDucciones(String ducciones) {
        this.ducciones = ducciones;
    }
    public String getVersiones() {
        return versiones;
    }
    public void setVersiones(String versiones) {
        this.versiones = versiones;
    }
    public String getDisposicion() {
        return disposicion;
    }
    public void setDisposicion(String disposicion) {
        this.disposicion = disposicion;
    }
    public String getRemision() {
        return remision;
    }
    public void setRemision(String remision) {
        this.remision = remision;
    }
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public String getControl() {
        return control;
    }
    public void setControl(String control) {
        this.control = control;
    }
    public String getTipoLente() {
        return tipoLente;
    }
    public void setTipoLente(String tipoLente) {
        this.tipoLente = tipoLente;
    }
    public String getDistanciaPupilar() {
        return distanciaPupilar;
    }
    public void setDistanciaPupilar(String distanciaPupilar) {
        this.distanciaPupilar = distanciaPupilar;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
    public Consulta() {
    } 
    
}
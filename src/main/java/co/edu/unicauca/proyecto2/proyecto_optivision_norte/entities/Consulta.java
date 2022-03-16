package co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities;

import javax.persistence.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.ConsultaDTO;

@Entity
@Table(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private String antecedentes;

    private Long AVLSC_OD;
    private Long AVLSC_OI;
    private Long AVLPC_OD;
    private Long AVPSC_OI;
    private Long PH_OD;
    private Long PH_OI;
    private Long LENS_OD;
    private Long LENS_OI;
    private Long BIOM_OD;
    private Long BIOM_OI;
    private Long REF_OD;
    private Long REF_OI;
    private Long QUER_OD;
    private Long QUER_OI;
    private Long PIO_OD;
    private Long PIO_OI;
    private Long TESTC_OD;
    private Long TESTC_OI;
    private Long OFTA_OD;
    private Long OFTA_OI;
    private Long KAPPA_OD;
    private Long KAPPA_OI;
   
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
   
    public Long getAVLSC_OD() {
        return AVLSC_OD;
    }
    public void setAVLSC_OD(Long AVLSC_OD) {
        this.AVLSC_OD = AVLSC_OD;
    }
    public String getFecha() {
        return fecha;
    }
    
    public Long getAVLSC_OI() {
        return AVLSC_OI;
    }
    public void setAVLSC_OI(Long AVLSC_OI) {
        this.AVLSC_OI = AVLSC_OI;
    }
    public Long getAVLPC_OD() {
        return AVLPC_OD;
    }
    public void setAVLPC_OD(Long AVLPC_OD) {
        this.AVLPC_OD = AVLPC_OD;
    }
    public Long getAVPSC_OI() {
        return AVPSC_OI;
    }
    public void setAVPSC_OI(Long AVPSC_OI) {
        this.AVPSC_OI = AVPSC_OI;
    }
    public Long getPH_OD() {
        return PH_OD;
    }
    public void setPH_OD(Long PH_OD) {
        this.PH_OD = PH_OD;
    }
    public Long getPH_OI() {
        return PH_OI;
    }
    public void setPH_OI(Long PH_OI) {
        PH_OI = this.PH_OI;
    }
    public Long getLENS_OD() {
        return LENS_OD;
    }
    public void setLENS_OD(Long LENS_OD) {
        this.LENS_OD = LENS_OD;
    }
    public Long getLENS_OI() {
        return LENS_OI;
    }
    public void setLENS_OI(Long LENS_OI) {
        this.LENS_OI = LENS_OI;
    }
    public Long getBIOM_OD() {
        return BIOM_OD;
    }
    public void setBIOM_OD(Long BIOM_OD) {
        this.BIOM_OD = BIOM_OD;
    }
    public Long getBIOM_OI() {
        return BIOM_OI;
    }
    public void setBIOM_OI(Long BIOM_OI) {
        this.BIOM_OI = BIOM_OI;
    }
    public Long getREF_OD() {
        return REF_OD;
    }
    public void setREF_OD(Long REF_OD) {
        this.REF_OD = REF_OD;
    }
    public Long getREF_OI() {
        return REF_OI;
    }
    public void setREF_OI(Long REF_OI) {
        this.REF_OI = REF_OI;
    }
    public Long getQUER_OD() {
        return QUER_OD;
    }
    public void setQUER_OD(Long QUER_OD) {
        this.QUER_OD = QUER_OD;
    }
    public Long getQUER_OI() {
        return QUER_OI;
    }
    public void setQUER_OI(Long QUER_OI) {
        this.QUER_OI = QUER_OI;
    }
    public Long getPIO_OD() {
        return PIO_OD;
    }
    public void setPIO_OD(Long PIO_OD) {
        this.PIO_OD = PIO_OD;
    }
    public Long getPIO_OI() {
        return PIO_OI;
    }
    public void setPIO_OI(Long PIO_OI) {
        this.PIO_OI = PIO_OI;
    }
    public Long getTESTC_OD() {
        return TESTC_OD;
    }
    public void setTESTC_OD(Long TESTC_OD) {
        this.TESTC_OD = TESTC_OD;
    }
    public Long getTESTC_OI() {
        return TESTC_OI;
    }
    public void setTESTC_OI(Long TESTC_OI) {
        this.TESTC_OI = TESTC_OI;
    }
    public Long getOFTA_OD() {
        return OFTA_OD;
    }
    public void setOFTA_OD(Long OFTA_OD) {
        this.OFTA_OD = OFTA_OD;
    }
    public Long getOFTA_OI() {
        return OFTA_OI;
    }
    public void setOFTA_OI(Long OFTA_OI) {
        this.OFTA_OI = OFTA_OI;
    }
    public Long getKAPPA_OD() {
        return KAPPA_OD;
    }
    public void setKAPPA_OD(Long KAPPA_OD) {
        this.KAPPA_OD = KAPPA_OD;
    }
    public Long getKAPPA_OI() {
        return KAPPA_OI;
    }
    public void setKAPPA_OI(Long KAPPA_OI) {
        this.KAPPA_OI = KAPPA_OI;
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

    public void convertirDTO_a_Consulta (ConsultaDTO objConsultaDTO)
    {
        //this.id = objConsultaDTO.getId();
        this.motivo = objConsultaDTO.getMotivo();
        this.antecedentes = objConsultaDTO.getAntecedentes();
    
        this.AVLSC_OD = objConsultaDTO.getAVLSC_OD();
        this.AVLSC_OI = objConsultaDTO.getAVLSC_OI();
        this.AVLPC_OD = objConsultaDTO.getAVLPC_OD();
        this.AVPSC_OI = objConsultaDTO.getAVPSC_OI();
        this.PH_OD = objConsultaDTO.getPH_OD();
        this.PH_OI = objConsultaDTO.getPH_OI();
        this.LENS_OD = objConsultaDTO.getLENS_OD();
        this.LENS_OI = objConsultaDTO.getLENS_OI();
        this.BIOM_OD = objConsultaDTO.getBIOM_OD();
        this.BIOM_OI = objConsultaDTO.getBIOM_OI();
        this.REF_OD = objConsultaDTO.getREF_OD();
        this.REF_OI = objConsultaDTO.getREF_OI();
        this.QUER_OD = objConsultaDTO.getQUER_OD();
        this.QUER_OI = objConsultaDTO.getQUER_OI();
        this.PIO_OD = objConsultaDTO.getPIO_OD();
        this.PIO_OI = objConsultaDTO.getPIO_OI();
        this.TESTC_OD = objConsultaDTO.getTESTC_OD();
        this.TESTC_OI = objConsultaDTO.getTESTC_OI();
        this.OFTA_OD = objConsultaDTO.getOFTA_OD();
        this.OFTA_OI = objConsultaDTO.getOFTA_OI();
        this.KAPPA_OD = objConsultaDTO.getKAPPA_OD();
        this.KAPPA_OI = objConsultaDTO.getKAPPA_OI(); 
    
        this.fecha = objConsultaDTO.getFecha();
        this.hirschberg = objConsultaDTO.getHirschberg();
        this.ducciones = objConsultaDTO.getDucciones();
        this.versiones = objConsultaDTO.getVersiones();
        this.disposicion = objConsultaDTO.getDisposicion();
        this.remision = objConsultaDTO.getRemision();
        this.codigo = objConsultaDTO.getCodigo();
        this.diagnostico = objConsultaDTO.getDiagnostico();
        this.control = objConsultaDTO.getControl();
        this.tipoLente = objConsultaDTO.getTipoLente();
        this.distanciaPupilar = objConsultaDTO.getDistanciaPupilar();
        this.tratamiento = objConsultaDTO.getTratamiento();
        this.observaciones = objConsultaDTO.getObservaciones();
    
        this.objCliente = new Cliente();
        objCliente.setId(objConsultaDTO.getIdCliente());
        this.objEmpleado = new Empleado();
        this.objEmpleado.setId(objConsultaDTO.getIdEmpleado());
    }
    
}
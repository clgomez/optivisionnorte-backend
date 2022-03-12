package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;

import javax.validation.Valid;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Consulta;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IConsultaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/consulta")
public class ConsultaController {
    
    @Autowired
    private IConsultaService consultaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Consulta objConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        //Map<String, Object> objMapConsulta = new HashMap<>();
        JSONObject objJSONConsulta = new JSONObject();
        Consulta consulta = new Consulta();

        try {
            consulta = this.consultaService.save(objConsulta);
            
            objJSONConsulta.put("id",consulta.getId());
            objJSONConsulta.put("motivo",consulta.getMotivo());
            objJSONConsulta.put("antecedentes",consulta.getAntecedentes());
            objJSONConsulta.put("examenes",consulta.getExamenes());
            objJSONConsulta.put("fecha",consulta.getFecha());
            objJSONConsulta.put("hirschberg",consulta.getHirschberg());
            objJSONConsulta.put("ducciones",consulta.getDucciones());
            objJSONConsulta.put("versiones",consulta.getVersiones());
            objJSONConsulta.put("disposicion",consulta.getDisposicion());
            objJSONConsulta.put("remision",consulta.getRemision());                
            objJSONConsulta.put("codigo",consulta.getCodigo());
            objJSONConsulta.put("diagnostico",consulta.getDiagnostico());
            objJSONConsulta.put("control",consulta.getControl());
            objJSONConsulta.put("tipo de lente",consulta.getTipoLente());
            objJSONConsulta.put("distancia pupilar",consulta.getDistanciaPupilar());
            objJSONConsulta.put("tratamiento",consulta.getTratamiento());
            objJSONConsulta.put("observaciones",consulta.getObservaciones());
            objJSONConsulta.put("id_cliente",consulta.getObjCliente().getId());
            objJSONConsulta.put("id_empleado",consulta.getObjEmpleado().getId());
            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONConsulta, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idConsulta){
        Map<String, Object> respuesta = new HashMap<>();

        JSONObject objJSONConsulta = new JSONObject();
            
        try {
            Optional<Consulta> optConsulta = this.consultaService.findById(idConsulta);
            Consulta consulta = new Consulta();
            if (optConsulta.isPresent()) {
                consulta = optConsulta.get();
                
                objJSONConsulta.put("id",consulta.getId());
                objJSONConsulta.put("motivo",consulta.getMotivo());
                objJSONConsulta.put("antecedentes",consulta.getAntecedentes());
                objJSONConsulta.put("examenes",consulta.getExamenes());
                objJSONConsulta.put("fecha",consulta.getFecha());
                objJSONConsulta.put("hirschberg",consulta.getHirschberg());
                objJSONConsulta.put("ducciones",consulta.getDucciones());
                objJSONConsulta.put("versiones",consulta.getVersiones());
                objJSONConsulta.put("disposicion",consulta.getDisposicion());
                objJSONConsulta.put("remision",consulta.getRemision());                
                objJSONConsulta.put("codigo",consulta.getCodigo());
                objJSONConsulta.put("diagnostico",consulta.getDiagnostico());
                objJSONConsulta.put("control",consulta.getControl());
                objJSONConsulta.put("tipo de lente",consulta.getTipoLente());
                objJSONConsulta.put("distancia pupilar",consulta.getDistanciaPupilar());
                objJSONConsulta.put("tratamiento",consulta.getTratamiento());
                objJSONConsulta.put("observaciones",consulta.getObservaciones());
                objJSONConsulta.put("id_cliente",consulta.getObjCliente().getId());
                objJSONConsulta.put("id_empleado",consulta.getObjEmpleado().getId());

                return new ResponseEntity<JSONObject>(objJSONConsulta, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró la consulta");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
	public ResponseEntity<?> listar(){
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ninguna consulta registrada", 
				HttpStatus.NOT_FOUND);

        JSONArray ArrayJSONConsultas = new JSONArray();        
        List <Consulta> consultas = this.consultaService.findAll();
        
		if (!consultas.isEmpty()) {
            for(Consulta consulta: consultas)
            {
                JSONObject objJSONConsulta = new JSONObject();
                objJSONConsulta.put("id",consulta.getId());
                objJSONConsulta.put("motivo",consulta.getMotivo());
                objJSONConsulta.put("antecedentes",consulta.getAntecedentes());
                objJSONConsulta.put("examenes",consulta.getExamenes());
                objJSONConsulta.put("fecha",consulta.getFecha());
                objJSONConsulta.put("hirschberg",consulta.getHirschberg());
                objJSONConsulta.put("ducciones",consulta.getDucciones());
                objJSONConsulta.put("versiones",consulta.getVersiones());
                objJSONConsulta.put("disposicion",consulta.getDisposicion());
                objJSONConsulta.put("remision",consulta.getRemision());                
                objJSONConsulta.put("codigo",consulta.getCodigo());
                objJSONConsulta.put("diagnostico",consulta.getDiagnostico());
                objJSONConsulta.put("control",consulta.getControl());
                objJSONConsulta.put("tipo de lente",consulta.getTipoLente());
                objJSONConsulta.put("distancia pupilar",consulta.getDistanciaPupilar());
                objJSONConsulta.put("tratamiento",consulta.getTratamiento());
                objJSONConsulta.put("observaciones",consulta.getObservaciones());
                objJSONConsulta.put("id_cliente",consulta.getObjCliente().getId());
                objJSONConsulta.put("id_empleado",consulta.getObjEmpleado().getId());
                ArrayJSONConsultas.put(objJSONConsulta);
               
            }
			respuesta = new ResponseEntity<JSONArray>(ArrayJSONConsultas, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idConsulta, 
                                        @Valid @RequestBody Consulta objConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        Consulta consulta = new Consulta();
        JSONObject objJSONConsulta = new JSONObject();

        try {
            consulta = this.consultaService.update(idConsulta, objConsulta);

            objJSONConsulta.put("id",consulta.getId());
            objJSONConsulta.put("motivo",consulta.getMotivo());
            objJSONConsulta.put("antecedentes",consulta.getAntecedentes());
            objJSONConsulta.put("examenes",objConsulta.getExamenes());
            objJSONConsulta.put("fecha",consulta.getFecha());
            objJSONConsulta.put("hirschberg",consulta.getHirschberg());
            objJSONConsulta.put("ducciones",consulta.getDucciones());
            objJSONConsulta.put("versiones",consulta.getVersiones());
            objJSONConsulta.put("disposicion",consulta.getDisposicion());
            objJSONConsulta.put("remision",consulta.getRemision());                
            objJSONConsulta.put("codigo",consulta.getCodigo());
            objJSONConsulta.put("diagnostico",consulta.getDiagnostico());
            objJSONConsulta.put("control",consulta.getControl());
            objJSONConsulta.put("tipo de lente",consulta.getTipoLente());
            objJSONConsulta.put("distancia pupilar",consulta.getDistanciaPupilar());
            objJSONConsulta.put("tratamiento",consulta.getTratamiento());
            objJSONConsulta.put("observaciones",consulta.getObservaciones());
            objJSONConsulta.put("id_cliente",consulta.getObjCliente().getId()); 
            objJSONConsulta.put("id_empleado",consulta.getObjEmpleado().getId());           

        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONConsulta, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            consultaService.delete(idConsulta);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

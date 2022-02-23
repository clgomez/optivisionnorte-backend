package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Consulta;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IConsultaService;

@RestController
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
            Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objConsulta.setId(longAleatorio);
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

        return new ResponseEntity<>(objJSONConsulta.toMap(), HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long IdConsulta){
        Map<String, Object> respuesta = new HashMap<>();

        JSONObject objJSONConsulta = new JSONObject();
            
        try {
            Optional<Consulta> optConsulta = this.consultaService.findById(IdConsulta);
            Consulta objConsulta = new Consulta();
            if (optConsulta.isPresent()) {
                objConsulta = optConsulta.get();
                
                objJSONConsulta.put("id",objConsulta.getId());
                objJSONConsulta.put("motivo",objConsulta.getMotivo());
                objJSONConsulta.put("antecedentes",objConsulta.getAntecedentes());
                objJSONConsulta.put("examenes",objConsulta.getExamenes());
                objJSONConsulta.put("fecha",objConsulta.getFecha());
                objJSONConsulta.put("hirschberg",objConsulta.getHirschberg());
                objJSONConsulta.put("ducciones",objConsulta.getDucciones());
                objJSONConsulta.put("versiones",objConsulta.getVersiones());
                objJSONConsulta.put("disposicion",objConsulta.getDisposicion());
                objJSONConsulta.put("remision",objConsulta.getRemision());                
                objJSONConsulta.put("codigo",objConsulta.getCodigo());
                objJSONConsulta.put("diagnostico",objConsulta.getDiagnostico());
                objJSONConsulta.put("control",objConsulta.getControl());
                objJSONConsulta.put("tipo de lente",objConsulta.getTipoLente());
                objJSONConsulta.put("distancia pupilar",objConsulta.getDistanciaPupilar());
                objJSONConsulta.put("tratamiento",objConsulta.getTratamiento());
                objJSONConsulta.put("observaciones",objConsulta.getObservaciones());
                objJSONConsulta.put("id_cliente",objConsulta.getObjCliente().getId());
                objJSONConsulta.put("id_empleado",objConsulta.getObjEmpleado().getId());

                return new ResponseEntity<>(objJSONConsulta.toMap(), HttpStatus.OK);
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

        JSONArray ArrayConsultas = new JSONArray();        
        List <Consulta> consultas = this.consultaService.findAll();
        
		if (!consultas.isEmpty()) {
            for(Consulta consulta: consultas)
            {
                JSONObject objConsulta = new JSONObject();
                objConsulta.put("id",consulta.getId());
                objConsulta.put("motivo",consulta.getMotivo());
                objConsulta.put("antecedentes",consulta.getAntecedentes());
                objConsulta.put("examenes",consulta.getExamenes());
                objConsulta.put("fecha",consulta.getFecha());
                objConsulta.put("hirschberg",consulta.getHirschberg());
                objConsulta.put("ducciones",consulta.getDucciones());
                objConsulta.put("versiones",consulta.getVersiones());
                objConsulta.put("disposicion",consulta.getDisposicion());
                objConsulta.put("remision",consulta.getRemision());                
                objConsulta.put("codigo",consulta.getCodigo());
                objConsulta.put("diagnostico",consulta.getDiagnostico());
                objConsulta.put("control",consulta.getControl());
                objConsulta.put("tipo de lente",consulta.getTipoLente());
                objConsulta.put("distancia pupilar",consulta.getDistanciaPupilar());
                objConsulta.put("tratamiento",consulta.getTratamiento());
                objConsulta.put("observaciones",consulta.getObservaciones());
                objConsulta.put("id_cliente",consulta.getObjCliente().getId());
                objConsulta.put("id_empleado",consulta.getObjEmpleado().getId());
                ArrayConsultas.put(objConsulta);
               
            }
			respuesta = new ResponseEntity<>(ArrayConsultas.toList(), HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Consulta objConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        Consulta consulta = new Consulta();
        JSONObject objJSONConsulta = new JSONObject();

        try {
            //Random aleatorio = new Random(System.currentTimeMillis());
            //Long longAleatorio = aleatorio.nextLong();
            //objCliente.setId(longAleatorio);
            consulta = this.consultaService.save(objConsulta);
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

        return new ResponseEntity<>(objJSONConsulta.toMap(), HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long IdConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            consultaService.delete(IdConsulta);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

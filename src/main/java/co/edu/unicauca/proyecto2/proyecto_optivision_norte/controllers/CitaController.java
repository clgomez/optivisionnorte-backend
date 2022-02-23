package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.json.*;

import javax.validation.Valid;


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

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cita;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.ICitaService;

@RestController
@RequestMapping("/api/cita")
public class CitaController {

    @Autowired
    private ICitaService citaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Cita objCita){
        Map<String, Object> respuesta = new HashMap<>();
    	Cita cita = new Cita();
        JSONObject objJSONCita = new JSONObject();

        try {
            Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objCita.setId(longAleatorio);
            cita = this.citaService.save(objCita);

            objJSONCita.put("id",cita.getId());
            objJSONCita.put("fecha",cita.getFecha());
            objJSONCita.put("hora",cita.getHora());
            objJSONCita.put("estado",cita.isEstado());
            objJSONCita.put("id_cliente",cita.getObjCliente().getId());
            objJSONCita.put("id_empleado",cita.getObjEmpleado().getId());
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //return new ResponseEntity<Cita>(cita, HttpStatus.OK);

        return new ResponseEntity<Map<String, Object>>(objJSONCita.toMap(), HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long IdCita){
        Map<String, Object> respuesta = new HashMap<>();
        JSONObject objJSONCita = new JSONObject(); 

        try {
            Optional<Cita> optCita = this.citaService.findById(IdCita);
            Cita objCita = new Cita();
      
            if (optCita.isPresent()) {
                
               objCita = optCita.get();
               objJSONCita.put("id",objCita.getId());
               objJSONCita.put("fecha",objCita.getFecha());
               objJSONCita.put("hora",objCita.getHora());
               objJSONCita.put("estado",objCita.isEstado());
               objJSONCita.put("id_cliente",objCita.getObjCliente().getId());
               objJSONCita.put("id_empleado",objCita.getObjEmpleado().getId()); 
               
               System.out.print(objJSONCita);

               return new ResponseEntity<>(objJSONCita.toMap(), HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró la cita");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ninguna cita registrada", 
				HttpStatus.NOT_FOUND);
        
        JSONArray ArrayCitas = new JSONArray();        
		List <Cita> citas = this.citaService.findAll();
		if (!citas.isEmpty()) {
             
            for(Cita cita: citas)
            {
               JSONObject objCita = new JSONObject();
               objCita.put("id",cita.getId());
               objCita.put("fecha",cita.getFecha());
               objCita.put("hora",cita.getHora());
               objCita.put("estado",cita.isEstado());
               objCita.put("id_cliente",cita.getObjCliente().getId());
               objCita.put("id_empleado",cita.getObjEmpleado().getId());

               ArrayCitas.put(objCita);
            }
			respuesta = new ResponseEntity<>(ArrayCitas.toList(), HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Cita objCita){
        Map<String, Object> respuesta = new HashMap<>();
        Cita cita = new Cita();
        JSONObject objJSONCita = new JSONObject();

        try {
            //Random aleatorio = new Random(System.currentTimeMillis());
            //Long longAleatorio = aleatorio.nextLong();
            //objCliente.setId(longAleatorio);
            cita = this.citaService.save(objCita);
            objJSONCita.put("id",cita.getId());
            objJSONCita.put("fecha",cita.getFecha());
            objJSONCita.put("hora",cita.getHora());
            objJSONCita.put("estado",cita.isEstado());
            objJSONCita.put("id_cliente",cita.getObjCliente().getId());
            objJSONCita.put("id_empleado",cita.getObjEmpleado().getId());
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(objJSONCita.toMap(), HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long IdCita){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            citaService.delete(IdCita);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
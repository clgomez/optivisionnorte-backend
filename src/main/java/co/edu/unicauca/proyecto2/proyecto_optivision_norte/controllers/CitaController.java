package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.CitaDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cita;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.ICitaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/cita")
public class CitaController {

    @Autowired
    private ICitaService citaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody CitaDTO objCitaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Cita objCita = new Cita();
    	Cita cita = new Cita();
        CitaDTO citaDTO = new CitaDTO();
        
       try {
           
            objCita.convertirDTO_a_Cita(objCitaDTO);
            cita = this.citaService.save(objCita);
            citaDTO.convertirCita_a_DTO(cita);
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idCita){
        Map<String, Object> respuesta = new HashMap<>();
        Cita cita = new Cita();
        CitaDTO citaDTO = new CitaDTO();

        try {
            Optional<Cita> optCita = this.citaService.findById(idCita);

            if (optCita.isPresent()) {
                
               cita = optCita.get();
               citaDTO.convertirCita_a_DTO(cita); 

               return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
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
        
        List <CitaDTO> ArrayCitasDTO = new ArrayList<>();        
		List <Cita> citas = this.citaService.findAll();
		if (!citas.isEmpty()) {
             
            for(Cita cita: citas)
            {
               CitaDTO citaDTO = new CitaDTO();
               citaDTO.convertirCita_a_DTO(cita);
               ArrayCitasDTO.add(citaDTO);
            }
			respuesta = new ResponseEntity<List <CitaDTO>>(ArrayCitasDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idCita, 
                                        @Valid @RequestBody CitaDTO objCitaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Cita objCita = new Cita();
    	Cita cita = new Cita();
        CitaDTO citaDTO = new CitaDTO();
        
        try {

            objCita.convertirDTO_a_Cita(objCitaDTO);
            cita = this.citaService.update(idCita, objCita);
            citaDTO.convertirCita_a_DTO(cita);
          
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idCita){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(citaService.delete(idCita))
            {    respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            
            }   
            else   
            {    respuesta.put("Mensaje","La cita no existe");  
                 return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);

            }
            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    
}
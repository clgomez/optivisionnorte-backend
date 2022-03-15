package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.ConsultaDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Consulta;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IConsultaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/consulta")
public class ConsultaController {
    
    @Autowired
    private IConsultaService consultaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody ConsultaDTO objConsultaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Consulta objConsulta = new Consulta();
        Consulta consulta = new Consulta();
        ConsultaDTO consultaDTO = new ConsultaDTO();

        try {
            objConsulta.convertirDTO_a_Consulta(objConsultaDTO);
            consulta = this.consultaService.save(objConsulta);
            consultaDTO.convertirConsulta_a_DTO(consulta);
           
            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ConsultaDTO>(consultaDTO, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idConsulta){
        Map<String, Object> respuesta = new HashMap<>();

        ConsultaDTO consultaDTO = new ConsultaDTO();    
         
        try {
            Optional<Consulta> optConsulta = this.consultaService.findById(idConsulta);
            Consulta consulta = new Consulta();
            if (optConsulta.isPresent()) {
                consulta = optConsulta.get();
                consultaDTO.convertirConsulta_a_DTO(consulta);

                return new ResponseEntity<ConsultaDTO>(consultaDTO, HttpStatus.OK);
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

        List <ConsultaDTO> ArrayConsultasDTO = new ArrayList<>();        
        List <Consulta> consultas = this.consultaService.findAll();
        
		if (!consultas.isEmpty()) {
            for(Consulta consulta: consultas)
            {
              ConsultaDTO consultaDTO = new ConsultaDTO(); 
              consultaDTO.convertirConsulta_a_DTO(consulta);
              ArrayConsultasDTO.add(consultaDTO);
               
            }
			respuesta = new ResponseEntity<List <ConsultaDTO>>(ArrayConsultasDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idConsulta, 
                                        @Valid @RequestBody ConsultaDTO objConsultaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Consulta objConsulta = new Consulta();
        Consulta consulta = new Consulta();
        ConsultaDTO consultaDTO = new ConsultaDTO();

        try {
            objConsulta.convertirDTO_a_Consulta(objConsultaDTO);
            consulta = this.consultaService.update(idConsulta, objConsulta);
            consultaDTO.convertirConsulta_a_DTO(consulta);


        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ConsultaDTO>(consultaDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idConsulta){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(consultaService.delete(idConsulta))
            {   respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }else
            {
                respuesta.put("Mensaje","La consulta no existe");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

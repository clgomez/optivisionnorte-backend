package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.EmpleadoDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IEmpleadoService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody EmpleadoDTO objEmpleadoDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado objEmpleado = new Empleado();
    	Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        try {
            
            objEmpleado.convertirDTO_a_Empleado(objEmpleadoDTO);
            empleado = this.empleadoService.save(objEmpleado);
            empleadoDTO.convertirEmpleado_a_DTO(empleado);
        
    
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EmpleadoDTO>(empleadoDTO, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        
        try {
            Optional<Empleado> optEmpleado = this.empleadoService.findById(idEmpleado);
            if (optEmpleado.isPresent()) {
                empleado = optEmpleado.get();
                empleadoDTO.convertirEmpleado_a_DTO(empleado);
                
                return new ResponseEntity<EmpleadoDTO>(empleadoDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el empleado");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la busqueda en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
	public ResponseEntity<?> listar(){
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun empleado registrado", 
				HttpStatus.NOT_FOUND);
        List <EmpleadoDTO> ArrayEmpleadosDTO = new ArrayList<>();                
		List <Empleado> empleados = this.empleadoService.findAll();
		if (!empleados.isEmpty()) {
            for(Empleado empleado: empleados)
            {
                EmpleadoDTO empleadoDTO = new EmpleadoDTO();
                empleadoDTO.convertirEmpleado_a_DTO(empleado);
                ArrayEmpleadosDTO.add(empleadoDTO);
            }
			respuesta = new ResponseEntity< List <EmpleadoDTO>>(ArrayEmpleadosDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idEmpleado, 
                                        @Valid @RequestBody EmpleadoDTO objEmpleadoDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado objEmpleado = new Empleado();
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        try {
            objEmpleado.convertirDTO_a_Empleado(objEmpleadoDTO);
            empleado = this.empleadoService.update(idEmpleado, objEmpleado);
            empleadoDTO.convertirEmpleado_a_DTO(empleado);
            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<EmpleadoDTO>(empleadoDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(empleadoService.delete(idEmpleado))
            {   respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }else
            {
                respuesta.put("Mensaje","El empleado no existe");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validarempleado")
    public ResponseEntity<?> iniciarSesion(@RequestParam Long idEmpleado, 
                                           @RequestParam String password){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado empleado = new Empleado();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(); 
        
        try {
            Optional<Empleado> optEmpleado = this.empleadoService.validarEmpleado(idEmpleado, password);
            if (optEmpleado.isPresent()) {
                empleado = optEmpleado.get();
                empleadoDTO.convertirEmpleado_a_DTO(empleado);
              
                return new ResponseEntity<EmpleadoDTO>(empleadoDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el empleado");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la busqueda en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/identificacion/{id}")
    public ResponseEntity<?> BuscarEmpleadoPorIdentificacion(@PathVariable("id") Long identificacionEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
            Empleado empleado = new Empleado();
            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        try {
            Optional<Empleado> optEmpleado = this.empleadoService.findByIdentificacion(identificacionEmpleado);
            if (optEmpleado.isPresent()) {
                empleado = optEmpleado.get();
                empleadoDTO.convertirEmpleado_a_DTO(empleado);
     
                return new ResponseEntity<EmpleadoDTO>(empleadoDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el empleado");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rol/{rol}")
	public ResponseEntity<?> listarEmpleadosPorRol(@PathVariable("rol") String rolEmpleado){
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun empleado registrado", 
				HttpStatus.NOT_FOUND);
        List <EmpleadoDTO> ArrayEmpleadosDTO = new ArrayList<>();                
		List <Empleado> empleados = this.empleadoService.findByRol(rolEmpleado);
		if (!empleados.isEmpty()) {
            for(Empleado empleado: empleados)
            {
                EmpleadoDTO empleadoDTO = new EmpleadoDTO();
                empleadoDTO.convertirEmpleado_a_DTO(empleado);
                ArrayEmpleadosDTO.add(empleadoDTO);
            }
			respuesta = new ResponseEntity< List <EmpleadoDTO>>(ArrayEmpleadosDTO, HttpStatus.OK);
		}
		return respuesta;
	}

    
}

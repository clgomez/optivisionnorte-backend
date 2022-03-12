package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IEmpleadoService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Empleado objEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
    	Empleado empleado = new Empleado();

        try {
          
            empleado = this.empleadoService.save(objEmpleado);
           
            empleado.setConsultas(null);
            empleado.setFacturas(null);
            empleado.setPedidos(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado empleado = new Empleado();
        
        try {
            Optional<Empleado> optEmpleado = this.empleadoService.findById(idEmpleado);
            if (optEmpleado.isPresent()) {
                empleado = optEmpleado.get();
               
                empleado.setConsultas(null);
                empleado.setPedidos(null);
                return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
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
        List <Empleado> ArrayEmpleados = new ArrayList<>();                
		List <Empleado> empleados = this.empleadoService.findAll();
		if (!empleados.isEmpty()) {
            for(Empleado empleado: empleados)
            {
               
                empleado.setConsultas(null);
                empleado.setFacturas(null);
                empleado.setPedidos(null);
                ArrayEmpleados.add(empleado);
            }
			respuesta = new ResponseEntity< List <Empleado>>(empleados, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idEmpleado, 
                                        @Valid @RequestBody Empleado objEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado empleado = new Empleado();

        try {
            empleado = this.empleadoService.update(idEmpleado, objEmpleado);
           
            empleado.setConsultas(null);
            empleado.setFacturas(null);
            empleado.setPedidos(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            empleadoService.delete(idEmpleado);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
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
        
        try {
            Optional<Empleado> optEmpleado = this.empleadoService.validarEmpleado(idEmpleado, password);
            if (optEmpleado.isPresent()) {
                empleado = optEmpleado.get();
              
                empleado.setConsultas(null);
                empleado.setFacturas(null);
                empleado.setPedidos(null);
                return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
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
    
}

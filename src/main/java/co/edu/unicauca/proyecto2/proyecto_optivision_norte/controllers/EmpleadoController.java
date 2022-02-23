package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IEmpleadoService;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Empleado objEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
    	Empleado empleado = new Empleado();

        try {
            Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objEmpleado.setId(longAleatorio);
            empleado = this.empleadoService.save(objEmpleado);
            empleado.setCitas(null);
            empleado.setConsultas(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long IdEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado objEmpleado = new Empleado();
        
        try {
            Optional<Empleado> empleado = this.empleadoService.findById(IdEmpleado);
            if (empleado.isPresent()) {
                objEmpleado = empleado.get();
                objEmpleado.setCitas(null);
                objEmpleado.setConsultas(null);
                return new ResponseEntity<>(objEmpleado, HttpStatus.OK);
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
            for(Empleado objEmpleado: empleados)
            {
                objEmpleado.setCitas(null);
                objEmpleado.setConsultas(null);
                ArrayEmpleados.add(objEmpleado);
            }
			respuesta = new ResponseEntity<>(empleados, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Empleado objEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        Empleado empleado = new Empleado();

        try {
            /*Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objEmpleado.setId(longAleatorio);*/
            empleado = this.empleadoService.save(objEmpleado);
            empleado.setCitas(null);
            empleado.setConsultas(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long IdEmpleado){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            empleadoService.delete(IdEmpleado);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/validarempleado")
    public ResponseEntity<?> iniciarSesion(@RequestParam Long idEmpleado, @RequestParam String password){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            Optional<Empleado> empleado = this.empleadoService.validarEmpleado(idEmpleado, password);
            if (empleado.isPresent()) {
                return new ResponseEntity<>(empleado.get(), HttpStatus.OK);
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

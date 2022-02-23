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

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente objCliente){
        Map<String, Object> respuesta = new HashMap<>();
    	Cliente cliente = new Cliente();

        try {
            Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objCliente.setId(longAleatorio);
            cliente = this.clienteService.save(objCliente);
            cliente.setCitas(null);
            cliente.setConsultas(null);
            //cliente.setFacturas(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long IdCliente){
        Map<String, Object> respuesta = new HashMap<>();
            Cliente objCliente = new Cliente();
        try {
            Optional<Cliente> cliente = this.clienteService.findById(IdCliente);
            if (cliente.isPresent()) {
                objCliente = cliente.get();
                objCliente.setCitas(null);
                objCliente.setConsultas(null);
                //objCliente.setFacturas(null);
                return new ResponseEntity<>(objCliente, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el cliente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
	public ResponseEntity<?> listar(){
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun cliente registrado", 
				HttpStatus.NOT_FOUND);
        List <Cliente> ArrayClientes = new ArrayList<>();        
		List <Cliente> clientes = this.clienteService.findAll();
		if (!clientes.isEmpty()) {
            for(Cliente objCliente: clientes)
            {
                objCliente.setCitas(null);
                objCliente.setConsultas(null);
                //objCliente.setFacturas(null);
                ArrayClientes.add(objCliente);
            }
			respuesta = new ResponseEntity<>(ArrayClientes, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Cliente objCliente){
        Map<String, Object> respuesta = new HashMap<>();
        Cliente cliente = new Cliente();

        try {
            //Random aleatorio = new Random(System.currentTimeMillis());
            //Long longAleatorio = aleatorio.nextLong();
            //objCliente.setId(longAleatorio);
            cliente = this.clienteService.save(objCliente);
            cliente.setCitas(null);
            cliente.setConsultas(null);
            //cliente.setFacturas(null);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long IdCliente){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            clienteService.delete(IdCliente);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

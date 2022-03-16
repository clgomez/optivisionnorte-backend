package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.ClienteDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IClienteService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody ClienteDTO objClienteDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Cliente objCliente = new Cliente();
    	Cliente cliente = new Cliente();
        ClienteDTO clienteDTO = new ClienteDTO();
       
        try {
            objCliente.convertirDTO_a_Cliente(objClienteDTO);
            cliente = this.clienteService.save(objCliente);
            clienteDTO.convertirCliente_a_DTO(cliente);

        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idCliente){
        Map<String, Object> respuesta = new HashMap<>();
            Cliente cliente = new Cliente();
            ClienteDTO clienteDTO = new ClienteDTO();
        try {
            Optional<Cliente> optCliente = this.clienteService.findById(idCliente);
            if (optCliente.isPresent()) {
                cliente = optCliente.get();
                clienteDTO.convertirCliente_a_DTO(cliente);
     
                return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el cliente");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun cliente registrado", 
				HttpStatus.NOT_FOUND);
        List <ClienteDTO> ArrayClientesDTO = new ArrayList<>();        
		List <Cliente> clientes = this.clienteService.findAll();
		if (!clientes.isEmpty()) {
            for(Cliente cliente: clientes)
            {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.convertirCliente_a_DTO(cliente); 
                ArrayClientesDTO.add(clienteDTO);
            }
			respuesta = new ResponseEntity<List <ClienteDTO>>(ArrayClientesDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idCliente, 
                                        @Valid @RequestBody ClienteDTO objClienteDTO){
        Map<String, Object> respuesta = new HashMap<>();
  
        Cliente objCliente = new Cliente();
    	Cliente cliente = new Cliente();
        ClienteDTO clienteDTO = new ClienteDTO();

        try {
            objCliente.convertirDTO_a_Cliente(objClienteDTO);
            cliente = this.clienteService.update(idCliente, objCliente);
            clienteDTO.convertirCliente_a_DTO(cliente);
    
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idCliente){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(clienteService.delete(idCliente))
            {   respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }else
            {
                respuesta.put("Mensaje","El cliente no existe");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/identificacion/{id}")
    public ResponseEntity<?> BuscarClienteporIdentificacion(@PathVariable("id") Long identificacionCliente){
        Map<String, Object> respuesta = new HashMap<>();
            Cliente cliente = new Cliente();
            ClienteDTO clienteDTO = new ClienteDTO();
            cliente.setId(-1);
        try {
            Optional<Cliente> optCliente = this.clienteService.findByIdentificacion(identificacionCliente);
            if (optCliente.isPresent()) {
                cliente = optCliente.get();
                clienteDTO.convertirCliente_a_DTO(cliente);
     
                return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el cliente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

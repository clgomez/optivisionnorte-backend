
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.DetalleFacturaDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetalleFactura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IDetalleFacturaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/detallefactura")
public class DetalleFacturaController {
    
    @Autowired
    private IDetalleFacturaService detalleFacturaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody DetalleFacturaDTO objDetalleFacturaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        DetalleFactura objDetalleFactura = new DetalleFactura();
        DetalleFactura detalleFactura = new DetalleFactura();
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
               

        try {
            objDetalleFactura.convertirDTO_a_DetalleFactura(objDetalleFacturaDTO);
            detalleFactura = this.detalleFacturaService.save(objDetalleFactura);
            detalleFacturaDTO.convertirDetalleFactura_a_DTO(detalleFactura);
     
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DetalleFacturaDTO>(detalleFacturaDTO, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        DetalleFactura detalleFactura = new DetalleFactura();
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO(); 

        try {
            Optional<DetalleFactura> optDetalleFactura = this.detalleFacturaService.findById(idDetalleFactura);
      
            if (optDetalleFactura.isPresent()) {
                
               detalleFactura = optDetalleFactura.get();
               detalleFacturaDTO.convertirDetalleFactura_a_DTO(detalleFactura); 

               return new ResponseEntity<DetalleFacturaDTO>(detalleFacturaDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el detalle de factura");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun detalle de factura registrado", 
				HttpStatus.NOT_FOUND);
        
        List <DetalleFacturaDTO> ArrayDetallesFacturaDTO = new ArrayList<>();        
		List <DetalleFactura> detallesFactura = this.detalleFacturaService.findAll();
		if (!detallesFactura.isEmpty()) {
             
            for(DetalleFactura detalleFactura: detallesFactura)
            {
               DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO(); 
               detalleFacturaDTO.convertirDetalleFactura_a_DTO(detalleFactura);

               ArrayDetallesFacturaDTO.add(detalleFacturaDTO);
            }
			respuesta = new ResponseEntity<List <DetalleFacturaDTO>>(ArrayDetallesFacturaDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idDetalleFactura, 
                                        @Valid @RequestBody DetalleFacturaDTO objDetalleFacturaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        DetalleFactura objDetalleFactura = new DetalleFactura();
        DetalleFactura detalleFactura = new DetalleFactura();
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        
        try {
            detalleFactura = this.detalleFacturaService.update(idDetalleFactura, objDetalleFactura);
            detalleFacturaDTO.convertirDetalleFactura_a_DTO(detalleFactura);
               
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<DetalleFacturaDTO>(detalleFacturaDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(detalleFacturaService.delete(idDetalleFactura))
            {
                respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }else
            {
                respuesta.put("Mensaje","El detalle de factura no existe");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

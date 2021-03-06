
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.dtos.FacturaDTO;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Factura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IFacturaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/factura")
public class FacturaController {
    
    @Autowired
    private IFacturaService facturaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody FacturaDTO objFacturaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Factura objFactura = new Factura();
        Factura factura = new Factura();
        FacturaDTO facturaDTO = new FacturaDTO();

        try {
            
            objFactura.convertirDTO_a_Factura(objFacturaDTO);
            factura = this.facturaService.save(objFactura);
            facturaDTO.convertirFactura_a_DTO(factura);
      
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<FacturaDTO>(facturaDTO, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idFactura){
        Map<String, Object> respuesta = new HashMap<>();
        FacturaDTO facturaDTO = new FacturaDTO(); 
        Factura factura = new Factura();

        try {
            Optional<Factura> optFactura = this.facturaService.findById(idFactura);

            if (optFactura.isPresent()) {
                
               factura = optFactura.get();
               facturaDTO.convertirFactura_a_DTO(factura); 

               return new ResponseEntity<FacturaDTO>(facturaDTO, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró la factura");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ninguna factura registrada", 
				HttpStatus.NOT_FOUND);
        
        List <FacturaDTO> ArrayFacturasDTO = new ArrayList<>();        
		List <Factura> facturas = this.facturaService.findAll();
		if (!facturas.isEmpty()) {
             
            for(Factura factura: facturas)
            {
               FacturaDTO facturaDTO = new FacturaDTO();
               facturaDTO.convertirFactura_a_DTO(factura);

               ArrayFacturasDTO.add(facturaDTO);
            }
			respuesta = new ResponseEntity<List <FacturaDTO>>(ArrayFacturasDTO, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idFactura, 
                                        @Valid @RequestBody FacturaDTO objFacturaDTO){
        Map<String, Object> respuesta = new HashMap<>();
        Factura objFactura = new Factura();
        Factura factura = new Factura();
        FacturaDTO facturaDTO = new FacturaDTO();
        
        try {
            objFactura.convertirDTO_a_Factura(objFacturaDTO);
            factura = this.facturaService.update(idFactura, objFactura);
            facturaDTO.convertirFactura_a_DTO(factura);

                
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<FacturaDTO>(facturaDTO, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idFactura){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            if(facturaService.delete(idFactura))
            {   respuesta.put("Exito","Se elimino correctamente");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
            }else
            {
                respuesta.put("Mensaje","La factura no existe");
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

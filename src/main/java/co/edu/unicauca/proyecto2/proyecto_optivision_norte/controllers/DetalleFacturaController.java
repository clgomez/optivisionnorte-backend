
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import org.json.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetalleFactura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IDetalleFacturaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/detallefactura")
public class DetalleFacturaController {
    
    @Autowired
    private IDetalleFacturaService detalleFacturaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody DetalleFactura objDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        DetalleFactura detalleFactura = new DetalleFactura();
               
        JSONObject objJSONDetalleFactura = new JSONObject();

        try {
     
            detalleFactura = this.detalleFacturaService.save(objDetalleFactura);

            objJSONDetalleFactura.put("id",detalleFactura.getId());
            objJSONDetalleFactura.put("cantidad",detalleFactura.getCantidad());
            objJSONDetalleFactura.put("precio unitario",detalleFactura.getPrecioUnitario());
            objJSONDetalleFactura.put("sutotal",detalleFactura.getSubtotal());
            objJSONDetalleFactura.put("id_factura",detalleFactura.getObjFactura().getId());
            objJSONDetalleFactura.put("id_producto",detalleFactura.getObjProducto().getId());
         
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONDetalleFactura, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        JSONObject objJSONDetalleFactura = new JSONObject(); 

        try {
            Optional<DetalleFactura> optDetalleFactura = this.detalleFacturaService.findById(idDetalleFactura);
            DetalleFactura detalleFactura = new DetalleFactura();
      
            if (optDetalleFactura.isPresent()) {
                
               detalleFactura = optDetalleFactura.get();

               objJSONDetalleFactura.put("id",detalleFactura.getId());
               objJSONDetalleFactura.put("cantidad",detalleFactura.getCantidad());
               objJSONDetalleFactura.put("precio unitario",detalleFactura.getPrecioUnitario());
               objJSONDetalleFactura.put("sutotal",detalleFactura.getSubtotal());
               objJSONDetalleFactura.put("id_factura",detalleFactura.getObjFactura().getId());
               objJSONDetalleFactura.put("id_producto",detalleFactura.getObjProducto().getId());
                         
               System.out.print(objJSONDetalleFactura);

               return new ResponseEntity<JSONObject>(objJSONDetalleFactura, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el detalle factura");
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
        
        JSONArray ArrayJSONDetallesFactura = new JSONArray();        
		List <DetalleFactura> detallesFactura = this.detalleFacturaService.findAll();
		if (!detallesFactura.isEmpty()) {
             
            for(DetalleFactura detalleFactura: detallesFactura)
            {
               JSONObject objJSONDetalleFactura = new JSONObject();

               objJSONDetalleFactura.put("id",detalleFactura.getId());
               objJSONDetalleFactura.put("cantidad",detalleFactura.getCantidad());
               objJSONDetalleFactura.put("precio unitario",detalleFactura.getPrecioUnitario());
               objJSONDetalleFactura.put("sutotal",detalleFactura.getSubtotal());
               objJSONDetalleFactura.put("id_factura",detalleFactura.getObjFactura().getId());
               objJSONDetalleFactura.put("id_producto",detalleFactura.getObjProducto().getId());
              

               ArrayJSONDetallesFactura.put(objJSONDetalleFactura);
            }
			respuesta = new ResponseEntity<JSONArray>(ArrayJSONDetallesFactura, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idDetalleFactura, 
                                        @Valid @RequestBody DetalleFactura objDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        DetalleFactura detalleFactura = new DetalleFactura();
        JSONObject objJSONDetalleFactura = new JSONObject();
        
        try {
            detalleFactura = this.detalleFacturaService.update(idDetalleFactura, objDetalleFactura);

            objJSONDetalleFactura.put("id",detalleFactura.getId());
            objJSONDetalleFactura.put("cantidad",detalleFactura.getCantidad());
            objJSONDetalleFactura.put("precio unitario",detalleFactura.getPrecioUnitario());
            objJSONDetalleFactura.put("sutotal",detalleFactura.getSubtotal());
            objJSONDetalleFactura.put("id_factura",detalleFactura.getObjFactura().getId());
            objJSONDetalleFactura.put("id_producto",detalleFactura.getObjProducto().getId());

            
                
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<JSONObject>(objJSONDetalleFactura, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idDetalleFactura){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            detalleFacturaService.delete(idDetalleFactura);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

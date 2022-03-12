
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import org.json.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetallePedido;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IDetallePedidoService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/detallepedido")
public class DetallePedidoController {

    @Autowired
    private IDetallePedidoService detallePedidoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody DetallePedido objDetallePedido){
        Map<String, Object> respuesta = new HashMap<>();
        DetallePedido detallePedido = new DetallePedido();
               
        JSONObject objJSONDetallePedido = new JSONObject();

        try {
     
            detallePedido = this.detallePedidoService.save(objDetallePedido);

            objJSONDetallePedido.put("id",detallePedido.getId());
            objJSONDetallePedido.put("cantidad",detallePedido.getCantidad());
            objJSONDetallePedido.put("precio unitario",detallePedido.getPrecioUnitario());
            objJSONDetallePedido.put("sutotal",detallePedido.getSubtotal());
            objJSONDetallePedido.put("id_pedido",detallePedido.getObjPedido().getId());
            objJSONDetallePedido.put("id_producto",detallePedido.getObjProducto().getId());
         
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONDetallePedido, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idDetallePedido){
        Map<String, Object> respuesta = new HashMap<>();
        JSONObject objJSONDetallePedido = new JSONObject(); 

        try {
            Optional<DetallePedido> optDetallePedido = this.detallePedidoService.findById(idDetallePedido);
            DetallePedido detallePedido = new DetallePedido();
      
            if (optDetallePedido.isPresent()) {
                
               detallePedido = optDetallePedido.get();

               objJSONDetallePedido.put("id",detallePedido.getId());
               objJSONDetallePedido.put("cantidad",detallePedido.getCantidad());
               objJSONDetallePedido.put("precio unitario",detallePedido.getPrecioUnitario());
               objJSONDetallePedido.put("sutotal",detallePedido.getSubtotal());
               objJSONDetallePedido.put("id_pedido",detallePedido.getObjPedido().getId());
               objJSONDetallePedido.put("id_producto",detallePedido.getObjProducto().getId());

                         
               System.out.print(objJSONDetallePedido);

               return new ResponseEntity<JSONObject>(objJSONDetallePedido, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el detalle de pedido");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun detalle de pedido registrado", 
				HttpStatus.NOT_FOUND);
        
        JSONArray ArrayJSONDetallesPedido = new JSONArray();        
		List <DetallePedido> detallesPedido = this.detallePedidoService.findAll();
		if (!detallesPedido.isEmpty()) {
             
            for(DetallePedido detallePedido: detallesPedido)
            {
               JSONObject objJSONDetallePedido = new JSONObject();

               objJSONDetallePedido.put("id",detallePedido.getId());
               objJSONDetallePedido.put("cantidad",detallePedido.getCantidad());
               objJSONDetallePedido.put("precio unitario",detallePedido.getPrecioUnitario());
               objJSONDetallePedido.put("sutotal",detallePedido.getSubtotal());
               objJSONDetallePedido.put("id_pedido",detallePedido.getObjPedido().getId());
               objJSONDetallePedido.put("id_producto",detallePedido.getObjProducto().getId());

               ArrayJSONDetallesPedido.put(objJSONDetallePedido);
            }
			respuesta = new ResponseEntity<JSONArray>(ArrayJSONDetallesPedido, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idDetallePedido, 
                                        @Valid @RequestBody DetallePedido objDetallePedido){
        Map<String, Object> respuesta = new HashMap<>();
        DetallePedido detallePedido = new DetallePedido();
        JSONObject objJSONDetallePedido = new JSONObject();
        
        try {
            detallePedido = this.detallePedidoService.update(idDetallePedido, objDetallePedido);

            objJSONDetallePedido.put("id",detallePedido.getId());
            objJSONDetallePedido.put("cantidad",detallePedido.getCantidad());
            objJSONDetallePedido.put("precio unitario",detallePedido.getPrecioUnitario());
            objJSONDetallePedido.put("sutotal",detallePedido.getSubtotal());
            objJSONDetallePedido.put("id_pedido",detallePedido.getObjPedido().getId());
            objJSONDetallePedido.put("id_producto",detallePedido.getObjProducto().getId());
            
                
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<JSONObject>(objJSONDetallePedido, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idDetallePedido){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            detallePedidoService.delete(idDetallePedido);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

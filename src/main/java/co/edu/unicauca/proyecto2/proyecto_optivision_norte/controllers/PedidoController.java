package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import org.json.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Pedido;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IPedidoService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Pedido objPedido){
        Map<String, Object> respuesta = new HashMap<>();
        Pedido pedido = new Pedido();
               
        JSONObject objJSONPedido = new JSONObject();

        try {
     
            pedido = this.pedidoService.save(objPedido);

            objJSONPedido.put("id",pedido.getId());
            objJSONPedido.put("fecha",pedido.getFecha());
            objJSONPedido.put("total",pedido.getTotal());
            objJSONPedido.put("id_empleado",pedido.getObjEmpleado().getId());
            objJSONPedido.put("id_proveedor",pedido.getObjProveedor().getId());
         
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONPedido, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idPedido){
        Map<String, Object> respuesta = new HashMap<>();
        JSONObject objJSONPedido = new JSONObject(); 

        try {
            Optional<Pedido> optPedido = this.pedidoService.findById(idPedido);
            Pedido pedido = new Pedido();
      
            if (optPedido.isPresent()) {
                
               pedido = optPedido.get();

               objJSONPedido.put("id",pedido.getId());
               objJSONPedido.put("fecha",pedido.getFecha());
               objJSONPedido.put("total",pedido.getTotal());
               objJSONPedido.put("id_empleado",pedido.getObjEmpleado().getId());
               objJSONPedido.put("id_proveedor",pedido.getObjProveedor().getId());
                                 
               System.out.print(objJSONPedido);

               return new ResponseEntity<JSONObject>(objJSONPedido, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el pedido");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun pedido registrado", 
				HttpStatus.NOT_FOUND);
        
        JSONArray ArrayJSONPedidos = new JSONArray();        
		List <Pedido> pedidos = this.pedidoService.findAll();
		if (!pedidos.isEmpty()) {
             
            for(Pedido pedido:pedidos)
            {
               JSONObject objJSONPedido = new JSONObject();
            
               objJSONPedido.put("id",pedido.getId());
               objJSONPedido.put("fecha",pedido.getFecha());
               objJSONPedido.put("total",pedido.getTotal());
               objJSONPedido.put("id_empleado",pedido.getObjEmpleado().getId());
               objJSONPedido.put("id_proveedor",pedido.getObjProveedor().getId());

               ArrayJSONPedidos.put(objJSONPedido);
            }
			respuesta = new ResponseEntity<JSONArray>(ArrayJSONPedidos, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idPedido, 
                                        @Valid @RequestBody Pedido objPedido){
        Map<String, Object> respuesta = new HashMap<>();
        Pedido pedido = new Pedido();
        JSONObject objJSONPedido = new JSONObject();
        
        try {
            pedido = this.pedidoService.update(idPedido, objPedido);

            objJSONPedido.put("id",pedido.getId());
            objJSONPedido.put("fecha",pedido.getFecha());
            objJSONPedido.put("total",pedido.getTotal());
            objJSONPedido.put("id_empleado",pedido.getObjEmpleado().getId());
            objJSONPedido.put("id_proveedor",pedido.getObjProveedor().getId());
                
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONPedido, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idPedido){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            pedidoService.delete(idPedido);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

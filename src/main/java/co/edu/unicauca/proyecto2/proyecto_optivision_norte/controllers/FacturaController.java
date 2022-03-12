
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import org.json.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Factura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IFacturaService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/factura")
public class FacturaController {
    
    @Autowired
    private IFacturaService facturaService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Factura objFactura){
        Map<String, Object> respuesta = new HashMap<>();
        Factura factura = new Factura();
               
        JSONObject objJSONFactura = new JSONObject();

        try {
     
            factura = this.facturaService.save(objFactura);

            objJSONFactura.put("id",factura.getId());
            objJSONFactura.put("fecha",factura.getFecha());
            objJSONFactura.put("total",factura.getTotal());
            objJSONFactura.put("abono",factura.getAbono());
            objJSONFactura.put("saldo",factura.getSaldo());
            objJSONFactura.put("id_cliente",factura.getObjCliente().getId());
            objJSONFactura.put("id_empleado",factura.getObjEmpleado().getId());
         
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(objJSONFactura, HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idFactura){
        Map<String, Object> respuesta = new HashMap<>();
        JSONObject objJSONFactura = new JSONObject(); 

        try {
            Optional<Factura> optFactura = this.facturaService.findById(idFactura);
            Factura factura = new Factura();
      
            if (optFactura.isPresent()) {
                
               factura = optFactura.get();

               objJSONFactura.put("id",factura.getId());
               objJSONFactura.put("fecha",factura.getFecha());
               objJSONFactura.put("total",factura.getTotal());
               objJSONFactura.put("abono",factura.getAbono());
               objJSONFactura.put("saldo",factura.getSaldo());
               objJSONFactura.put("id_cliente",factura.getObjCliente().getId());
               objJSONFactura.put("id_empleado",factura.getObjEmpleado().getId());
                         
               System.out.print(objJSONFactura);

               return new ResponseEntity<JSONObject>(objJSONFactura, HttpStatus.OK);
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
        
        JSONArray ArrayJSONFacturas = new JSONArray();        
		List <Factura> facturas = this.facturaService.findAll();
		if (!facturas.isEmpty()) {
             
            for(Factura factura: facturas)
            {
               JSONObject objJSONFactura = new JSONObject();
               objJSONFactura.put("id",factura.getId());
               objJSONFactura.put("fecha",factura.getFecha());
               objJSONFactura.put("total",factura.getTotal());
               objJSONFactura.put("abono",factura.getAbono());
               objJSONFactura.put("saldo",factura.getSaldo());
               objJSONFactura.put("id_cliente",factura.getObjCliente().getId());
               objJSONFactura.put("id_empleado",factura.getObjEmpleado().getId());
              

               ArrayJSONFacturas.put(objJSONFactura);
            }
			respuesta = new ResponseEntity<JSONArray>(ArrayJSONFacturas, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idFactura, 
                                        @Valid @RequestBody Factura objFactura){
        Map<String, Object> respuesta = new HashMap<>();
        Factura factura = new Factura();
        JSONObject objJSONFactura = new JSONObject();
        
        try {
            factura = this.facturaService.update(idFactura, objFactura);

            objJSONFactura.put("id",factura.getId());
            objJSONFactura.put("fecha",factura.getFecha());
            objJSONFactura.put("total",factura.getTotal());
            objJSONFactura.put("abono",factura.getAbono());
            objJSONFactura.put("saldo",factura.getSaldo());
            objJSONFactura.put("id_cliente",factura.getObjCliente().getId());
            objJSONFactura.put("id_empleado",factura.getObjEmpleado().getId());
                
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<JSONObject>(objJSONFactura, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idFactura){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            facturaService.delete(idFactura);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

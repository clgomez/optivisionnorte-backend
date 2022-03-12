
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Proveedor;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IProveedorService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Proveedor objProveedor){
        Map<String, Object> respuesta = new HashMap<>();
    	Proveedor proveedor = new Proveedor();

        try {
            proveedor = this.proveedorService.save(objProveedor);
            proveedor.setPedidos(null);
          
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idProveedor){
        Map<String, Object> respuesta = new HashMap<>();
        Proveedor proveedor = new Proveedor();
        try {
            Optional<Proveedor> optProveedor = this.proveedorService.findById(idProveedor);
            if (optProveedor.isPresent()) {
                proveedor = optProveedor.get();
                proveedor.setPedidos(null);
             
                return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun proveedor registrado", 
				HttpStatus.NOT_FOUND);
        List <Proveedor> ArrayProveedores = new ArrayList<>();        
		List <Proveedor> proveedores = this.proveedorService.findAll();
		if (!proveedores.isEmpty()) {
            for(Proveedor proveedor:proveedores)
            {
                proveedor.setPedidos(null);
                ArrayProveedores.add(proveedor);
            }
			respuesta = new ResponseEntity<List <Proveedor>>(ArrayProveedores, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idProveedor, 
                                        @Valid @RequestBody Proveedor objProveedor){
        Map<String, Object> respuesta = new HashMap<>();
        Proveedor proveedor = new Proveedor();

        try {
            proveedor = this.proveedorService.update(idProveedor, objProveedor);
            proveedor.setPedidos(null);
         
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idProveedor){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            proveedorService.delete(idProveedor);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}

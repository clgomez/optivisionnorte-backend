package co.edu.unicauca.proyecto2.proyecto_optivision_norte.controllers;

import java.util.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Producto;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.services.IProductoService;

@RestController
@CrossOrigin(origins={"http://localhost:4400","http://localhost:4200"})
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

	@PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Producto objProducto){
        Map<String, Object> respuesta = new HashMap<>();
    	Producto producto = new Producto();
    
        try {
            producto = this.productoService.save(objProducto);
            producto.setDetallesFactura(null);
            producto.setDetallesPedido(null);
                            
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }


	@GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long idProducto){
        Map<String, Object> respuesta = new HashMap<>();
            Producto producto = new Producto();
        try {
            Optional<Producto> optProducto = this.productoService.findById(idProducto);
            if (optProducto.isPresent()) {
                producto = optProducto.get();
                producto.setDetallesFactura(null);
                producto.setDetallesPedido(null);
                                       
                return new ResponseEntity<>(producto, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el producto");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun producto registrado", 
				HttpStatus.NOT_FOUND);

        List <Producto> ArrayProductos = new ArrayList<>();            
		List <Producto> productos = this.productoService.findAll();
		if (!productos.isEmpty()) {
            for(Producto producto: productos)
            {
                producto.setDetallesFactura(null);
                producto.setDetallesPedido(null);   
                ArrayProductos.add(producto);
            }
         
			respuesta = new ResponseEntity<List <Producto>>(ArrayProductos, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long idProducto, @Valid @RequestBody Producto objProducto){
        Map<String, Object> respuesta = new HashMap<>();
        Producto producto = new Producto();

        try {
            producto = this.productoService.update(idProducto, objProducto);
            producto.setDetallesFactura(null);
            producto.setDetallesPedido(null);   
          
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long idProducto){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            productoService.delete(idProducto);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

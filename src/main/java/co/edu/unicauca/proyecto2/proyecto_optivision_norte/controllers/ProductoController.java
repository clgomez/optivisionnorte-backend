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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            Random aleatorio = new Random(System.currentTimeMillis());
            Long longAleatorio = aleatorio.nextLong();
            objProducto.setId(longAleatorio);
            producto = this.productoService.save(objProducto);
                            
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
            Producto objProducto = new Producto();
        try {
            Optional<Producto> producto = this.productoService.findById(idProducto);
            if (producto.isPresent()) {
                objProducto = producto.get();
                                       
                return new ResponseEntity<>(objProducto, HttpStatus.OK);
            } else {
                respuesta.put("mensaje", "No se encontró el producto");
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
		ResponseEntity<?> respuesta = new ResponseEntity<>("No hay ningun producto registrado", 
				HttpStatus.NOT_FOUND);
        List <Producto> ArrayProductos = new ArrayList<>();        
		List <Producto> productos = this.productoService.findAll();
		if (!productos.isEmpty()) {
            for(Producto objProducto: productos)
            {
              
                ArrayProductos.add(objProducto);
            }
			respuesta = new ResponseEntity<>(ArrayProductos, HttpStatus.OK);
		}
		return respuesta;
	} 

	@PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Producto objProducto){
        Map<String, Object> respuesta = new HashMap<>();
        Producto producto = new Producto();

        try {
            //Random aleatorio = new Random(System.currentTimeMillis());
            //Long longAleatorio = aleatorio.nextLong();
            //objCliente.setId(longAleatorio);
            producto = this.productoService.save(objProducto);
          
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al actualizar en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

	@DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long IdProducto){
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            productoService.delete(IdProducto);
            respuesta.put("Exito","Se elimino correctamente");
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataAccessException e){
            respuesta.put("mensaje", "Error al realizar la eliminacion en la base de datos");
            respuesta.put("Error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

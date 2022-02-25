package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Producto;

public interface IProductoService {
    public Producto save(Producto objProducto);
    public Optional<Producto> findById(Long idProducto);
    public List <Producto> findAll();
    public Producto update(Long idProducto, Producto objProducto);
    public boolean delete(Long idProducto);
}

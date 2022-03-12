
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Proveedor;

public interface IProveedorService {
    public Proveedor save(Proveedor objProveedor);
    public Optional<Proveedor> findById(Long idProveedor);
    public List <Proveedor> findAll();
    public Proveedor update(Long idProveedor, Proveedor objProveedor);
    public boolean delete(Long idProveedor);
    
}

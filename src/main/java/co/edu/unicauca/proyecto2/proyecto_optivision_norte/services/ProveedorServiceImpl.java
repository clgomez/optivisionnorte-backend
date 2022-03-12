
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Proveedor;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements IProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Proveedor save(Proveedor objProveedor)
    {
        return this.proveedorRepository.save(objProveedor);
    }

    @Override
    public Optional<Proveedor> findById(Long idProveedor)
    {
        return this.proveedorRepository.findById(idProveedor);
    }

    @Override
    public List <Proveedor> findAll()
    {
        return this.proveedorRepository.findAll();
    }
    
    @Override
    public Proveedor update(Long idProveedor, Proveedor objProveedor)
    {
        if(this.proveedorRepository.existsById(idProveedor))
        {
            objProveedor.setId(idProveedor);
            return this.proveedorRepository.save(objProveedor);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idProveedor)
    {
        Optional <Proveedor> optProveedor = this.proveedorRepository.findById(idProveedor);
        if(optProveedor.isPresent())
        {   this.proveedorRepository.deleteById(idProveedor);
            return true; 
        }
        return false;
    }
    
}


package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetalleFactura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.DetalleFacturaRepository;

@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService{

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public DetalleFactura save(DetalleFactura objDetalleFactura)
    {
        return this.detalleFacturaRepository.save(objDetalleFactura);
    }

    @Override
    public Optional<DetalleFactura> findById(Long idDetalleFactura)
    {
        return this.detalleFacturaRepository.findById(idDetalleFactura);
    }

    @Override
    public List <DetalleFactura> findAll()
    {
        return this.detalleFacturaRepository.findAll();
    }
    
    @Override
    public DetalleFactura update(Long idDetalleFactura, DetalleFactura objDetalleFactura)
    {
        if(this.detalleFacturaRepository.existsById(idDetalleFactura))
        {
            objDetalleFactura.setId(idDetalleFactura);
            return this.detalleFacturaRepository.save(objDetalleFactura);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idDetalleFactura)
    {
        Optional <DetalleFactura> optDetalleFactura = this.detalleFacturaRepository.findById(idDetalleFactura);
        if(optDetalleFactura.isPresent())
        {   this.detalleFacturaRepository.deleteById(idDetalleFactura);
            return true; 
        }
        return false;
    }
    
}

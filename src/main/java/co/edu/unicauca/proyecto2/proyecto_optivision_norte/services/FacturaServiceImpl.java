
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Factura;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.FacturaRepository;

@Service
public class FacturaServiceImpl implements IFacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Factura save(Factura objFactura)
    {
        return this.facturaRepository.save(objFactura);
    }

    @Override
    public Optional<Factura> findById(Long idFactura)
    {
        return this.facturaRepository.findById(idFactura);
    }

    @Override
    public List <Factura> findAll()
    {
        return this.facturaRepository.findAll();
    }
    
    @Override
    public Factura update(Long idFactura, Factura objFactura)
    {
        if(this.facturaRepository.existsById(idFactura))
        {
            objFactura.setId(idFactura);
            return this.facturaRepository.save(objFactura);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idFactura)
    {
        Optional <Factura> optFactura = this.facturaRepository.findById(idFactura);
        if(optFactura.isPresent())
        {   this.facturaRepository.deleteById(idFactura);
            return true; 
        }
        return false;
    }
    
}

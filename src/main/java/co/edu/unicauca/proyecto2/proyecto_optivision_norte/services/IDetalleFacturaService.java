
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetalleFactura;

public interface IDetalleFacturaService {
    public DetalleFactura save(DetalleFactura objDetalleFactura);
    public Optional<DetalleFactura> findById(Long idDetalleFactura);
    public List <DetalleFactura> findAll();
    public DetalleFactura update(Long idDetalleFactura, DetalleFactura objDetalleFactura);
    public boolean delete(Long idDetalleFactura);
    
}


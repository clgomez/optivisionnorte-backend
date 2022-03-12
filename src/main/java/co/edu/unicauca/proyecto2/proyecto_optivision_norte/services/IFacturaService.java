package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Factura;

public interface IFacturaService {
    public Factura save(Factura objFactura);
    public Optional<Factura> findById(Long idFactura);
    public List <Factura> findAll();
    public Factura update(Long idFactura, Factura objFactura);
    public boolean delete(Long idFactura);
}

package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;

public interface IClienteService {
    public Cliente save(Cliente objCliente);
    public Optional<Cliente> findById(Long idCliente);
    public List <Cliente> findAll();
    public Cliente update(Long idCliente, Cliente objCliente);
    public boolean delete(Long idCliente);
    public Optional <Cliente> findByIdentificacion(Long identificacionCliente);
    
}

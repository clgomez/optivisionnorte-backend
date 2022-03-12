package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente objCliente)
    {
        return this.clienteRepository.save(objCliente);
    }

    @Override
    public Optional<Cliente> findById(Long idCliente)
    {
        return this.clienteRepository.findById(idCliente);
    }

    @Override
    public List <Cliente> findAll()
    {
        return this.clienteRepository.findAll();
    }
    
    @Override
    public Cliente update(Long idCliente, Cliente objCliente)
    {
        if(this.clienteRepository.existsById(idCliente))
        {
            objCliente.setId(idCliente);
            return this.clienteRepository.save(objCliente);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idCliente)
    {
        Optional <Cliente> optCliente = this.clienteRepository.findById(idCliente);
        if(optCliente.isPresent())
        {   this.clienteRepository.deleteById(idCliente);
            return true; 
        }
        return false;
    }

    @Override
    public Optional <Cliente> findByIdentificacion(Long identificacionCliente) {
        
        return this.clienteRepository.findByIdentificacion(identificacionCliente);
    }
    
    
}

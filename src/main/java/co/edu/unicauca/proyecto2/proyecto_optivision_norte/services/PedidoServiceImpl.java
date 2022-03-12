package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Pedido;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.PedidoRepository;

@Service
public class PedidoServiceImpl implements IPedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido save(Pedido objPedido)
    {
        return this.pedidoRepository.save(objPedido);
    }

    @Override
    public Optional<Pedido> findById(Long idPedido)
    {
        return this.pedidoRepository.findById(idPedido);
    }

    @Override
    public List <Pedido> findAll()
    {
        return this.pedidoRepository.findAll();
    }
    
    @Override
    public Pedido update(Long idPedido, Pedido objPedido)
    {
        if(this.pedidoRepository.existsById(idPedido))
        {
            objPedido.setId(idPedido);
            return this.pedidoRepository.save(objPedido);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idPedido)
    {
        Optional <Pedido> optPedido = this.pedidoRepository.findById(idPedido);
        if(optPedido.isPresent())
        {   this.pedidoRepository.deleteById(idPedido);
            return true; 
        }
        return false;
    }
    
}

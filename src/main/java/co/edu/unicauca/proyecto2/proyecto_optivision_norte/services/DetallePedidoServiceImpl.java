
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetallePedido;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.DetallePedidoRepository;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService{
    
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public DetallePedido save(DetallePedido objDetallePedido)
    {
        return this.detallePedidoRepository.save(objDetallePedido);
    }

    @Override
    public Optional<DetallePedido> findById(Long idDetallePedido)
    {
        return this.detallePedidoRepository.findById(idDetallePedido);
    }

    @Override
    public List <DetallePedido> findAll()
    {
        return this.detallePedidoRepository.findAll();
    }
    
    @Override
    public DetallePedido update(Long idDetallePedido, DetallePedido objDetallePedido)
    {
        if(this.detallePedidoRepository.existsById(idDetallePedido))
        {
            objDetallePedido.setId(idDetallePedido);
            return this.detallePedidoRepository.save(objDetallePedido);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idDetallePedido)
    {
        Optional <DetallePedido> optDetallePedido = this.detallePedidoRepository.findById(idDetallePedido);
        if(optDetallePedido.isPresent())
        {   this.detallePedidoRepository.deleteById(idDetallePedido);
            return true; 
        }
        return false;
    }
    
}

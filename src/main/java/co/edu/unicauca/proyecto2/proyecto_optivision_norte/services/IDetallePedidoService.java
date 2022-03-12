
package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.DetallePedido;

public interface IDetallePedidoService {
    public DetallePedido save(DetallePedido objDetallePedido);
    public Optional<DetallePedido> findById(Long idDetallePedido);
    public List <DetallePedido> findAll();
    public DetallePedido update(Long idDetallePedido, DetallePedido objDetallePedido);
    public boolean delete(Long idDetallePedido);
    
}

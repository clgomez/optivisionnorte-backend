package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Pedido;

public interface IPedidoService {
    public Pedido save(Pedido objPedido);
    public Optional<Pedido> findById(Long idPedido);
    public List <Pedido> findAll();
    public Pedido update(Long idPedido, Pedido objPedido);
    public boolean delete(Long idPedido);
    
}

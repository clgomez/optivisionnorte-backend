package co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    public Optional <Cliente> findByIdentificacion(Long identificacionCliente);
}

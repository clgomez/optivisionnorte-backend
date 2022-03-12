package co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

    Optional<Empleado> findByIdentificacionAndPassword(Long idEmpleado, String password);
    
}

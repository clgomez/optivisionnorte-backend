package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;

public interface IEmpleadoService {
    public Empleado save(Empleado objEmpleado);
    public Optional<Empleado> findById(Long idEmpleado);
    public List <Empleado> findAll();
    public Empleado update(Long idEmpleado, Empleado objEmpleado);
    public boolean delete(Long idEmpleado);
    public Optional<Empleado> validarEmpleado(Long idEmpleado, String password);
    public Optional <Empleado> findByIdentificacion(Long identificacionEmpleado);
    public List <Empleado> findByRol(String rol);

}

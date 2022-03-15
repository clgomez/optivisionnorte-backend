package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Empleado;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado save(Empleado objEmpleado)
    {
        return this.empleadoRepository.save(objEmpleado);
    }

    @Override
    public Optional<Empleado> findById(Long idEmpleado)
    {
        return this.empleadoRepository.findById(idEmpleado);
    }

    @Override
    public List <Empleado> findAll()
    {
        return this.empleadoRepository.findAll();
    }
    
    @Override
    public Empleado update(Long idEmpleado, Empleado objEmpleado)
    {
        if(this.empleadoRepository.existsById(idEmpleado))
        {
            objEmpleado.setId(idEmpleado);
            return this.empleadoRepository.save(objEmpleado);
        }
        else return null;    
    }

    @Override
    public boolean delete(Long idEmpleado)
    {
        Optional <Empleado> optEmpleado = this.empleadoRepository.findById(idEmpleado);
        if(optEmpleado.isPresent())
        {   this.empleadoRepository.deleteById(idEmpleado);
            return true; 
        }
        return false;
    }

    @Override
    public Optional <Empleado> validarEmpleado(Long idEmpleado, String password)
    {
        return this.empleadoRepository.findByIdentificacionAndPassword(idEmpleado, password);
        
    }

    @Override
    public Optional <Empleado> findByIdentificacion(Long identificacionEmpleado) {
        return this.empleadoRepository.findByIdentificacion(identificacionEmpleado);
    }

    @Override
    public List<Empleado> findByRol(String rol) {
        return this.empleadoRepository.findByRol(rol);
    }

    
    
}

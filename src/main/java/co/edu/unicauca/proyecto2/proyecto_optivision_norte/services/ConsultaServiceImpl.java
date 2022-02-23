package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Consulta;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.ConsultaRepository;

@Service
public class ConsultaServiceImpl implements IConsultaService{
    
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public Consulta save(Consulta objConsulta)
    {
        return this.consultaRepository.save(objConsulta);
    }

    @Override
    public Optional<Consulta> findById(Long idConsulta)
    {
        return this.consultaRepository.findById(idConsulta);
    }

    @Override
    public List <Consulta> findAll()
    {
        return this.consultaRepository.findAll();
    }
    
    @Override
    public Consulta update(Long idConsulta, Consulta objConsulta)
    {
        if(this.consultaRepository.existsById(idConsulta))
            return this.consultaRepository.save(objConsulta);
        else return null;    
    }

    @Override
    public boolean delete(Long idConsulta)
    {
        Optional <Consulta> optConsulta = this.findById(idConsulta);
        if(optConsulta.isPresent())
        {   this.consultaRepository.deleteById(idConsulta);
            return true; 
        }
        return false;
    }
}

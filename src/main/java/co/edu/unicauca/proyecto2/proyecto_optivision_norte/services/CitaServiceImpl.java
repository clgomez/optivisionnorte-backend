package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cita;
import co.edu.unicauca.proyecto2.proyecto_optivision_norte.repositories.CitaRepository;

@Service
public class CitaServiceImpl implements ICitaService{

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita save(Cita objCita)
    {
        return this.citaRepository.save(objCita);
    }

    @Override
    public Optional<Cita> findById(Long idCita)
    {
        return this.citaRepository.findById(idCita);
    }

    @Override
    public List <Cita> findAll()
    {
        return this.citaRepository.findAll();
      
    }

    @Override
    public Cita update(Long idCita, Cita objCita)
    {
        if(this.citaRepository.existsById(idCita))
        {
            objCita.setId(idCita);
            return this.citaRepository.save(objCita);
        }
        else return null; 
    }

    @Override
    public boolean delete(Long idCita)
    {
        Optional <Cita> optCita = this.citaRepository.findById(idCita);
        if(optCita.isPresent())
        {   this.citaRepository.deleteById(idCita);
            return true; 
        }
        return false;
    }
    
}
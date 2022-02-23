package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Cita;

public interface ICitaService {
    public Cita save(Cita objCita);
    public Optional<Cita> findById(Long idCita);
    public List <Cita> findAll();
    public Cita update(Long idCita, Cita objCita);
    public boolean delete(Long idCita);
    
}
 
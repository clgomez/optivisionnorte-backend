package co.edu.unicauca.proyecto2.proyecto_optivision_norte.services;

import java.util.List;
import java.util.Optional;

import co.edu.unicauca.proyecto2.proyecto_optivision_norte.entities.Consulta;

public interface IConsultaService {
    public Consulta save(Consulta objConsulta);
    public Optional<Consulta> findById(Long idConsulta);
    public List <Consulta> findAll();
    public Consulta update(Long idConsulta, Consulta objConsulta);
    public boolean delete(Long idCliente);
}

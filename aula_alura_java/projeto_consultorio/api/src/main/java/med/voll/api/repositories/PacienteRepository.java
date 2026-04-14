package med.voll.api.repositories;

import med.voll.api.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    Optional<Paciente> findById(Long id);
    List<Paciente> findAll(int size, int offset);
    Integer save(Paciente paciente);
    Integer update(Paciente paciente, Long id);
    Integer delete(Long id);
}

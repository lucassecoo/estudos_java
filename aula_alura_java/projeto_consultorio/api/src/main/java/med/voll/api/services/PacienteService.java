package med.voll.api.services;

import med.voll.api.entities.Paciente;
import med.voll.api.exceptions.ResourceNotFoundException;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository){this.pacienteRepository = pacienteRepository;}

    public List<Paciente> findAllPacientes(int page, int size){
        int offset = (page -1) * size;

        return this.pacienteRepository.findAll(size, offset);
    }

    public Optional<Paciente> findByIdPaciente(long id){
        return Optional.ofNullable(this.pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrada")));
    }

    public void savePaciente(Paciente paciente){
        var save = this.pacienteRepository.save(paciente);
        if (save != 1) {
            throw new IllegalStateException("Erro ao salvar paciente " + paciente.getNome());
        }
    }

    public void updatePaciente(Paciente paciente, Long id){
        var update = this.pacienteRepository.update(paciente, id);
        if (update == 0) {
            throw new IllegalStateException("Erro ao salvar paciente " + paciente.getNome());
        }
    }

    public void deletePaciente(Long id){
        var delete = this.pacienteRepository.delete(id);
        if (delete == 0) {
            throw new IllegalStateException("Erro ao deletar paciente " + id);
        }
    }
}

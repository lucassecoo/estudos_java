package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.requests.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.locatech.repositories.PessoaRepository;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;
    private final PessoaRepository pessoaRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository, PessoaRepository pessoaRepository){
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size){
        int offset = (page -1) * size;

        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return Optional.ofNullable(this.aluguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO aluguel) {
        var aluguelEntity = calculaAluguel(aluguel);
        var save = this.aluguelRepository.save(aluguelEntity);
        if (save != 1) {
            throw new IllegalStateException("Erro ao salvar aluguel " + aluguel.toString());
        }
    }

    public void updateAluguel(Aluguel aluguel, Long id) {
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new IllegalStateException("Erro ao salvar aluguel " + aluguel.toString());
        }
    }

    public void deleteAluguel(Long id){
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0) {
            throw new IllegalStateException("Erro ao deletar aluguel " + id);
        }
    }

    private void validarData(AluguelRequestDTO dto){
        if (dto.dataFim().isBefore(dto.dataInicio())) {
            throw new IllegalArgumentException("Data fim inválida");
        }
    }

    private long calcularDias(AluguelRequestDTO dto){
        long dias = ChronoUnit.DAYS.between(dto.dataInicio(), dto.dataFim());

        if (dias <= 0) {
            throw new IllegalArgumentException("Quantidade de dias inválida");
        }

        return dias;
    }

    private Aluguel calculaAluguel(AluguelRequestDTO dto) {
        validarData(dto);

        long dias = calcularDias(dto);

        var veiculo = this.veiculoRepository.findById(dto.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado."));

        var pessoa = this.pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        var valor = veiculo.getValorDiaria().multiply(BigDecimal.valueOf(dias));

        return new Aluguel(dto, valor);
    }
}
